# DatastoreCoordinator
## Coordinator code for the DatastoreService
This package contains the source code, deployment script, and Dockerfile for the coordinator for servers of this app, please complete the instructions at the top-level README, and the DatastoreServer README before proceeding to this step.

### Project Structure
```bash
DatastoreCoordinator
│
├── deploy.sh
├── Dockerfile
├── pom.xml
├── README.md
├── src/main
│   ├── java/neu/edu
│   │   ├── DatastoreCoordinator
│   │   └── DatastoreService
│   └── resources
│       ├── CoordinatorService.proto
│       └── DatastoreService.proto  
│
└── target
    └── DatastoreCoordinator.jar
```

If you have completed the installation step correctly in the top-level README, you should now see a file called DatastoreCoordinator.jar in the target/ subdirectory, please confirm this file was generated as it is needed to run the coordinator.

### Running the Coordinator in Docker (NOT WORKING)
Once you have installed Docker launch the server by running the deploy.sh script in the **Server** Terminal.

Then run the run_coordinator.sh script in the **Coordinator** Terminal:

`Coordinator % ./run_coordinator.sh`

### Running the Coordinator locally from the Terminal
The coordinator app runs on port 9090 by default, please make sure this port is available before continuing.

In the **Coordinator** Terminal run the following command:

`java -jar target/DatastoreCoordinator.jar <server1_hostname> <server1_port> <server2_hostname> <server2_port> ... <serverN_hostname> <serverN_port>`

For each server you are running, you must provide its hostname followed by port number separated by spaces.

You should see the following output to Terminal:

`Nov 10, 2021 5:25:19:506 [INFO]: Establishing channel with server <host> on port <port>`

`Nov 10, 2021 5:25:19:929 [INFO]: Establishing channel with server <host> on port <port>`

`Nov 10, 2021 5:25:20:112 [INFO]: Coordinator started on port 9090`

You are now ready to launch the Clients. Skip ahead to the README in the DatastoreClient subdirectory and follow the instructions under the **Running the Client locally from the Terminal** heading.
