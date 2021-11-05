package edu.neu.DatastoreService;

import io.grpc.stub.StreamObserver;
import java.util.logging.Logger;

public class DatastoreService extends DatastoreServiceGrpc.DatastoreServiceImplBase {
    private Datastore datastore = new Datastore();
    private Logger log = Logger.getLogger( "SERVER");

    @Override
    public void put(DatastoreServiceOuterClass.PutRequest request, StreamObserver<DatastoreServiceOuterClass.APIResponse> responseObserver) {
        // Get key and value from request
        String key = request.getKey();
        String value = request.getValue();
        log.info(String.format("Request: PUT <%s, %s>", key, value));

        // Add key and value to datastore
        String result = datastore.put(key, value);

        // Generate response
        DatastoreServiceOuterClass.APIResponse.Builder response = DatastoreServiceOuterClass.APIResponse.newBuilder();

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
        // Send response to client
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
        // Get key from request
        String key = request.getKey();

        log.info(String.format("Request: DELETE %s", key));

        // Delete key from datastore
        String result = datastore.delete(key);

        // Generate response
        DatastoreServiceOuterClass.APIResponse.Builder response = DatastoreServiceOuterClass.APIResponse.newBuilder();

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
        // Send response to client
        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }
}
