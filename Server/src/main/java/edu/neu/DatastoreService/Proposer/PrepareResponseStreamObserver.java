package edu.neu.DatastoreService.Proposer;

import io.grpc.stub.StreamObserver;
import edu.neu.DatastoreService.Acceptor.AcceptorOuterClass.PrepareRequest;
import edu.neu.DatastoreService.Acceptor.AcceptorOuterClass.PrepareResponse;

import java.util.logging.Logger;

public class PrepareResponseStreamObserver implements StreamObserver<PrepareResponse> {
    private static final Logger log = Logger.getLogger( "PROPOSER");
    private StreamObserver<PrepareRequest> prepareRequestStreamObserver;

    @Override
    public void onNext(PrepareResponse prepareResponse) {
        //Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        log.info("onNext of PrepareResponseStreamObserver");
    }

    @Override
    public void onError(Throwable throwable) {
        log.info("onError of PrepareResponseStreamObserver");
    }

    @Override
    public void onCompleted() {
        log.info("onCompleted of PrepareResponseStreamObserver");
    }

    public void startStream(StreamObserver<PrepareRequest> prepareRequestStreamObserver) {
        this.prepareRequestStreamObserver = prepareRequestStreamObserver;
        log.info("startStream of PrepareResponseStreamObserver");
    }
}
