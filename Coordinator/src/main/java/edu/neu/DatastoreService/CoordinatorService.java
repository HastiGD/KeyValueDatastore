package edu.neu.DatastoreService;

import io.grpc.stub.StreamObserver;
import edu.neu.DatastoreService.CoordinatorServiceOuterClass.OperateResponse;
import edu.neu.DatastoreService.CoordinatorServiceOuterClass.OperateRequest;

import java.util.logging.Logger;

public class CoordinatorService  extends CoordinatorServiceGrpc.CoordinatorServiceImplBase {
    private static final Logger log = Logger.getLogger("COORDINATOR");

    @Override
    public void operate(OperateRequest request, StreamObserver<OperateResponse> responseObserver) {
        // Get operation, key, and value from request
        String operation = request.getOperation();
        String key = request.getKey();
        String value = request.getValue();

        log.info("Received request to operate");

        // Ask servers if it's possible to operate
        boolean operate = datastoreAvailable();

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
    }

    private boolean datastoreAvailable() {
        // TODO query all servers, and see if it's possible to operate on the datastore
        // call RPC method on DatastoreServiceOuterClass.available(PutRequest/DeleteRequest)
        return true;
    }
}
