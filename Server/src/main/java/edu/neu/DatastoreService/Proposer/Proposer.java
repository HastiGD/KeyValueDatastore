package edu.neu.DatastoreService.Proposer;

import edu.neu.DatastoreService.Acceptor.AcceptorGrpc;
import edu.neu.DatastoreService.Acceptor.AcceptorGrpc.AcceptorBlockingStub;
import edu.neu.DatastoreService.Acceptor.AcceptorOuterClass.ProposeRequest;
import edu.neu.DatastoreService.Acceptor.AcceptorOuterClass.ProposeResponse;
import edu.neu.DatastoreService.Acceptor.AcceptorOuterClass.PrepareRequest;
import edu.neu.DatastoreService.Acceptor.AcceptorOuterClass.PrepareResponse;
import edu.neu.DatastoreService.Learner.LearnerGrpc;
import edu.neu.DatastoreService.Learner.LearnerGrpc.LearnerBlockingStub;
import edu.neu.DatastoreService.Learner.LearnerOuterClass.UpdateRequest;
import edu.neu.DatastoreService.Learner.LearnerOuterClass.UpdateResponse;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;

import edu.neu.DatastoreService.Proposer.ProposerOuterClass.ConsensusRequest;
import edu.neu.DatastoreService.Proposer.ProposerOuterClass.ConsensusResponse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Proposer extends ProposerGrpc.ProposerImplBase {
    private static final Logger log = Logger.getLogger("PROPOSER");
    private final String NODE_ID;
    private List<AcceptorBlockingStub> acceptorStubs;
    private List<LearnerBlockingStub> learnerStubs;

    public Proposer(String nodeId, List<String> serverHostnames, List<Integer> serverPorts) {
        this.NODE_ID = nodeId;

        // Create acceptor stubs
        this.acceptorStubs = IntStream
                .range(0, serverHostnames.size())
                .mapToObj(i -> AcceptorGrpc
                        .newBlockingStub(ManagedChannelBuilder
                                .forAddress(serverHostnames.get(i), serverPorts.get(i))
                                .usePlaintext()
                                .build()))
                .collect(Collectors.toList());

        // Create learner stubs
        this.learnerStubs = IntStream
                .range(0, serverHostnames.size())
                .mapToObj(i -> LearnerGrpc
                        .newBlockingStub(ManagedChannelBuilder
                                .forAddress(serverHostnames.get(i), serverPorts.get(i))
                                .usePlaintext()
                                .build()))
                .collect(Collectors.toList());
    }

    @Override
    public void getConsensus(ConsensusRequest request, StreamObserver<ConsensusResponse> responseObserver) {
        try {
            // Get value from Client request
            String operation = request.getOperation();
            String key = request.getKey();
            String value = request.getValue();
            log.info(String.format("Received Client request: %s %s %s, initiating Paxos phase 1",
                    operation,
                    key,
                    value));

            // Start Paxos phase 1
            int numPromises = 0;
            String proposalId = "";
            while (numPromises < acceptorStubs.size()/2) {
                // Generate proposalId
                proposalId = createProposalId();

                // Send prepare message to acceptors
                numPromises = sendPrepare(proposalId);
            }
            log.info(String.format("Received %d promises, initiating Paxos phase 2", numPromises));

            ConsensusResponse.Builder responseBuilder = ConsensusResponse.newBuilder();

            // Start Paxos phase 2
            int numAccepts = sendPropose(proposalId, operation, key, value);
            if (numAccepts > acceptorStubs.size()/2) {
                log.info(String.format("Received %d accepts, completing Client request", numPromises));

                // Summarise learner responses
                List<UpdateResponse> updateResults = notifyLearners(operation, key, value);
                UpdateResponse majorityResult = compareUpdateResults(updateResults);

                // Generate Client response
                if (majorityResult == null) {
                    responseBuilder
                            .setCode(500)
                            .setMessage("Internal Server error");

                } else {
                    responseBuilder
                            .setCode(majorityResult.getCode())
                            .setMessage(majorityResult.getMessage())
                            .setValue(majorityResult.getValue());
                }
            } else {
                // Generate response
                responseBuilder
                        .setCode(405)
                        .setMessage("Request cannot be completed at this time");
            }
            // Send response to Client
            responseObserver.onNext(responseBuilder.build());
            responseObserver.onCompleted();
        } catch (io.grpc.StatusRuntimeException e) {
            if (e.getStatus().getCode() == Status.Code.CANCELLED) {
                log.warning("Context cancelled due to Client error");
            }
        } catch (Exception e) {
            e.printStackTrace();
            //log.warning(e.getMessage());
        }
    }

    private UpdateResponse compareUpdateResults(List<UpdateResponse> updateResults) {
        // Create a list of distinct responses
        List<UpdateResponse> distinctResponses = updateResults
                .stream()
                .distinct()
                .collect(Collectors.toList());

        if (distinctResponses.size() == 1) {
            // Datastores are all exact replicas
            return distinctResponses.get(0);
        } else if (1 < distinctResponses.size() && distinctResponses.size() < updateResults.size()) {
            // Get majority response
            Map<UpdateResponse, Long> responseCounts =
                    updateResults
                            .stream()
                            .collect(Collectors.groupingBy(e -> e, Collectors.counting()));
            return Collections
                    .max(responseCounts.entrySet(), Map.Entry.comparingByValue())
                    .getKey();
        } else {
            return null;
        }
    }

    private String createProposalId() {
        String proposalId = NODE_ID + "." + System.nanoTime();
        log.info("Generated proposalId: " + proposalId);
        return proposalId;
    }

    private int sendPrepare(String proposalId) {
        // Generate PrepareRequest
        PrepareRequest.Builder prepareRequestBuilder = PrepareRequest
                .newBuilder()
                .setProposalId(proposalId);

        // Send PrepareRequest to all Acceptors
        log.info("Sending prepare message to all Acceptors");
        int promiseCounter = 0;
        for (AcceptorBlockingStub stub : acceptorStubs) {
            PrepareResponse prepareResponse = stub
                    .withDeadlineAfter(2, TimeUnit.MINUTES)
                    .getPromise(prepareRequestBuilder.build());
            if (prepareResponse.getCode() == 200) {
                promiseCounter++;
            }
        }
        return promiseCounter;
    }

    private int sendPropose(String proposalId, String operation, String key, String value) {
        // Generate ProposeRequest
        ProposeRequest.Builder proposeRequestBuilder = ProposeRequest
                .newBuilder()
                .setProposalId(proposalId)
                .setOperation(operation)
                .setKey(key)
                .setValue(value);

        // Send ProposeRequest to all Acceptors
        log.info("Sending propose message to all Acceptors");
        int acceptCounter = 0;
        for (AcceptorBlockingStub stub : acceptorStubs) {
            ProposeResponse proposeResponse = stub
                    .withDeadlineAfter(2, TimeUnit.MINUTES)
                    .getAccept(proposeRequestBuilder.build());
            if (proposeResponse.getCode() == 200) {
                acceptCounter++;
            }
        }
        return acceptCounter;
    }

    private List<UpdateResponse> notifyLearners(String operation, String key, String value) {
        // Generate UpdateRequest
        UpdateRequest.Builder updateRequestBuilder = UpdateRequest
                .newBuilder()
                .setOperation(operation)
                .setKey(key)
                .setValue(value);

        // Send UpdateRequest to all Learners
        List <UpdateResponse> updateResponses = new ArrayList<>();
        for (LearnerBlockingStub stub : learnerStubs) {
            UpdateResponse updateResponse = stub
                    .withDeadlineAfter(2, TimeUnit.MINUTES)
                    .updateDatastore(updateRequestBuilder.build());
            updateResponses.add(updateResponse);
        }
        return updateResponses;
    }
}
