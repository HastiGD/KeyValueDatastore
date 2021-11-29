package edu.neu.DatastoreService.Acceptor;

import edu.neu.DatastoreService.Proposal;
import io.grpc.stub.StreamObserver;
import edu.neu.DatastoreService.Acceptor.AcceptorOuterClass.PrepareResponse;
import edu.neu.DatastoreService.Acceptor.AcceptorOuterClass.PrepareRequest;

import java.util.logging.Logger;

public class PrepareRequestStreamObserver implements StreamObserver<PrepareRequest> {
    private final Logger log = Logger.getLogger( "ACCEPTOR");
    private final StreamObserver<PrepareResponse> responseStreamObserver;
    private Proposal maxProposal;

    public PrepareRequestStreamObserver(StreamObserver<PrepareResponse> responseStreamObserver) {
        this.responseStreamObserver = responseStreamObserver;
        this.maxProposal = new Proposal();
    }

    @Override
    public void onNext(PrepareRequest prepareRequest) {
        String proposalId = prepareRequest.getProposalId();
        log.info(String.format("Received prepare message with proposalId: %s", proposalId));

        // Build response
        PrepareResponse.Builder prepareResponseBuilder = PrepareResponse.newBuilder();

        // Compare with current maxProposalId
        if (maxProposal.updateProposalId(proposalId)) {
            maxProposal.setProposalId(proposalId);
            prepareResponseBuilder.setCode(200).setMessage("Promise Granted");
        } else {
            prepareResponseBuilder.setCode(405).setMessage("Promise denied");
        }

        // Send promise message back to server
        log.info(String.format("Sending promise to Proposer Response Code: %d Response Message: %s",
                prepareResponseBuilder.getCode(),
                prepareResponseBuilder.getMessage()));
        this.responseStreamObserver.onNext(prepareResponseBuilder.build());
    }


    @Override
    public void onError(Throwable throwable) {
        log.warning("Encountered error: " + throwable.getMessage());
    }

    @Override
    public void onCompleted() {
        log.info("Closing response stream");
        this.responseStreamObserver.onCompleted();
    }
}
