package edu.neu.DatastoreServer;

import edu.neu.DatastoreService.*;
import edu.neu.DatastoreService.Acceptor.Acceptor;
import edu.neu.DatastoreService.Acceptor.AcceptorGrpc;
import edu.neu.DatastoreService.Acceptor.AcceptorGrpc.AcceptorStub;
import edu.neu.DatastoreService.Proposer.Proposer;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class DatastoreServer {
    private static final Logger log = Logger.getLogger( "SERVER");
    private final int port;
    private final Server server;
    private Map<String, Integer> acceptors;

    public DatastoreServer(int port, Datastore datastore) {
        // Create the Proposer and Acceptor services
        Proposer proposer = new Proposer(String.valueOf(port));
        Acceptor acceptor = new Acceptor(datastore);

        // Bind the server
        this.port = port;
        this.server = ServerBuilder
                .forPort(port)
                .addService(proposer)
                .addService(acceptor)
                .build();
    }

    private ManagedChannel buildChannel(String hostname, int port) {
        return ManagedChannelBuilder.forAddress(hostname, port).usePlaintext().build();
    }

    private AcceptorStub createAcceptorStub(ManagedChannel acceptorChannel) {
        return AcceptorGrpc.newStub(acceptorChannel);
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
                // Get port from args, this Port exposes the proposer
                int port = Integer.parseInt(args[0]);

                // Get Acceptor info from args


                // Create datastore
                Datastore datastore = new Datastore();

                try {
                    // Create server instance
                    DatastoreServer server =
                            new DatastoreServer(port, datastore);

                    // Establish bidirectional streams with Acceptors

                    // Start server
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
