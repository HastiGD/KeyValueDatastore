package edu.neu.DatastoreService;

import io.grpc.stub.StreamObserver;
import java.util.logging.Logger;

public class DatastoreService extends DatastoreServiceGrpc.DatastoreServiceImplBase {
    private static final Logger log = Logger.getLogger( "SERVER");

    private final CoordinatorServiceGrpc.CoordinatorServiceBlockingStub coordinatorStub;
    private Datastore datastore;

    public DatastoreService(Datastore datastore, CoordinatorServiceGrpc.CoordinatorServiceBlockingStub coordinatorStub) {
        this.datastore = datastore;
        this.coordinatorStub = coordinatorStub;
    }

    @Override
    public void put(DatastoreServiceOuterClass.PutRequest request, StreamObserver<DatastoreServiceOuterClass.APIResponse> responseObserver) {
        // Determine the caller and get key and value from request
        String caller = request.getCaller();
        String key = request.getKey();
        String value = request.getValue();
        log.info(String.format("Request from " + caller + " to: PUT <%s, %s>", key, value));

        // Forward request to Coordinator
        boolean result = requestOperation("PUT", key, value);

        // Generate response
        DatastoreServiceOuterClass.APIResponse.Builder response = DatastoreServiceOuterClass.APIResponse.newBuilder();
        if (result) {
            response.setResponseCode(200).setResponseText("Processing request");
        } else {
            response.setResponseCode(405).setResponseText("Cannot process request");
        }

//        // Add key and value to datastore
//        String result = datastore.put(key, value);
//
//
//        if (result.equals("")) {
//            // Key was added
//            response
//                    .setResponseCode(200)
//                    .setResponseText("OK")
//                    .setValue("");
//        } else {
//            // Key already exists, return value
//            response
//                    .setResponseCode(405)
//                    .setResponseText("Method Not Allowed")
//                    .setValue(result);
//        }

        // Send response to coordinator
        // Send response
        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }

    @Override
    public void get(DatastoreServiceOuterClass.GetRequest request, StreamObserver<DatastoreServiceOuterClass.APIResponse> responseObserver) {
        // Get key from request
        String key = request.getKey();
        log.info(String.format("Request: GET %s", key));

        // Get value from datastore
        String result = datastore.get(key);

        // Generate response
        DatastoreServiceOuterClass.APIResponse.Builder response = DatastoreServiceOuterClass.APIResponse.newBuilder();

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
        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }

    @Override
    public void delete(DatastoreServiceOuterClass.DeleteRequest request, StreamObserver<DatastoreServiceOuterClass.APIResponse> responseObserver) {
        // Determine the caller and get key from request
        String caller = request.getCaller();
        String key = request.getKey();
        log.info(String.format("Request from " + caller + " to: DELETE %s", key));

        // Forward request to Coordinator
        boolean result = requestOperation("DELETE", key, "");

        // Generate response
        DatastoreServiceOuterClass.APIResponse.Builder response = DatastoreServiceOuterClass.APIResponse.newBuilder();
        if (result) {
            response.setResponseCode(200).setResponseText("Processing request");
        } else {
            response.setResponseCode(405).setResponseText("Cannot process request");
        }

//        // Delete key from datastore
//        String result = datastore.delete(key);
//
//        if (result.equals("")) {
//            // Key was not found
//            response
//                    .setResponseCode(404)
//                    .setResponseText("Not Found")
//                    .setValue("");
//        } else {
//            // Key was deleted, return value
//            response
//                    .setResponseCode(200)
//                    .setResponseText("OK")
//                    .setValue(result);
//        }


        // Send response to client
        // Send response
        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }

    private boolean requestOperation(String operation, String key, String value) {
        log.info("Requesting to operate");

        // Send request
        CoordinatorServiceOuterClass.OperateRequest request =
                CoordinatorServiceOuterClass.OperateRequest
                        .newBuilder()
                        .setOperation(operation)
                        .setKey(key)
                        .setValue(value)
                        .build();

        // Get response
        CoordinatorServiceOuterClass.OperateResponse response = coordinatorStub.operate(request);
        int responseCode = response.getResponseCode();
        log.info("Response: " + responseCode + " " + response.getResponseText());

        if (responseCode == 200) {
            return true;
        } else {
            return false;
        }
    }
}
