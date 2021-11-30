package edu.neu.DatastoreService.Proposer;

import edu.neu.DatastoreService.Acceptor.AcceptorOuterClass;
import io.grpc.stub.StreamObserver;
import edu.neu.DatastoreService.Acceptor.AcceptorOuterClass.ProposeRequest;
import edu.neu.DatastoreService.Acceptor.AcceptorOuterClass.ProposeResponse;

import java.util.logging.Logger;

public class ProposeResponseStreamObserver implements StreamObserver<ProposeResponse> {
    private static final Logger log = Logger.getLogger( "PROPOSER");
    private StreamObserver<ProposeRequest> proposeRequestStreamObserver;

    @Override
    public void onNext(ProposeResponse proposeResponse) {
        //Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        log.info("onNext of ProposeResponseStreamObserver");
    }

    @Override
    public void onError(Throwable throwable) {
        log.info("onError of ProposeResponseStreamObserver");
    }

    @Override
    public void onCompleted() {
        log.info("onCompleted of ProposeResponseStreamObserver");
    }

    public void startStream(StreamObserver<ProposeRequest> proposeRequestStreamObserver) {
        this.proposeRequestStreamObserver = proposeRequestStreamObserver;
        log.info("startStream of ProposeResponseStreamObserver");
    }
}
