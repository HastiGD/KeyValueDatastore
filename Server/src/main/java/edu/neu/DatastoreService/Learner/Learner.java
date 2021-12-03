package edu.neu.DatastoreService.Learner;

import io.grpc.stub.StreamObserver;

import java.util.logging.Logger;

public class Learner extends LearnerGrpc.LearnerImplBase {
    private static final Logger log = Logger.getLogger( "LEARNER");
    private Datastore datastore;

    public Learner(Datastore datastore) {
        this.datastore = datastore;
    }

    @Override
    public void updateDatastore(LearnerOuterClass.UpdateRequest request, StreamObserver<LearnerOuterClass.UpdateResponse> responseObserver) {
        // TODO complete the PUT, GET, or DELETE request
    }
}
