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

    public Acceptor(Datastore datastore) {
        this.datastore = datastore;
    }

    @Override
    public StreamObserver<PrepareRequest> getPromise(StreamObserver<PrepareResponse> responseObserver) {
        return new PrepareRequestStreamObserver(responseObserver);
    }

    @Override
    public StreamObserver<ProposeRequest> getAccept(StreamObserver<ProposeResponse> responseObserver) {
        return new ProposeRequestStreamObserver(responseObserver);
    }
}
