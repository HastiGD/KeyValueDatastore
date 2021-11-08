package edu.neu.DatastoreService;

import edu.neu.DatastoreService.DatastoreServiceOuterClass.OperateRequest;
import edu.neu.DatastoreService.DatastoreServiceOuterClass.OperateResponse;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import java.util.logging.Logger;

public class DatastoreService extends DatastoreServiceGrpc.DatastoreServiceImplBase {
    private static final Logger log = Logger.getLogger( "SERVER");

    private Datastore datastore;

    public DatastoreService(Datastore datastore) {
        this.datastore = datastore;
    }

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

    @Override
    public StreamObserver<OperateRequest> operate(StreamObserver<OperateResponse> responseObserver) {
        log.info("DEBUG - IN OPERATE");
        return new StreamObserver<OperateRequest>() {
            @Override
            public void onNext(OperateRequest operateRequest) {
                // Get requesting servers ID
                String serverId = operateRequest.getServerId();
                log.info("Received operate request from " + serverId);

                // Determine if the datastore is operable
//                boolean operate = isOperable();
//                if (!operate) {
//                    responseObserver.onError(
//                                Status.PERMISSION_DENIED
//                                    .withDescription("Operation not allowed")
//                                    .asRuntimeException()
//                    );
//                    return;
//                }

                // Generate response
                OperateResponse operateResponse = OperateResponse
                                .newBuilder()
                                .setOperate(true)
                                .build();

                // Send response
                log.info("Sending response " + operateResponse);
                responseObserver.onNext(operateResponse);
            }

            @Override
            public void onError(Throwable throwable) {
                log.warning(throwable.getMessage());
            }

            @Override
            public void onCompleted() {
                responseObserver.onCompleted();
            }
        };
    }
}
