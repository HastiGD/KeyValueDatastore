package edu.neu.DatastoreClient;

import com.google.common.util.concurrent.Uninterruptibles;
import edu.neu.DatastoreService.ProposerGrpc;
import edu.neu.DatastoreService.ProposerGrpc.ProposerBlockingStub;
import edu.neu.DatastoreService.ProposerOuterClass;
import edu.neu.DatastoreService.ProposerOuterClass.ConsensusRequest;
import edu.neu.DatastoreService.ProposerOuterClass.ConsensusResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    public ConsensusResponse sendConsensusRequest(String operation, String key, String value) {
        ConsensusRequest request = ConsensusRequest
                .newBuilder()
                .setOperation(operation)
                .setKey(key)
                .setValue(value)
                .build();

        return stub.getConsensus(request);
    }

    public void testClient() {
        log.info("================================================");
        log.info("-----------------Testing Client-----------------");
        log.info("================================================");
        List<String> keys = Arrays.asList("Good", "Love", "Day", "Friend", "White", "Happy");
        List<String> values = Arrays.asList("Bad", "Hate", "Night", "Foe", "Black", "Sad");

        Map<String, String> keyMap = IntStream
                .range(0, keys.size())
                .boxed()
                .collect(Collectors.toMap(keys::get, values::get));

        // Make a PUT Request to server
        keyMap.forEach((key, value) -> {
            log.info(String.format("Request: PUT <%s, %s>", key, value));
            ConsensusResponse response = sendConsensusRequest("PUT", key, value);
            log.info(String.format("Response Code: %d Response Message: %s",
                    response.getCode(),
                    response.getMessage()));
        });
        // Make a GET Request to server
        keyMap.forEach((key, value) -> {
            log.info(String.format("Request: GET %s", key));
            ConsensusResponse response = sendConsensusRequest("GET", key, "");
            log.info(String.format("Response Code: %d Response Message: %s Response Value: %s",
                    response.getCode(),
                    response.getMessage(),
                    response.getValue()));
        });
        // Make a DELETE Request to server
        keyMap.forEach((key, value) -> {
            log.info(String.format("Request: DELETE %s", key));
            ConsensusResponse response = sendConsensusRequest("DELETE", key, "");
            log.info(String.format("Response Code: %d Response Message: %s Response Value: %s",
                    response.getCode(),
                    response.getMessage(),
                    response.getValue()));
        });
        log.info("================================================");
        log.info("----------------Testing Complete----------------");
        log.info("================================================");
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

            // Demonstrate use of service
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            client.testClient();

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
                    String key = "";
                    String value = "";
                    String displayValue = "";
                    ConsensusResponse response = null;
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
    }
}
