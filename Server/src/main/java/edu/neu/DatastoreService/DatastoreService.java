package edu.neu.DatastoreService;

import edu.neu.DatastoreService.Learner.Datastore;
import io.grpc.stub.StreamObserver;
import java.util.logging.Logger;
import edu.neu.DatastoreService.DatastoreServiceOuterClass.PutRequest;
import edu.neu.DatastoreService.DatastoreServiceOuterClass.DeleteRequest;
import edu.neu.DatastoreService.DatastoreServiceOuterClass.GetRequest;
import edu.neu.DatastoreService.DatastoreServiceOuterClass.Request;
import edu.neu.DatastoreService.DatastoreServiceOuterClass.APIResponse;

public class DatastoreService extends DatastoreServiceGrpc.DatastoreServiceImplBase {
    private static final Logger log = Logger.getLogger( "SERVER");

    //private final CoordinatorServiceGrpc.CoordinatorServiceBlockingStub coordinatorStub;
    private Datastore datastore;

    public DatastoreService(Datastore datastore){ //, CoordinatorServiceGrpc.CoordinatorServiceBlockingStub coordinatorStub) {
        this.datastore = datastore;
        //this.coordinatorStub = coordinatorStub;
    }

    @Override
    public void put(PutRequest request, StreamObserver<APIResponse> responseObserver) {
        // Get key and value from request
        String key = request.getKey();
        String value = request.getValue();
        log.info(String.format("Received PutRequest from Client: PUT %s %s", key, value));

        // Forward request to Coordinator
        log.info("Forwarding PutRequest to Coordinator");
        boolean result = requestOperation("PUT", key, value);

        // Generate response
        APIResponse.Builder response = APIResponse.newBuilder();
        if (result) {
            // Operation was accepted
            response
                    .setResponseCode(200)
                    .setResponseText("OK");
        } else {
            // Operation was denied
            response
                    .setResponseCode(405)
                    .setResponseText("Method Not Allowed");
        }

        // Send response to client
        log.info("Sending APIResponse to Client Response Code: " +
                response.getResponseCode() +
                " Response Message: " +
                response.getResponseText());

        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }

    @Override
    public void coordinatorPut(PutRequest request, StreamObserver<APIResponse> responseObserver) {
        // Get key and value from request
        String key = request.getKey();
        String value = request.getValue();
        log.info(String.format("Received PutRequest from Coordinator: PUT %s %s", key, value));

        // Add key and value to datastore
        String result = datastore.put(key, value);

        // Generate response
        APIResponse.Builder response = APIResponse.newBuilder();
        if (result.equals("")) {
            // Key was added
            response
                    .setResponseCode(200)
                    .setResponseText("OK")
                    .setValue("");
        } else {
            // Key already exists, return value
            response
                    .setResponseCode(405)
                    .setResponseText("Method Not Allowed")
                    .setValue(result);
        }

        // Send response to Coordinator
        log.info("Sending APIResponse to Coordinator Response Code: " +
                response.getResponseCode() +
                " Response Message: " +
                response.getResponseText());

        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }


    @Override
    public void get(GetRequest request, StreamObserver<APIResponse> responseObserver) {
        // Get key from request
        String key = request.getKey();
        log.info(String.format("Received GetRequest from Client: GET %s", key));

        // Get value from datastore
        String result = datastore.get(key);

        // Generate response
        APIResponse.Builder response = APIResponse.newBuilder();

        if (result.equals("")) {
            // Key was not found
            response
                    .setResponseCode(404)
                    .setResponseText("Not Found")
                    .setValue("");
        } else {
            // Key was found, return value
            response
                    .setResponseCode(200)
                    .setResponseText("OK")
                    .setValue(result);
        }

        // Send response to client
        log.info("Sending APIResponse to Client Response Code: " +
                response.getResponseCode() +
                " Response Message: " +
                response.getResponseText());

        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }

    @Override
    public void delete(DeleteRequest request, StreamObserver<APIResponse> responseObserver) {
        // Get key from request
        String key = request.getKey();
        log.info(String.format("Received DeleteRequest from Client: DELETE %s", key));

        // Forward request to Coordinator
        log.info("Forwarding DeleteRequest to Coordinator");
        boolean result = requestOperation("DELETE", key, "");

        // Generate response
        APIResponse.Builder response = APIResponse.newBuilder();
        if (result) {
            // Operation was accepted
            response
                    .setResponseCode(200)
                    .setResponseText("OK");
        } else {
            // Operation cannot be performed at this time
            response
                    .setResponseCode(405)
                    .setResponseText("Method not allowed");
        }

        // Send response to client
        log.info("Sending APIResponse to Client Response Code: " +
                response.getResponseCode() +
                " Response Message: " +
                response.getResponseText());

        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }

    @Override
    public void coordinatorDelete(DeleteRequest request, StreamObserver<APIResponse> responseObserver) {
        // Get key from request
        String key = request.getKey();
        log.info(String.format("Received DeleteRequest from Coordinator: DELETE %s", key));

        // Delete key from datastore
        String result = datastore.delete(key);

        // Generate response
        APIResponse.Builder response = APIResponse.newBuilder();
        if (result.equals("")) {
            // Key was not found
            response
                    .setResponseCode(404)
                    .setResponseText("Not Found")
                    .setValue("");
        } else {
            // Key was deleted, return value
            response
                    .setResponseCode(200)
                    .setResponseText("OK")
                    .setValue(result);
        }

        // Send response to Coordinator
        log.info("Sending APIResponse to Coordinator Response Code: " +
                response.getResponseCode() +
                " Response Message: " +
                response.getResponseText());

        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }

    @Override
    public void available(Request request, StreamObserver<APIResponse> responseObserver) {
        // Get operation, key, and value from request
        String operation = request.getOperation();
        String key = request.getKey();
        String value = request.getValue();
        log.info(String.format("Received Request from Coordinator: %s %s %s", operation, key, value));

        // See if datastore is available for transaction
        log.info("Checking datastore availability");
        boolean available = datastoreAvailable(operation, key, value);

        // Generate response
        APIResponse.Builder response = APIResponse.newBuilder();
        if (available) {
            response
                    .setResponseCode(200)
                    .setResponseText("OK");
        } else {
            response
                    .setResponseCode(405)
                    .setResponseText("Method Not Allowed");
        }

        // Send response
        log.info("Sending APIResponse to Coordinator Response Code: " +
                response.getResponseCode() +
                " Response Message: " +
                response.getResponseText());

        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }

    private boolean requestOperation(String operation, String key, String value) {
        // Send request
        log.info("Sending OperateRequest to Coordinator");
//        OperateRequest request = OperateRequest
//                        .newBuilder()
//                        .setOperation(operation)
//                        .setKey(key)
//                        .setValue(value)
//                        .build();
//
//        // Get response
//        OperateResponse response = coordinatorStub.operate(request);
//        int responseCode = response.getResponseCode();
//        log.info("Received OperateResponse from Coordinator Response Code: " +
//                responseCode +
//                " Response Text: " +
//                response.getResponseText());

        // Send true if ok to operate otherwise false
        //return responseCode == 200;
        return false;
    }

    private boolean datastoreAvailable(String operation, String key, String value) {
        return true;    // TODO check to see if datastore is available
    }
}
