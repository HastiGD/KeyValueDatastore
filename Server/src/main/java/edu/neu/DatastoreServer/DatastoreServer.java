package edu.neu.DatastoreServer;

import edu.neu.DatastoreService.*;
import edu.neu.DatastoreService.Proposer.Proposer;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class DatastoreServer {
    private static final Logger log = Logger.getLogger( "SERVER");
    private final int port;
    private final Server server;
//    private final String coordinatorHostname;
//    private final int coordinatorPort;
//    private final ManagedChannel coordinatorChannel;
//    private final CoordinatorServiceGrpc.CoordinatorServiceBlockingStub coordinatorStub;

    public DatastoreServer(int port, Datastore datastore) {
//        // Establish a channel with the coordinator
//        this.coordinatorHostname = coordinatorHostname;
//        this.coordinatorPort = coordinatorPort;
//        this.coordinatorChannel = ManagedChannelBuilder
//                .forAddress(coordinatorHostname, coordinatorPort)
//                .usePlaintext()
//                .build();
//
//        // Create stubs
//        this.coordinatorStub = CoordinatorServiceGrpc
//                .newBlockingStub(coordinatorChannel);

        // Create the service
        //DatastoreService datastoreService = new DatastoreService(datastore, coordinatorStub);
        Proposer proposer = new Proposer(String.valueOf(port), datastore);

        // Bind the server
        this.port = port;
        this.server = ServerBuilder
                .forPort(port)
                .addService(proposer)
                .build();
    }

    public void start() throws IOException {
        server.start();
        log.info("Server started on port " + port);

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                System.err.println("Shut down server because JVM shut down");
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
                // Get port from args
                int port = Integer.parseInt(args[0]);

                // Read coordinator info from terminal
                //DataInputStream input = new DataInputStream(System.in);
                //String[] coordinatorInfo = null;

                    //log.info("Enter coordinator hostname");
                    //coordinatorInfo = input.readLine().split(" ");
                    //String coordinatorHostname = coordinatorInfo[0];
                    //int coordinatorPort = 9090;

                    // Create datastore
                Datastore datastore = new Datastore();

                // Start server
                try {
                    DatastoreServer server =
                            new DatastoreServer(port, datastore);
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
