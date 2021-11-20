package edu.neu.CoordinatorService;

import edu.neu.CoordinatorService.CoordinatorServiceOuterClass.OperateRequest;
import edu.neu.CoordinatorService.CoordinatorServiceOuterClass.OperateResponse;
import edu.neu.DatastoreService.DatastoreServiceGrpc;
import edu.neu.DatastoreService.DatastoreServiceOuterClass;
import io.grpc.stub.StreamObserver;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class OperateRequestStreamObserver implements StreamObserver<OperateRequest> {
    private final Logger log = Logger.getLogger( "COORDINATOR");
    private final StreamObserver<OperateResponse> responseStreamObserver;
    private final Map<String, DatastoreServiceGrpc.DatastoreServiceBlockingStub> stubs;

    public OperateRequestStreamObserver(StreamObserver<OperateResponse> responseStreamObserver,
                                        Map<String, DatastoreServiceGrpc.DatastoreServiceBlockingStub> stubs) {
        this.responseStreamObserver = responseStreamObserver;
        this.stubs = stubs;
    }

    @Override
    public void onNext(OperateRequest operateRequest) {
        String operation = operateRequest.getOperation();
        String key = operateRequest.getKey();
        String value = operateRequest.getValue();
        log.info(String.format("Received OperateRequest from Server: %s %s %s", operation, key, value));

        // Query servers to see if their datastore is available
        log.info("Checking Datastore availability across all Servers");
        Map<String, Boolean> isAvailable = requestAvailable(operation, key, value);

        // Check for any unavailable datastores, if found abort operation
        OperateResponse.Builder abortOperateResponse = OperateResponse.newBuilder();
        isAvailable.forEach((server, available) -> {
            if (!available) {
                log.info(String.format("Server %s Datastore unavailable", server));
                abortOperateResponse
                        .setCode(405)
                        .setMessage(String.format("Server %s Datastore unavailable", server));
                log.info(String.format("Sending OperateResponse to Server Response Code: %d Response Message: %s",
                        abortOperateResponse.getCode(),
                        abortOperateResponse.getMessage()));
                this.responseStreamObserver.onNext(abortOperateResponse.build());
                return; // TODO check if really works
            }
        });
        // All datastores available
        log.info("Datastores available across all Servers");

        // Update all datastores
        Map<String, DatastoreServiceOuterClass.DatastoreResponse> updatesMade = updateDatastores(operation, key, value);

        // Build response
        OperateResponse operateResponse = verifyUpdates(operation, key, value, updatesMade);

        // Send response back to server
        log.info(String.format("Sending OperateResponse to Server Response Code: %d Response Message: %s %s",
                operateResponse.getCode(),
                operateResponse.getMessage(),
                operateResponse.getValue()));
        this.responseStreamObserver.onNext(operateResponse);
    }

    public OperateResponse verifyUpdates(String operation, String key, String value, Map<String, DatastoreServiceOuterClass.DatastoreResponse> updatesMade) {
        OperateResponse.Builder operateResponse = OperateResponse.newBuilder();

        // Count number of distinct DatastoreResponses
        Set<DatastoreServiceOuterClass.DatastoreResponse> set = new HashSet<>();
        Map<String, DatastoreServiceOuterClass.DatastoreResponse> distinctResponsesMap =
                updatesMade
                        .entrySet()
                        .stream()
                        .filter((entry -> set.add(entry.getValue())))
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        // Use distinct DatastoreResponses to determine if a server didn't update its Datastore
        if (distinctResponsesMap.size() > 1) {
            distinctResponsesMap.forEach((server, response) -> {
                // TODO handle different responses Datastores that were changed need to rollback
                log.info(String.format("Server %s Datastore did not update", server));
            });
            operateResponse
                    .setCode(405)
                    .setMessage("Server Datastore did not update");
        } else {
            // All Datastores updated
            log.info("All Datastores updated");
            DatastoreServiceOuterClass.DatastoreResponse firstResponse =
                    updatesMade
                            .entrySet()
                            .iterator()
                            .next()
                            .getValue();
            operateResponse
                    .setCode(firstResponse.getCode())
                    .setValue(firstResponse.getValue())
                    .setMessage(firstResponse.getMessage());
        }
        return operateResponse.build();
    }

    private Map<String, Boolean> requestAvailable(String operation, String key, String value) {
        Map<String, Boolean> availabilityMap = new HashMap<>();

        stubs.forEach((server, stub) -> {
            DatastoreServiceOuterClass.OperateRequest request = DatastoreServiceOuterClass.OperateRequest.newBuilder()
                    .setOperation(operation)
                    .setKey(key)
                    .setValue(value)
                    .build();
            DatastoreServiceOuterClass.OperateResponse response = stub.available(request);
            if (response.getCode() == 200) {
                availabilityMap.put(server, true);
            } else {
                availabilityMap.put(server, false);
            }
        });
        return availabilityMap;
    }

    private Map<String, DatastoreServiceOuterClass.DatastoreResponse> updateDatastores(String operation, String key, String value) {
        log.info("Updating Datastores across all Servers");
        Map<String, DatastoreServiceOuterClass.DatastoreResponse> datastoreResponseMap = new HashMap<>();

        stubs.forEach((server, stub) ->  {
            DatastoreServiceOuterClass.DatastoreRequest request = DatastoreServiceOuterClass.DatastoreRequest.newBuilder()
                    .setOperation(operation)
                    .setKey(key)
                    .setValue(value)
                    .build();
            DatastoreServiceOuterClass.DatastoreResponse response = stub.update(request);
            datastoreResponseMap.put(server, response);
            if (response.getCode() != 200) {
                log.info(String.format("Server %s Datastore did not update Reason: %s", server, response.getMessage()));
            }
        });
        return datastoreResponseMap;
    }
    @Override
    public void onError(Throwable throwable) {
        log.warning("Encountered error: " + throwable.getMessage());
    }

    @Override
    public void onCompleted() {
        this.responseStreamObserver.onCompleted();
        log.info("End of OperateRequests stream");
    }
}
