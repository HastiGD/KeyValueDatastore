package edu.neu.DatastoreService;

import io.grpc.stub.StreamObserver;
import edu.neu.DatastoreService.ProposerOuterClass.ConsensusResponse;
import edu.neu.DatastoreService.ProposerOuterClass.ConsensusRequest;

import java.util.logging.Logger;

public class Proposer extends ProposerGrpc.ProposerImplBase {
    private static final Logger log = Logger.getLogger( "PROPOSER");
    private final String NODE_ID;
    private Datastore datastore;

    public Proposer(String nodeId, Datastore datastore) {
        this.NODE_ID = nodeId;
        this.datastore = datastore;
    }

    private String createProposalId() {
        String proposalId = NODE_ID + "." + System.nanoTime();
        log.info("Generated proposalId: " + proposalId);
        return proposalId;
    }

    private void sendPrepare(String proposalId) {
        log.info("Sent prepare message to Acceptors");
        // TODO handle promises
    }

    @Override
    public void getConsensus(ConsensusRequest request, StreamObserver<ConsensusResponse> responseObserver) {
        // Get value from Client request
        String operation = request.getOperation();
        String key = request.getKey();
        String value = request.getValue();
        log.info(String.format("Received request from Client: %s %s %s", operation, key, value));

        // Generate proposalId
        String proposalId = createProposalId();

        // Send prepare message to acceptors
        sendPrepare(proposalId);

        // Generate response
        ConsensusResponse.Builder responseBuilder = ConsensusResponse.newBuilder();
        responseBuilder
                .setCode(200)
                .setMessage("Test working");

        // Send response
        responseObserver.onNext(responseBuilder.build());
        responseObserver.onCompleted();
    }
}
