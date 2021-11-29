package edu.neu.DatastoreService;

import io.grpc.stub.StreamObserver;
import edu.neu.DatastoreService.AcceptorOuterClass.PrepareRequest;
import edu.neu.DatastoreService.AcceptorOuterClass.PrepareResponse;
import edu.neu.DatastoreService.AcceptorOuterClass.ProposeRequest;
import edu.neu.DatastoreService.AcceptorOuterClass.ProposeResponse;

import java.util.logging.Logger;

public class Acceptor extends AcceptorGrpc.AcceptorImplBase {
    private static final Logger log = Logger.getLogger( "ACCEPTOR");
    private Proposal maxProposal = new Proposal();

    @Override
    public void getPromise(PrepareRequest request, StreamObserver<PrepareResponse> responseObserver) {
        // Get proposalId
        String proposalId = request.getProposalId();

        PrepareResponse.Builder prepareResponseBuilder = PrepareResponse.newBuilder();

        // Compare with current maxProposalId
        if (maxProposal.updateProposalId(proposalId)) {
            maxProposal.setProposalId(proposalId);
            prepareResponseBuilder.setCode(200).setMessage("Promise Granted");
        } else {
            prepareResponseBuilder.setCode(405).setMessage("Promise denied");
        }
        responseObserver.onNext(prepareResponseBuilder.build());
        responseObserver.onCompleted();
    }

    @Override
    public void getAccept(ProposeRequest request, StreamObserver<ProposeResponse> responseObserver) {
        super.getAccept(request, responseObserver);
    }
}
