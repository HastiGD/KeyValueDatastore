package edu.neu.DatastoreService.Acceptor;

import edu.neu.DatastoreService.Proposal;
import io.grpc.stub.StreamObserver;
import edu.neu.DatastoreService.Acceptor.AcceptorOuterClass.ProposeRequest;
import edu.neu.DatastoreService.Acceptor.AcceptorOuterClass.ProposeResponse;
import java.util.logging.Logger;

public class ProposeRequestStreamObserver implements StreamObserver<ProposeRequest> {
    private final Logger log = Logger.getLogger( "ACCEPTOR");
    private final StreamObserver<ProposeResponse> responseStreamObserver;
    private Proposal maxProposal;

    public ProposeRequestStreamObserver(StreamObserver<ProposeResponse> responseStreamObserver) {
        this.responseStreamObserver = responseStreamObserver;
        this.maxProposal = new Proposal();
    }

    @Override
    public void onNext(ProposeRequest proposeRequest) {
        String proposalId = proposeRequest.getProposalId();
        log.info(String.format("Received accept message with proposalId: %s", proposalId));

        // Build response
        ProposeResponse.Builder proposeResponseBuilder = ProposeResponse.newBuilder();

        // TODO this is problematic because the proposalId is being tracked in two places, the Proposal here will be out ot date
//        // Compare with current maxProposalId
//        if (maxProposal.updateProposalId(proposalId)) {
//            maxProposal.setProposalId(proposalId);
//            proposeResponseBuilder.setCode(200).setMessage("Promise Granted");
//        } else {
//            proposeResponseBuilder.setCode(405).setMessage("Promise denied");
//        }

        // Send promise message back to server
        log.info(String.format("Sending accept to Proposer Response Code: %d Response Message: %s",
                proposeResponseBuilder.getCode(),
                proposeResponseBuilder.getMessage()));
        this.responseStreamObserver.onNext(proposeResponseBuilder.build());
    }

    @Override
    public void onError(Throwable throwable) {
        log.warning("Encountered error: " + throwable.getMessage());
    }

    @Override
    public void onCompleted() {
        this.responseStreamObserver.onCompleted();
    }
}
