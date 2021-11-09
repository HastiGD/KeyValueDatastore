package edu.neu.DatastoreCoordinator;

import edu.neu.DatastoreService.CoordinatorService;
import edu.neu.DatastoreService.DatastoreServiceGrpc;
import edu.neu.DatastoreService.DatastoreServiceOuterClass.OperateRequest;
import edu.neu.DatastoreService.DatastoreServiceOuterClass.OperateResponse;
import edu.neu.DatastoreService.DatastoreServiceOuterClass.PutRequest;
import edu.neu.DatastoreService.DatastoreServiceOuterClass.APIResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DatastoreCoordinator {
    private final Logger log = Logger.getLogger( "COORDINATOR");
    private final int port;
    public final Server server;

//    private final ManagedChannel serverChannel;
//    private final String serverHostname;
//    private final int serverPort;
//    private final DatastoreServiceGrpc.DatastoreServiceBlockingStub datastoreStub;

    public DatastoreCoordinator(int port) {
        // Create the service
        CoordinatorService coordinatorService = new CoordinatorService();

        // Bind the server
        this.port = port;
        this.server = ServerBuilder
                .forPort(port)
                .addService(coordinatorService)
                .build();

//        // Establish a channel with server
//        this.serverChannel = ManagedChannelBuilder
//                .forAddress(serverHostname, serverPort)
//                .usePlaintext()
//                .build();
//        log.info("Established channel with " + serverHostname + " on port " + serverPort);
//
//        // Create stubs
//        this.datastoreStub = DatastoreServiceGrpc
//                .newBlockingStub(serverChannel)
//                .withDeadlineAfter(5, TimeUnit.MINUTES);

    }

    public void start() throws IOException {
        server.start();
        log.info("Coordinator started on port " + port);

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                System.err.println("Shut down coordinator because JVM shut down");
                try {
                    DatastoreCoordinator.this.stop();
                } catch (InterruptedException e) {
                    e.printStackTrace(System.err);
                }
                System.err.println("Coordinator shut down");
            }
        });
    }

    public void stop() throws InterruptedException {
        if (server != null) {
            server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
        }
    }

    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    public static void main(String[] args) {
        System.setProperty(
                "java.util.logging.SimpleFormatter.format",
                "%1$tb %1$td, %1$tY %1$tl:%1$tM:%1$tS:%1$tL [%4$-4s]: %5$s %n");
        Logger log = Logger.getLogger( "COORDINATOR");

        try {
            // Get port from args
            int port = Integer.parseInt(args[0]);

            // Create the coordinator
            DatastoreCoordinator coordinator = new DatastoreCoordinator(port);

            // Start the coordinator
            try {
                coordinator.start();
                coordinator.blockUntilShutdown();
            } catch (InterruptedException e) {
                log.info("Coordinator interrupted, system exiting");
                System.exit(1);
            } catch (IOException e) {
                log.info("Coordinator failed, system exiting");
                System.exit(1);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            log.severe("Missing port, system exiting");
            System.exit(0);
        } catch (NumberFormatException e) {
            log.severe("Bad port, system exiting");
            System.exit(0);
        }

//        Map<String, Integer> servers;
//        List<String> hostnames;
//        List<String> ports;
//        DatastoreCoordinator coordinator;
//
//        // Get Servers from arguments
//        if (args.length < 2) {
//            log.severe("Missing arguments, system exiting");
//            System.exit(0);
//        } else {
//            List<String> input = Arrays.asList(args);
//            try {
//                // Get server hostnames
//                hostnames = IntStream
//                        .range(0, input.size())
//                        .filter(n -> n % 2 == 0)
//                        .mapToObj(input::get)
//                        .collect(Collectors.toList());
//
//                // Get server ports
//                ports = IntStream
//                        .range(0, input.size())
//                        .filter(n -> n % 2 != 0)
//                        .mapToObj(input::get)
//                        .collect(Collectors.toList());
//
//                // Store servers
//                servers = IntStream
//                        .range(0, hostnames.size())
//                        .boxed()
//                        .collect(Collectors.toMap(i -> hostnames.get(i) + ports.get(i), i -> Integer.parseInt(ports.get(i))));

                // Connect to Servers

    }
}
