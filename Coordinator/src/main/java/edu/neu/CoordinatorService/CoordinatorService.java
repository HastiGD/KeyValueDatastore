package edu.neu.CoordinatorService;

import edu.neu.DatastoreService.DatastoreServiceGrpc;
import io.grpc.stub.StreamObserver;

import java.util.Map;

public class CoordinatorService extends CoordinatorServiceGrpc.CoordinatorServiceImplBase {
    private final Map<String, DatastoreServiceGrpc.DatastoreServiceBlockingStub> datastoreServiceStubs;

    public CoordinatorService(Map<String, DatastoreServiceGrpc.DatastoreServiceBlockingStub> stubs) {
        this.datastoreServiceStubs = stubs;
    }

    @Override
    public StreamObserver<CoordinatorServiceOuterClass.OperateRequest> operate(StreamObserver<CoordinatorServiceOuterClass.OperateResponse> responseObserver) {
        return new OperateRequestStreamObserver(responseObserver, datastoreServiceStubs);
    }
}
