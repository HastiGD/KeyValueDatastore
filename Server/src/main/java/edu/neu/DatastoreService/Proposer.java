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
        /*
        TODO send PrepareRequest to all Acceptors and count promises
        	if number promises > numAcceptors/2
                tell acceptors to accept proposal
	        else start another round of paxos
         */
        log.info("Sent prepare message to Acceptors");
    }

    private void sendPropose(String proposalId) {
        /*
        TODO send ProposeRequest to all Acceptors and count accepts
            	if number accepts > numAcceptors/2
		        consensus has been reached, update (or read) datastores
         */
        log.info("Sent propose message to Acceptors");
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
