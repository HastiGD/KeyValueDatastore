package edu.neu.DatastoreCoordinator;

import edu.neu.DatastoreService.DatastoreServiceGrpc;
import edu.neu.DatastoreService.DatastoreServiceOuterClass.OperateRequest;
import edu.neu.DatastoreService.DatastoreServiceOuterClass.OperateResponse;
import edu.neu.DatastoreService.DatastoreServiceOuterClass.PutRequest;
import edu.neu.DatastoreService.DatastoreServiceOuterClass.APIResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DatastoreCoordinator {
    private final Logger log = Logger.getLogger( "COORDINATOR");
    private final ManagedChannel channel;
    private final DatastoreServiceGrpc.DatastoreServiceStub asyncStub;
    private final DatastoreServiceGrpc.DatastoreServiceBlockingStub stub;

    public DatastoreCoordinator(String host, int port) {
        // Establish a channel with server
        channel = ManagedChannelBuilder
                .forAddress(host, port)
                .usePlaintext()
                .build();
        log.info("Establishing channel with " + host + " on port " + port);

        // Create stubs
        asyncStub = DatastoreServiceGrpc.newStub(channel);
        stub = DatastoreServiceGrpc.newBlockingStub(channel).withDeadlineAfter(5, TimeUnit.MINUTES);

    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
        log.info("Channel shutdown");
    }

    public void requestOperation() {
        // Create the response observer for operate requests
        StreamObserver<OperateResponse> responseObserver = new StreamObserver<OperateResponse>() {
            @Override
            public void onNext(OperateResponse operateResponse) {
                log.info("Request to operate accepted: " + operateResponse.getOperate());
            }

            @Override
            public void onError(Throwable throwable) {
                log.severe("Request failed: " + throwable.getMessage());
            }

            @Override
            public void onCompleted() {
                log.info("Request to operate completed");
            }
        };

        // Create the request stream observer for operate requests
        StreamObserver<OperateRequest> requestObserver = asyncStub.operate(responseObserver);

        // Request to operate
        OperateRequest request = OperateRequest.newBuilder().setServerId("coord").build();

        // Send request
        requestObserver.onNext(request);
        log.info("Requesting to operate on servers");

        // End request stream
        //requestObserver.onCompleted();
    }

    public void requestPut(String key, String value) {
        log.info("Requesting PUT <" + key + ", " + value + ">");
        PutRequest request =
                PutRequest.newBuilder().setKey(key).setValue(value).build();

        APIResponse response = stub.put(request);
        log.info("Response: " + response.toString());
    }

    public static void main(String[] args) {
        System.setProperty(
                "java.util.logging.SimpleFormatter.format",
                "%1$tb %1$td, %1$tY %1$tl:%1$tM:%1$tS:%1$tL [%4$-4s]: %5$s %n");
        Logger log = Logger.getLogger( "COORDINATOR");

        Map<String, Integer> servers;
        List<String> hostnames;
        List<String> ports;
        DatastoreCoordinator coordinator;

        // Get Servers from arguments
        if (args.length < 2) {
            log.severe("Missing arguments, system exiting");
            System.exit(0);
        } else {
            List<String> input = Arrays.asList(args);
            try {
                // Get server hostnames
                hostnames = IntStream
                        .range(0, input.size())
                        .filter(n -> n % 2 == 0)
                        .mapToObj(input::get)
                        .collect(Collectors.toList());

                // Get server ports
                ports = IntStream
                        .range(0, input.size())
                        .filter(n -> n % 2 != 0)
                        .mapToObj(input::get)
                        .collect(Collectors.toList());

                // Store servers
                servers = IntStream
                        .range(0, hostnames.size())
                        .boxed()
                        .collect(Collectors.toMap(i -> hostnames.get(i) + ports.get(i), i -> Integer.parseInt(ports.get(i))));

                // Connect to Servers
                coordinator = new DatastoreCoordinator(hostnames.get(0), Integer.parseInt(ports.get(0)));

                //coordinator.requestPut("Test", "123");

                // Request operation
                coordinator.requestOperation();

            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                log.severe("Bad arguments, system exiting");
                System.exit(1);
            }
        }
    }
}
