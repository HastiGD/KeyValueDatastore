package edu.neu.DatastoreService.Proposer;

import io.grpc.stub.StreamObserver;
import edu.neu.DatastoreService.Acceptor.AcceptorOuterClass.PrepareRequest;
import edu.neu.DatastoreService.Acceptor.AcceptorOuterClass.PrepareResponse;

public class PrepareResponseStreamObserver implements StreamObserver<PrepareResponse> {
    private StreamObserver<PrepareRequest> requestStreamObserver;

    @Override
    public void onNext(PrepareResponse prepareResponse) {

    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void onCompleted() {

    }
}
