package edu.neu.DatastoreServer;

import edu.neu.CoordinatorService.CoordinatorServiceGrpc;
import edu.neu.CoordinatorService.CoordinatorServiceOuterClass.OperateRequest;
import edu.neu.CoordinatorService.OperateResponseStreamObserver;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.util.logging.Logger;

public class Test {
    public static void main(String[] args) {
        Logger log = Logger.getLogger( "SERVER");
        log.info("Building channel for localhost on port 11000");
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 11000)
                .usePlaintext()
                .build();

        CoordinatorServiceGrpc.CoordinatorServiceStub stub = CoordinatorServiceGrpc.newStub(channel);

        OperateResponseStreamObserver operateResponseStreamObserver = new OperateResponseStreamObserver();

        StreamObserver<OperateRequest> requestStreamObserver = stub.operate(operateResponseStreamObserver);
        operateResponseStreamObserver.startStream(requestStreamObserver);

        log.info("Sending 10 OperateRequests");
        for (int i = 0; i < 3; i++) {
            operateResponseStreamObserver.requestOperate("TestOperation", "TestKey", "TestValue", "TestServer");
        }
    }
}
