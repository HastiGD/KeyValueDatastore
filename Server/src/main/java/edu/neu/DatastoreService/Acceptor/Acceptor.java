package edu.neu.DatastoreService.Acceptor;

import edu.neu.DatastoreService.Datastore;
import io.grpc.stub.StreamObserver;
import edu.neu.DatastoreService.Acceptor.AcceptorOuterClass.PrepareRequest;
import edu.neu.DatastoreService.Acceptor.AcceptorOuterClass.PrepareResponse;
import edu.neu.DatastoreService.Acceptor.AcceptorOuterClass.ProposeRequest;
import edu.neu.DatastoreService.Acceptor.AcceptorOuterClass.ProposeResponse;

import java.util.logging.Logger;

public class Acceptor extends AcceptorGrpc.AcceptorImplBase {
    private static final Logger log = Logger.getLogger( "ACCEPTOR");
    private Datastore datastore;
    private Proposal maxProposal;

    public Acceptor(Datastore datastore) {
        this.datastore = datastore;
        this.maxProposal = new Proposal();
    }

    @Override
    public void getPromise(PrepareRequest request, StreamObserver<PrepareResponse> responseObserver) {
        String proposalId = request.getProposalId();
        log.info(String.format("Received prepare message with proposalId: %s", proposalId));

        // Build response
        PrepareResponse.Builder prepareResponseBuilder = PrepareResponse.newBuilder();

        // Compare with current maxProposalId
        if (maxProposal.updateProposalId(proposalId)) {
            // Update maxProposal
            maxProposal.setProposalId(proposalId);

            // Send promise message back to server
            prepareResponseBuilder.setCode(200).setMessage("Promise Granted");
            log.info(String.format("Sending promise to Proposer Response Code: %d Response Message: %s",
                    prepareResponseBuilder.getCode(),
                    prepareResponseBuilder.getMessage()));
            responseObserver.onNext(prepareResponseBuilder.build());
            responseObserver.onCompleted();
        } else {
            // TODO determine what happens here
            log.info("in else statement of getPromise awaiting timeout");
//            prepareResponseBuilder.setCode(405).setMessage("Promise denied");
//            log.info(String.format("Sending promise to Proposer Response Code: %d Response Message: %s",
//                    prepareResponseBuilder.getCode(),
//                    prepareResponseBuilder.getMessage()));
        }
    }

    @Override
    public void getAccept(ProposeRequest request, StreamObserver<ProposeResponse> responseObserver) {
        String proposalId = request.getProposalId();
        log.info(String.format("Received propose message with proposalId: %s", proposalId));

        // Build response
        ProposeResponse.Builder proposeResponseBuilder = ProposeResponse.newBuilder();

        // Compare with current maxProposalId
        if (maxProposal.getProposalId().equals(proposalId)) {
            String operation = request.getOperation();
            String key = request.getKey();
            String value = request.getValue();

            // TODO complete the PUT, GET, or DELETE request
        } else {
            // TODO determine what happens here
            log.info("in else statement of getAccept awaiting timeout");
        }
    }
}
