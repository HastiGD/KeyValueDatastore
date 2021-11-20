package edu.neu.DatastoreService;

import edu.neu.CoordinatorService.CoordinatorServiceGrpc;
import edu.neu.CoordinatorService.CoordinatorServiceGrpc.CoordinatorServiceStub;
import edu.neu.CoordinatorService.CoordinatorServiceOuterClass;
import edu.neu.CoordinatorService.OperateResponseStreamObserver;
import edu.neu.DatastoreService.DatastoreServiceOuterClass.OperateRequest;
import edu.neu.DatastoreService.DatastoreServiceOuterClass.OperateResponse;
import io.grpc.ManagedChannel;
import io.grpc.stub.StreamObserver;
import java.util.logging.Logger;

public class DatastoreService extends DatastoreServiceGrpc.DatastoreServiceImplBase {
    private final Logger log = Logger.getLogger( "SERVER");

    private final String serverId;
    private Datastore datastore;
    private final CoordinatorServiceStub stub;
    private final OperateResponseStreamObserver operateResponseStreamObserver;

    public DatastoreService(String serverId, Datastore datastore, ManagedChannel channel) {
        this. serverId = serverId;
        this.datastore = datastore;

        // Create CoordinatorService stub
        this.stub = CoordinatorServiceGrpc.newStub(channel);

        // Create the coordinator response stream observer
        this.operateResponseStreamObserver = new OperateResponseStreamObserver();
        StreamObserver<CoordinatorServiceOuterClass.OperateRequest> requestStreamObserver = stub.operate(operateResponseStreamObserver);
        operateResponseStreamObserver.startStream(requestStreamObserver);
    }

    @Override
    public void operate(OperateRequest request, StreamObserver<OperateResponse> responseObserver) {
        // Get request from client
        String operation = request.getOperation();
        String key = request.getKey();
        String value = request.getValue();
        log.info(String.format("Received OperateRequest from Client: %s %s %s", operation, key, value));

        // Forward request to coordinator
        operateResponseStreamObserver.requestOperate(operation, key, value, serverId);

        // TODO Get DatastoreResponse from coordinator and forward result to client

        // Send response to client
        DatastoreServiceOuterClass.OperateResponse.Builder response =
                DatastoreServiceOuterClass.OperateResponse.newBuilder();
        response
                .setCode(200)
                .setMessage("OK");

        log.info(String.format("Sending OperateResponse to Client Response Code: %d Response Message: %s", response.getCode(), response.getMessage()));
        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }

    @Override
    public void available(OperateRequest request, StreamObserver<OperateResponse> responseObserver) {
        // Get request from coordinator
        String operation = request.getOperation();
        String key = request.getKey();
        String value = request.getValue();
        log.info(String.format("Coordinator requesting Datastore availability for OperateRequest: %s %s %s", operation, key, value));

        // Check datastore availability
        boolean operate = datastoreAvailable();

        // Generate response
        DatastoreServiceOuterClass.OperateResponse.Builder response =
                DatastoreServiceOuterClass.OperateResponse.newBuilder();
        if (operate) {
            response
                    .setCode(200)
                    .setMessage("OK")
                    .setResponderId(serverId);
        } else {
            response.
                    setCode(405)
                    .setMessage("Method Not Allowed")
                    .setResponderId(serverId);
        }

        // Send response to coordinator
        log.info(String.format("Sending availability to Coordinator Response Code: %d Response Message: %s", response.getCode(), response.getMessage()));
        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }

    @Override
    public void update(DatastoreServiceOuterClass.DatastoreRequest request, StreamObserver<DatastoreServiceOuterClass.DatastoreResponse> responseObserver) {
        // Get request from coordinator
        String operation = request.getOperation();
        String key = request.getKey();
        String value = request.getValue();
        log.info(String.format("Received DatastoreRequest from Coordinator: %s %s %s", operation, key, value));

        // Complete update to datastore
        String result = "";
        int responseCode = 200;
        String responseMessage = "OK";
        String responseValue = "";
        switch (operation) {
            case "PUT":
                log.info(String.format("Updating Datastore: %s %s %s", operation, key, value));
                result = datastore.put(key, value);
                if (!result.equals("")) {
                    responseCode = 405;
                    responseMessage = "Key already exists";
                    responseValue = result;
                }
                break;
            case "GET":
                log.info(String.format("Updating Datastore: %s %s %s", operation, key, value));
                result = datastore.get(key);
                if (result.equals("")) {
                    responseCode = 404;
                    responseMessage = "Key not found";
                } else {
                    responseValue = result;
                }
                break;
            case "DELETE":
                log.info(String.format("Updating Datastore: %s %s %s", operation, key, value));
                result = datastore.delete(key);
                if (result.equals("")) {
                    responseCode = 404;
                    responseMessage = "Key not found";
                } else {
                    responseValue = result;
                }
                break;
            default:
                log.info("Invalid operation");
                responseCode = 400;
                responseMessage = "Bad request";
                break;
        }

        // Send response to Coordinator
        log.info(String.format("Sending DatastoreResponse to Coordinator Response Code: %d Response message: %s", responseCode, responseMessage));
        DatastoreServiceOuterClass.DatastoreResponse.Builder response =
                DatastoreServiceOuterClass.DatastoreResponse.newBuilder();
        response
                .setCode(responseCode)
                .setMessage(responseMessage)
                .setValue(responseValue);
        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }

    private boolean datastoreAvailable() {
        // TODO implement lock check
        return true;
    }
}
