package edu.neu.DatastoreClient;

import edu.neu.DatastoreService.DatastoreServiceGrpc;
import edu.neu.DatastoreService.DatastoreServiceOuterClass;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import java.util.logging.Logger;

public class ClientApp {
    public static void main(String[] args) {
        // Create logger
        System.setProperty(
                "java.util.logging.SimpleFormatter.format",
                "%1$tb %1$td, %1$tY %1$tl:%1$tM:%1$tS:%1$tL [%4$-4s]: %5$s %n");
        Logger log = Logger.getLogger( "CLIENT");

        // Get hostname and port number from args
        String host = "";
        int port = 0;
        if (args.length < 2) {
            log.severe("Missing arguments, system exiting");
            System.exit(0);
        } else {
            host = args[0];
            port = Integer.parseInt(args[1]);
        }

        // Create a channel to the server
        ManagedChannel channel =
                ManagedChannelBuilder
                        .forAddress(host, port)
                        .usePlaintext()
                        .build();
        log.info("Client connected to " + host + " on port " + port);

        // Get stub from proto file
        DatastoreServiceGrpc.DatastoreServiceBlockingStub stub =
                DatastoreServiceGrpc
                        .newBlockingStub(channel)
                        .withDeadlineAfter(120, TimeUnit.SECONDS);

        // Demonstrate how to operate on Datastore
        String[] keys = {"Good", "Love", "Night", "Friend", "Half-Full", "White"};
        String[] values = {"Bad", "Hate", "Day", "Foe", "Half-Empty", "Black"};

        log.info("Testing Datastore operations");

        for (int i = 0; i < keys.length; i++) {
            // Make put Request
            DatastoreServiceOuterClass.PutRequest putRequest =
                    DatastoreServiceOuterClass
                            .PutRequest
                            .newBuilder()
                            .setCaller("CLIENT")
                            .setKey(keys[i])
                            .setValue(values[i])
                            .build();
            log.info(String.format("Request: PUT <%s, %s>", keys[i], values[i]));

            // Read response
            DatastoreServiceOuterClass.APIResponse putResponse = stub.put(putRequest);
            log.info("Response: "+ Integer.toString(putResponse.getResponseCode())+" "+putResponse.getResponseText()+" "+putResponse.getValue());

            // Make get request
            DatastoreServiceOuterClass.GetRequest getRequest =
                    DatastoreServiceOuterClass
                            .GetRequest.newBuilder()
                            .setKey(keys[i])
                            .build();
            log.info(String.format("Request: GET %s", keys[i]));

            // Read response
            DatastoreServiceOuterClass.APIResponse getResponse = stub.get(getRequest);
            log.info("Response: "+Integer.toString(getResponse.getResponseCode())+" "+getResponse.getResponseText()+" "+getResponse.getValue());

            // Make delete request
            DatastoreServiceOuterClass.DeleteRequest deleteRequest =
                    DatastoreServiceOuterClass
                            .DeleteRequest
                            .newBuilder()
                            .setKey(keys[i])
                            .build();
            log.info(String.format("Request: DELETE %s", keys[i]));

            // Read response
            DatastoreServiceOuterClass.APIResponse deleteResponse = stub.delete(deleteRequest);
            log.info("Response: "+Integer.toString(deleteResponse.getResponseCode())+" "+deleteResponse.getResponseText()+" "+deleteResponse.getValue());
        }

        log.info("Finished Test");

        // Read input from terminal
        DataInputStream input = new DataInputStream(System.in);

        log.info("Enter requests separated by space: <OPERATION> <KEY> <VALUE>");
        log.info("Valid operations: PUT, GET, DELETE");
        log.info("Enter CLOSE to exit program");

        // Ask user for requests
        String[] request = null;
        boolean exit = false;
        while (!exit) {
            log.info("Enter your request: ");
            try {
                // Parse input, send requests, read response
                request = input.readLine().split(" ");
                String operation = request[0].toUpperCase();
                DatastoreServiceOuterClass.APIResponse response = null;
                switch (operation) {
                    case "PUT" :    log.info(String.format("Request: PUT <%s, %s>", request[1], request[2]));
                        DatastoreServiceOuterClass.PutRequest putRequest = DatastoreServiceOuterClass
                                .PutRequest
                                .newBuilder()
                                .setKey(request[1])
                                .setValue(request[2])
                                .build();
                        response = stub.put(putRequest);
                        log.info("Response: "+Integer.toString(response.getResponseCode())+" "+response.getResponseText());
                        break;
                    case "GET" :    log.info(String.format("Request: GET %s", request[1]));
                        DatastoreServiceOuterClass.GetRequest getRequest = DatastoreServiceOuterClass
                                .GetRequest.newBuilder()
                                .setKey(request[1])
                                .build();
                        response = stub.get(getRequest);
                        log.info("Response: "+Integer.toString(response.getResponseCode())+" "+response.getResponseText()+" "+response.getValue());
                        break;
                    case "DELETE" : log.info(String.format("Request: DELETE %s", request[1]));
                        DatastoreServiceOuterClass.DeleteRequest deleteRequest = DatastoreServiceOuterClass
                                .DeleteRequest
                                .newBuilder()
                                .setKey(request[1])
                                .build();
                        response = stub.delete(deleteRequest);
                        log.info("Response: "+Integer.toString(response.getResponseCode())+" "+response.getResponseText());
                        break;
                    case "CLOSE" :  log.info("Closing client");
                        exit = true;
                        break;
                    default :       log.warning("Invalid operation");
                        break;
                }
            } catch (IOException e) {
                log.warning("Failed to read input");
            } catch (ArrayIndexOutOfBoundsException e) {
                log.warning("Missing input");
            } catch (StatusRuntimeException e) {
                log.warning("Connection timed out, system exiting");
                System.exit(0);
            }
        }
        // Close client
        channel.shutdownNow();
    }
}
