package edu.neu.DatastoreService.Proposer;

import io.grpc.stub.StreamObserver;
import edu.neu.DatastoreService.Acceptor.AcceptorOuterClass.ProposeRequest;
import edu.neu.DatastoreService.Acceptor.AcceptorOuterClass.ProposeResponse;

public class ProposeResponseStreamObserver implements StreamObserver<ProposeResponse> {
    private StreamObserver<ProposeRequest> proposeRequestStreamObserver;

    @Override
    public void onNext(ProposeResponse proposeResponse) {

    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void onCompleted() {

    }
}
