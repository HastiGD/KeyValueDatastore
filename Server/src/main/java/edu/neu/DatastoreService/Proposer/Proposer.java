package edu.neu.DatastoreService.Proposer;

import edu.neu.DatastoreService.Acceptor.AcceptorGrpc;
import edu.neu.DatastoreService.Acceptor.AcceptorGrpc.AcceptorBlockingStub;
import edu.neu.DatastoreService.Acceptor.AcceptorOuterClass.ProposeRequest;
import edu.neu.DatastoreService.Acceptor.AcceptorOuterClass.ProposeResponse;
import edu.neu.DatastoreService.Acceptor.AcceptorOuterClass.PrepareRequest;
import edu.neu.DatastoreService.Acceptor.AcceptorOuterClass.PrepareResponse;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import edu.neu.DatastoreService.Proposer.ProposerOuterClass.ConsensusRequest;
import edu.neu.DatastoreService.Proposer.ProposerOuterClass.ConsensusResponse;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Proposer extends ProposerGrpc.ProposerImplBase {
    private static final Logger log = Logger.getLogger( "PROPOSER");
    private final String NODE_ID;
    private List<AcceptorBlockingStub> acceptorStubs;

    public Proposer(String nodeId, List<String> acceptorHostnames, List<Integer> acceptorPorts) {
        this.NODE_ID = nodeId;

        // Create acceptor stubs
        this.acceptorStubs = IntStream
                .range(0, acceptorHostnames.size())
                .mapToObj((i) -> AcceptorGrpc
                        .newBlockingStub(ManagedChannelBuilder
                                .forAddress(acceptorHostnames.get(i), acceptorPorts.get(i))
                                .usePlaintext()
                                .build())
                        .withDeadlineAfter(30, TimeUnit.SECONDS))
                .collect(Collectors.toList());
    }

    @Override
    public void getConsensus(ConsensusRequest request, StreamObserver<ConsensusResponse> responseObserver) {
        // Get value from Client request
        String operation = request.getOperation();
        String key = request.getKey();
        String value = request.getValue();
        log.info(String.format("Received request from Client: %s %s %s, initiating Paxos phase 1",
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

        // Start Paxos phase 2
        int numAccepts = sendPropose(proposalId, operation, key, value);
        log.info(String.format("Received %d promises, initiating Paxos phase 2", numPromises));

        // Generate response
        ConsensusResponse.Builder responseBuilder = ConsensusResponse.newBuilder();
        responseBuilder
                .setCode(200)
                .setMessage("Test working");

        // Send response
        responseObserver.onNext(responseBuilder.build());
        responseObserver.onCompleted();
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
        AtomicInteger promiseCounter = new AtomicInteger();
        acceptorStubs.forEach(stub -> {
            PrepareResponse prepareResponse = stub.getPromise(prepareRequestBuilder.build());
            if (prepareResponse.getCode() == 200) {
                promiseCounter.getAndIncrement();
            }
        });
        return promiseCounter.get();
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
        AtomicInteger acceptCounter = new AtomicInteger();
        acceptorStubs.forEach(stub -> {
            ProposeResponse proposeResponse = stub.getAccept(proposeRequestBuilder.build());
            if (proposeResponse.getCode() == 200) {
                acceptCounter.getAndIncrement();
            }
        });
        return acceptCounter.get();
    }
}
