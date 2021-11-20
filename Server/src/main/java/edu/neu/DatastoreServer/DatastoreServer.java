package edu.neu.DatastoreServer;

import edu.neu.DatastoreService.Datastore;
import edu.neu.DatastoreService.DatastoreService;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class DatastoreServer {
    private static final Logger log = Logger.getLogger( "SERVER");
    private final int port;
    private final Server server;
    private final String serverId;

    public DatastoreServer(int port, Datastore datastore, String coordinatorHostname, int coordinatorPort) {
        this.port = port;
        this.serverId = "Server" + String.valueOf(port);

        // Establish channel with coordinator
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress(coordinatorHostname, coordinatorPort)
                .usePlaintext()
                .build();

        // Create DatastoreService
        DatastoreService datastoreService = new DatastoreService(serverId, datastore, channel);

        // Build gRPC server and bind DatastoreService
        this.server = ServerBuilder
                .forPort(port)
                .addService(datastoreService)
                .build();
    }

    public void start() throws IOException {
        server.start();
        log.info("Server started on port " + port);

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                System.err.println("Shut down Datastore server because JVM shut down");
                try {
                    DatastoreServer.this.stop();
                } catch (InterruptedException e) {
                    e.printStackTrace(System.err);
                }
                System.err.println("Server shut down");
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
        // Create logger
        System.setProperty(
                "java.util.logging.SimpleFormatter.format",
                "%1$tb %1$td, %1$tY %1$tl:%1$tM:%1$tS:%1$tL [%4$-4s]: %5$s %n");
        Logger log = Logger.getLogger( "SERVER");

        if (args.length < 1) {
            log.severe("Missing port number, system exiting");
            System.exit(0);
        } else {
            try {
                // Get port number from args
                int port = Integer.parseInt(args[0]);

                // Get coordinator hostname and port
                Scanner in = new Scanner(System.in);
                log.info("Enter coordinator hostname and port number: <hostname> <port>");
                String coordinatorHostname = in.next();
                int coordinatorPort = in.nextInt();
                try {
                    // Start server
                    Datastore datastore = new Datastore();
                    DatastoreServer server =
                            new DatastoreServer(port, datastore, coordinatorHostname, coordinatorPort);
                    server.start();
                    server.blockUntilShutdown();
                } catch (IOException e) {
                    log.severe("Server failed, system exiting");
                    System.exit(1);
                } catch (InterruptedException e) {
                    log.severe("Server interrupted, system exiting");
                    System.exit(1);
                }
            } catch (NumberFormatException e) {
                log.severe("Bad port number, system exiting");
                System.exit(0);
            }
        }
    }
}
