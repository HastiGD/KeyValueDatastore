package edu.neu.DatastoreService;

import io.grpc.stub.StreamObserver;
import edu.neu.DatastoreService.CoordinatorServiceOuterClass.OperateResponse;
import edu.neu.DatastoreService.CoordinatorServiceOuterClass.OperateRequest;
import edu.neu.DatastoreService.DatastoreServiceOuterClass.Request;
import edu.neu.DatastoreService.DatastoreServiceOuterClass.APIResponse;
import edu.neu.DatastoreService.DatastoreServiceGrpc.DatastoreServiceBlockingStub;

import java.util.List;
import java.util.logging.Logger;

public class CoordinatorService  extends CoordinatorServiceGrpc.CoordinatorServiceImplBase {
    private static final Logger log = Logger.getLogger("COORDINATOR");

    private final List<DatastoreServiceBlockingStub> datastoreStubs;

    public CoordinatorService(List<DatastoreServiceBlockingStub> datastoreStubs) {
        this.datastoreStubs = datastoreStubs;
    }

    @Override
    public void operate(OperateRequest request, StreamObserver<OperateResponse> responseObserver) {
        // Get operation, key, and value from request
        String operation = request.getOperation();
        String key = request.getKey();
        String value = request.getValue();

        log.info("Received request to operate");

        // Ask servers if it's possible to operate
        boolean operate = datastoreAvailable(operation, key, value);

        // Generate response
        OperateResponse.Builder response = OperateResponse.newBuilder();
        if (operate) {
            log.info("Permission to operate accepted");
            response.setResponseCode(200)
                    .setResponseText("OK");
        } else {
            log.info("Permission to operate denied");
            response.setResponseCode(405)
                    .setResponseText("Method Not allowed");
        }

        // Send response to server
        log.info("Sending response");
        responseObserver.onNext(response.build());
        responseObserver.onCompleted();

        // TODO complete request on all servers
    }

    private boolean datastoreAvailable(String operation, String key, String value) {
        // Create request
        Request request = Request.newBuilder()
                .setOperation(operation)
                .setKey(key).setValue(value)
                .build();
        log.info("Requesting datastore availability on all server");

        // Confirm datastore availability of all servers
        for (DatastoreServiceBlockingStub stub : datastoreStubs) {
            APIResponse response = stub.available(request);
            // If any of the servers datastore is unavailable return false
            if (response.getResponseCode() == 405) {
                log.info("Datastore unavailable");
                return false;
            }
        }
        // Otherwise return true
        log.info("All datastores available");
        return true;
    }
}
