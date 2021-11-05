package edu.neu.DatastoreServer;

import edu.neu.DatastoreService.DatastoreService;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;
import java.util.logging.Logger;

public class ServerApp {
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
                log.info("Starting server on port: " + port);

                // Start server
                Server server =
                        ServerBuilder
                                .forPort(port)
                                .addService(new DatastoreService())
                                .build();

                // Start server
                try {
                    server.start();
                    log.info("Server started");

                    // Keep server running until terminated
                    try {
                        server.awaitTermination();
                    } catch (InterruptedException e) {
                        log.severe("Server interrupted, system exiting");
                        System.exit(1);
                    }
                } catch (IOException e) {
                    log.severe("Failed to start server, system exiting");
                    System.exit(1);
                }
            } catch (NumberFormatException e) {
                log.severe("Bad port number, system exiting");
                System.exit(0);
            }
        }
    }
}
