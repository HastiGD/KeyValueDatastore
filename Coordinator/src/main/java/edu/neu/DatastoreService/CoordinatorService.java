package edu.neu.DatastoreService;

import io.grpc.stub.StreamObserver;
import edu.neu.DatastoreService.CoordinatorServiceOuterClass.OperateResponse;
import edu.neu.DatastoreService.CoordinatorServiceOuterClass.OperateRequest;
import edu.neu.DatastoreService.DatastoreServiceOuterClass.Request;
import edu.neu.DatastoreService.DatastoreServiceOuterClass.PutRequest;
import edu.neu.DatastoreService.DatastoreServiceOuterClass.DeleteRequest;
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

        log.info(String.format("Received OperateRequest from Server: %s %s %s", operation, key, value));

        // Ask servers if it's possible to operate
        log.info("Confirming Datastore availability with all Servers");
        boolean operate = datastoreAvailable(operation, key, value);

        // Generate response
        OperateResponse.Builder operateResponse = OperateResponse.newBuilder();
        if (operate) {
            log.info("OperateRequest confirmed");

            operateResponse.setResponseCode(200)
                    .setResponseText("OK");
        } else {
            log.info("OperateRequest denied");
            operateResponse.setResponseCode(405)
                    .setResponseText("Method Not allowed");
        }

        // Send response to server
        log.info("Sending APIResponse to Server Response Code: " +
                operateResponse.getResponseCode() +
                " Response Message: " +
                operateResponse.getResponseText());

        responseObserver.onNext(operateResponse.build());
        responseObserver.onCompleted();

        // Complete request on all servers if allowed
        if (operate) {
            completeRequest(operation, key, value);
        }
    }

    private boolean datastoreAvailable(String operation, String key, String value) {
        // Create request
        Request request = Request.newBuilder()
                .setOperation(operation)
                .setKey(key).setValue(value)
                .build();
        log.info(String.format("Confirming Request with Servers: %s %s %s", operation, key, value));

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
        log.info("All Datastores available");
        return true;
    }

    private APIResponse completeRequest(String operation, String key, String value) {
        // Create request and store all responses
        List<APIResponse> responses = null;
        switch (operation) {
            case "PUT":
                PutRequest putRequest = PutRequest.newBuilder().setKey(key).setValue(value).build();
                log.info(String.format("Completing PutRequest on all Servers: %s %s %s", operation, key, value));
                for (DatastoreServiceBlockingStub stub : datastoreStubs) {
                    APIResponse response = stub.coordinatorPut(putRequest);
                    responses.add(response);
                }
                break;
            case "DELETE":
                DeleteRequest deleteRequest = DeleteRequest.newBuilder().setKey(key).build();
                log.info(String.format("Completing DeleteRequest on all Servers: %s %s", operation, key));
                for (DatastoreServiceBlockingStub stub : datastoreStubs) {
                    APIResponse response = stub.coordinatorDelete(deleteRequest);
                    responses.add(response);
                }
                break;
            default:
                break;
        }

        // Iterate through responses and make sure all servers completed request
        APIResponse response = null;
        for (int i = 0; i < responses.size(); i++) {
            if (i == 0) {
                response = responses.get(i);
            } else {
                if (!responses.get(i).equals(response)) {
                    // TODO handle case where one server didn't complete request properly
                    log.info("One server failed to update Datastore");
                    response = responses.get(i);
                }
            }
        }
        return response;
    }
}
