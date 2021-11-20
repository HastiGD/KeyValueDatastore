package edu.neu.DatastoreCoordinator;

import edu.neu.CoordinatorService.CoordinatorService;
import edu.neu.DatastoreService.DatastoreServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Server;
import io.grpc.ServerBuilder;

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
    private final Server server;
    private final List<String> datastoreServerHostnames;
    private final List<Integer> datastoreServerPorts;
    private final Map<String, DatastoreServiceGrpc.DatastoreServiceBlockingStub> stubs;

    public DatastoreCoordinator(int port, List<String> serverHostnames, List<Integer> serverPorts) {
        this.port = port;
        this.datastoreServerHostnames = serverHostnames;
        this.datastoreServerPorts = serverPorts;
        this.stubs = generateDatastoreServiceStubs();

        // Create CoordinatorService
        CoordinatorService coordinatorService = new CoordinatorService(stubs);

        // Build gRPC server and bind CoordinatorService
        this.server = ServerBuilder
                .forPort(port)
                .addService(coordinatorService)
                .build();
    }

    private Map<String, DatastoreServiceGrpc.DatastoreServiceBlockingStub> generateDatastoreServiceStubs() {
        // Generate stubs for the servers
        return IntStream
                .range(0, datastoreServerHostnames.size())
                .boxed()
                .collect(Collectors
                        .toMap(i -> String.valueOf(datastoreServerPorts.get(i)),
                                i -> createDatastoreServiceStub(datastoreServerHostnames.get(i), datastoreServerPorts.get(i))));

    }

    private DatastoreServiceGrpc.DatastoreServiceBlockingStub createDatastoreServiceStub(String hostname, Integer port) {
        ManagedChannel channel =
                ManagedChannelBuilder
                        .forAddress(hostname, port)
                        .usePlaintext()
                        .build();
        return DatastoreServiceGrpc
                .newBlockingStub(channel);
    }

    public void start() throws IOException {
        server.start();
        log.info("Coordinator started on port " + port);

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                System.err.println("Shut down Coordinator server because JVM shut down");
                try {
                    DatastoreCoordinator.this.stop();
                } catch (InterruptedException e) {
                    e.printStackTrace(System.err);
                }
                System.err.println("Coordinator shut down");
            }
        });
    }

    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    public void stop() throws InterruptedException {
        if (server != null) {
            server
                    .shutdown()
                    .awaitTermination(30, TimeUnit.SECONDS);
        }
    }

    public static void main(String[] args) {
        System.setProperty(
                "java.util.logging.SimpleFormatter.format",
                "%1$tb %1$td, %1$tY %1$tl:%1$tM:%1$tS:%1$tL [%4$-4s]: %5$s %n");
        Logger log = Logger.getLogger( "COORDINATOR");

        // Get port from arguments
        if (args.length < 1) {
            log.severe("Missing port number, system exiting");
            System.exit(0);
        } else {
            try {
                int port = Integer.parseInt(args[0]);

                // Get servers from arguments
                List<String> input = Arrays.asList(Arrays.copyOfRange(args, 1, args.length));
                if (input.size() < 2) {
                    log.severe("Missing servers, system exiting");
                    System.exit(0);
                } else {
                    // Get the server hostnames and ports Map<String, stubs>
                    List<String> hostnames;
                    List<Integer> ports;
                    Map<String, Integer> servers;

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
                            .map(Integer::parseInt)
                            .collect(Collectors.toList());

                    try {
                        // Create the coordinator
                        DatastoreCoordinator coordinator = new DatastoreCoordinator(port, hostnames, ports);
                        coordinator.start();
                        coordinator.blockUntilShutdown();
                    } catch (IOException e) {
                        e.printStackTrace();
                        System.exit(1);
                    } catch (InterruptedException e) {
                        log.severe("Coordinator interrupted: " + e.getMessage());
                        System.exit(1);
                    }
                }
            } catch (NumberFormatException e) {
                log.severe("Bad port, system exiting");
                System.exit(0);
            }
        }
    }
}
