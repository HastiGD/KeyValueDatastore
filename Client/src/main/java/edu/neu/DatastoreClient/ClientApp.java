package edu.neu.DatastoreClient;

import edu.neu.DatastoreService.ProposerGrpc;
import edu.neu.DatastoreService.ProposerGrpc.ProposerBlockingStub;
import edu.neu.DatastoreService.ProposerOuterClass;
import edu.neu.DatastoreService.ProposerOuterClass.ConsensusRequest;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import java.util.logging.Logger;

public class ClientApp {
    private static final Logger log = Logger.getLogger( "CLIENT");
    private final int port;
    private final String hostname;
    private final ManagedChannel channel;
    private final ProposerBlockingStub stub;

    public ClientApp(int port, String hostname) {
        this.port = port;
        this.hostname = hostname;

        // Create a channel to the server
        channel =
                ManagedChannelBuilder
                        .forAddress(hostname, port)
                        .usePlaintext()
                        .build();

        // Get stub from proto file
        stub = ProposerGrpc
                .newBlockingStub(channel)
                .withDeadlineAfter(10, TimeUnit.MINUTES);
        log.info("Client connecting to " + hostname + " on port " + port);
    }

    public ProposerOuterClass.ConsensusResponse sendConsensusRequest(String operation, String key, String value) {
        ConsensusRequest request = ProposerOuterClass.ConsensusRequest
                .newBuilder()
                .setOperation(operation)
                .setKey(key)
                .setValue(value)
                .build();

        return stub.getConsensus(request);
    }

    public void shutdownChannel() {
        channel.shutdownNow();
    }

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

            // Create Client
            ClientApp client = new ClientApp(port, host);

            // Read input
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
                    String key = "";
                    String value = "";
                    String displayValue = "";
                    ProposerOuterClass.ConsensusResponse response = null;
                    switch (operation) {
                        case "PUT" :
                            key = request[1];
                            value = request[2];
                            log.info(String.format("Request: PUT <%s, %s>", key, value));
                            response = client.sendConsensusRequest(operation, key, value);
                            displayValue = response.getValue().equals("") ? "" :  " Response Value: " + response.getValue();
                            log.info(String.format("Response Code: %d Response Message: %s",
                                    response.getCode(),
                                    response.getMessage())
                                    + displayValue);
                            break;
                        case "GET" :
                            key = request[1];
                            log.info(String.format("Request: GET %s", key));
                            response = client.sendConsensusRequest(operation, key, value);
                            displayValue = response.getValue().equals("") ? "" :  " Response Value: " + response.getValue();
                            log.info(String.format("Response Code: %d Response Message: %s",
                                    response.getCode(),
                                    response.getMessage())
                                    + displayValue);
                            break;
                        case "DELETE" :
                            key = request[1];
                            log.info(String.format("Request: DELETE %s", key));
                            response = client.sendConsensusRequest(operation, key, value);
                            displayValue = response.getValue().equals("") ? "" :  " Response Value: " + response.getValue();
                            log.info(String.format("Response Code: %d Response Message: %s",
                                    response.getCode(),
                                    response.getMessage())
                                    + displayValue);
                            break;
                        case "CLOSE" :
                            log.info("Closing client");
                            exit = true;
                            break;
                        default :
                            log.warning("Invalid operation");
                            break;
                    }
                } catch (IOException e) {
                    log.warning("Failed to read input");
                } catch (ArrayIndexOutOfBoundsException e) {
                    log.warning("Missing input");
                } catch (StatusRuntimeException e) {
                    log.warning("Connection timed out, system exiting");
                    exit = true;
                    System.exit(0);
                }
            }
            client.shutdownChannel();
        }
//        // Demonstrate how to operate on Datastore
//        String[] keys = {"Good", "Love", "Night", "Friend", "Half-Full", "White"};
//        String[] values = {"Bad", "Hate", "Day", "Foe", "Half-Empty", "Black"};
//
//        log.info("Testing Datastore operations");
//
//        for (int i = 0; i < keys.length; i++) {
//            // Make put Request
//            DatastoreServiceOuterClass.PutRequest putRequest =
//                    DatastoreServiceOuterClass
//                            .PutRequest
//                            .newBuilder()
//                            .setCaller("CLIENT")
//                            .setKey(keys[i])
//                            .setValue(values[i])
//                            .build();
//            log.info(String.format("Request: PUT <%s, %s>", keys[i], values[i]));
//
//            // Read response
//            DatastoreServiceOuterClass.APIResponse putResponse = stub.put(putRequest);
//            log.info("Response: "+ Integer.toString(putResponse.getResponseCode())+" "+putResponse.getResponseText()+" "+putResponse.getValue());
//
//            // Make get request
//            DatastoreServiceOuterClass.GetRequest getRequest =
//                    DatastoreServiceOuterClass
//                            .GetRequest.newBuilder()
//                            .setKey(keys[i])
//                            .build();
//            log.info(String.format("Request: GET %s", keys[i]));
//
//            // Read response
//            DatastoreServiceOuterClass.APIResponse getResponse = stub.get(getRequest);
//            log.info("Response: "+Integer.toString(getResponse.getResponseCode())+" "+getResponse.getResponseText()+" "+getResponse.getValue());
//
//            // Make delete request
//            DatastoreServiceOuterClass.DeleteRequest deleteRequest =
//                    DatastoreServiceOuterClass
//                            .DeleteRequest
//                            .newBuilder()
//                            .setKey(keys[i])
//                            .build();
//            log.info(String.format("Request: DELETE %s", keys[i]));
//
//            // Read response
//            DatastoreServiceOuterClass.APIResponse deleteResponse = stub.delete(deleteRequest);
//            log.info("Response: "+Integer.toString(deleteResponse.getResponseCode())+" "+deleteResponse.getResponseText()+" "+deleteResponse.getValue());
//        }
//
//        log.info("Finished Test");

        // Read input from terminal





        // Close client
    }
}
