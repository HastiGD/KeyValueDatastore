package edu.neu.DatastoreService;

import io.grpc.stub.StreamObserver;
import edu.neu.DatastoreService.AcceptorOuterClass.PrepareRequest;
import edu.neu.DatastoreService.AcceptorOuterClass.PrepareResponse;
import edu.neu.DatastoreService.AcceptorOuterClass.ProposeRequest;
import edu.neu.DatastoreService.AcceptorOuterClass.ProposeResponse;

import java.util.logging.Logger;

public class Acceptor extends AcceptorGrpc.AcceptorImplBase {
    private static final Logger log = Logger.getLogger( "ACCEPTOR");

    @Override
    public StreamObserver<PrepareRequest> getPromise(StreamObserver<PrepareResponse> responseObserver) {
        return new PrepareRequestStreamObserver(responseObserver);
    }

    @Override
    public StreamObserver<ProposeRequest> getAccept(StreamObserver<ProposeResponse> responseObserver) {
        return new ProposeRequestStreamObserver(responseObserver);
    }
}
