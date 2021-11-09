package edu.neu.DatastoreCoordinator;

import edu.neu.DatastoreService.CoordinatorService;
import edu.neu.DatastoreService.DatastoreServiceGrpc;
import edu.neu.DatastoreService.DatastoreServiceGrpc.DatastoreServiceBlockingStub;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DatastoreCoordinator {
    private static final Logger log = Logger.getLogger( "COORDINATOR");
    private static final int port = 9000;
    public final Server coordinatorServer;

    public DatastoreCoordinator(List<String> serverHostnames, List<Integer> serverPorts) {
        // Create DatastoreService stubs
        List<DatastoreServiceBlockingStub> datastoreStubs = IntStream
                .range(0, serverHostnames.size())
                .mapToObj((i) -> ManagedChannelBuilder
                        .forAddress(serverHostnames.get(i), serverPorts.get(i))
                        .usePlaintext()
                        .build())
                .map(DatastoreServiceGrpc::newBlockingStub)
                .collect(Collectors.toList());

        // Create the service
        CoordinatorService coordinatorService = new CoordinatorService(datastoreStubs);

        // Bind the server
        this.coordinatorServer = ServerBuilder
                .forPort(port)
                .addService(coordinatorService)
                .build();
    }

    public void start() throws IOException {
        coordinatorServer.start();
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
        if (coordinatorServer != null) {
            coordinatorServer.shutdown().awaitTermination(30, TimeUnit.SECONDS);
        }
    }

    private void blockUntilShutdown() throws InterruptedException {
        if (coordinatorServer != null) {
            coordinatorServer.awaitTermination();
        }
    }

    public static void main(String[] args) {
        System.setProperty(
                "java.util.logging.SimpleFormatter.format",
                "%1$tb %1$td, %1$tY %1$tl:%1$tM:%1$tS:%1$tL [%4$-4s]: %5$s %n");
        Logger log = Logger.getLogger( "COORDINATOR");

        // Create a list to store server hostnames and ports
        List<String> hostnames;
        List<Integer> ports;

        // Get server hostnames and ports from args
        if (args.length < 2) {
            log.severe("Missing server hostname or port, system exiting");
            System.exit(0);
        } else {
            List<String> input = Arrays.asList(args);
            // Get server hostnames
            hostnames = IntStream
                    .range(0, input.size())
                    .filter(n -> n % 2 == 0)
                    .mapToObj(input::get)
                    .collect(Collectors.toList());

            // Get server ports
            List<String> inputPorts = IntStream
                    .range(0, input.size())
                    .filter(n -> n % 2 != 0)
                    .mapToObj(input::get)
                    .collect(Collectors.toList());

            // Convert inputPorts to Integer
            try {
                ports = inputPorts
                        .stream()
                        .map(Integer::parseInt)
                        .collect(Collectors.toList());

                // Create the coordinator
                DatastoreCoordinator coordinator = new DatastoreCoordinator(hostnames, ports);

                // Start the coordinator
                try {
                    coordinator.start();
                    coordinator.blockUntilShutdown();
                } catch (InterruptedException | IOException e) {
                    log.severe("Coordinator failed, system exiting");
                    log.severe(e.getMessage());
                    System.exit(1);
                }
            } catch (NumberFormatException e) {
                log.severe("Bad port, system exiting");
                System.exit(0);
            }
        }
    }
}
//            // Read server input from console
//            DataInputStream inputStream = new DataInputStream(System.in);
//            log.info(
//                    "Enter the hostname followed by port for each server" +
//                    System.getProperty("line.separator") +
//                    "server1 port1 server2 port2 server3 port3");
//
//            String[] input = null;
//            try {
//                input = inputStream.readLine().split(" ");
//            } catch (IOException e) {
//                log.severe("Failed to read input, system exiting");
//                System.exit(0);
//            }


//
//                // Store servers
//        Map<String, Integer> servers;
//                servers = IntStream
//                        .range(0, hostnames.size())
//                        .boxed()
//                        .collect(Collectors.toMap(i -> hostnames.get(i) + ports.get(i), i -> Integer.parseInt(ports.get(i))));