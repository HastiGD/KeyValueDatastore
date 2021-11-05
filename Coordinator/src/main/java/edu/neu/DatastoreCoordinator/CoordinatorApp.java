package edu.neu.DatastoreCoordinator;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CoordinatorApp {
    public static void main(String[] args) {
        Logger log = Logger.getLogger( "COORDINATOR");
        Map<String, Integer> servers;

        // Get Servers from arguments
        if (args.length < 2) {
            log.severe("Missing arguments, system exiting");
            System.exit(0);
        } else {
            List<String> input = Arrays.asList(args);
            try {
                // Get server hostnames
                List<String> hostnames = IntStream
                        .range(0, input.size())
                        .filter(n -> n % 2 == 0)
                        .mapToObj(input::get)
                        .collect(Collectors.toList());

                // Get server ports
                List<String> ports = IntStream
                        .range(0, input.size())
                        .filter(n -> n % 2 != 0)
                        .mapToObj(input::get)
                        .collect(Collectors.toList());

                // Store servers
                servers = IntStream
                        .range(0, hostnames.size())
                        .boxed()
                        .collect(Collectors.toMap(i -> hostnames.get(i) + ports.get(i), i -> Integer.parseInt(ports.get(i))));
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                log.severe("Bad arguments, system exiting");
                System.exit(1);
            }
        }

        // Create Channels to Servers
    }
}
