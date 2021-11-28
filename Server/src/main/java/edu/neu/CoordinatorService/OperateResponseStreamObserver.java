package edu.neu.CoordinatorService;

import com.google.common.util.concurrent.Uninterruptibles;
import edu.neu.DatastoreService.DatastoreService;
import edu.neu.DatastoreService.DatastoreServiceOuterClass;
import io.grpc.stub.StreamObserver;
import edu.neu.CoordinatorService.CoordinatorServiceOuterClass.OperateResponse;
import edu.neu.CoordinatorService.CoordinatorServiceOuterClass.OperateRequest;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class OperateResponseStreamObserver implements StreamObserver<OperateResponse> {
    private static final Logger log = Logger.getLogger( "SERVER");
    private StreamObserver<OperateRequest> requestStreamObserver;
    private  DatastoreServiceOuterClass.OperateResponse.Builder response = null;

    @Override
    public void onNext(OperateResponse operateResponse) {
        int code = operateResponse.getCode();
        String message = operateResponse.getMessage();
        String value = operateResponse.getValue();
        this.response = DatastoreServiceOuterClass.OperateResponse
                .newBuilder()
                .setCode(code)
                .setMessage(message)
                .setValue(value);
        log.info(String.format("Received OperateResponse from Coordinator Response Code: %d Response Message: %s %s", code, message, value));
    }

    @Override
    public void onError(Throwable throwable) {
        log.warning("Encountered error: " + throwable.getMessage());
    }

    @Override
    public void onCompleted() {
        log.info("End of OperateResponse stream");
    }

    public void startStream(StreamObserver<OperateRequest> requestStreamObserver) {
        this.requestStreamObserver = requestStreamObserver;
    }

    public DatastoreServiceOuterClass.OperateResponse.Builder getDatastoreResponse() {
        return this.response;
    }
    public OperateRequest requestOperate(String operation, String key, String value, String callerId) {
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        OperateRequest operateRequest = OperateRequest.newBuilder()
                .setOperation(operation)
                .setKey(key)
                .setValue(value)
                .setCallerId(callerId)
                .build();
        log.info(String.format("Forwarding OperateRequest to Coordinator: %s %s %s", operation, key, value));
        requestStreamObserver.onNext(operateRequest);
        return operateRequest;
    }
}
