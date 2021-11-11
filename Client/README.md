# DatastoreClient
## Client side code for the DatastoreService
This package contains the source code, deployment script, and Dockerfile for clients of this app, please complete the instructions at the top-level README, and the DatastoreServer README, and the DatastoreCoordinator README before proceeding to this step.

### Project Structure
```bash
DatastoreClient
│
├── deploy.sh
├── Dockerfile
├── pom.xml
├── README.md
├── src/main
│   ├── java/neu/edu
│   │   ├── DatastoreClient
│   │   └── DatastoreService
│   └── resources
│       └── DatastoreService.proto  
│
└── target
    └── DatastoreClient.jar
```

If you have completed the installation step correctly in the top-level README, you should now see a file called DatastoreClient.jar in the target/ subdirectory, please confirm this file was generated as it is needed to run the client.

### Running the Client in Docker (NOT WORKING)
Once you have installed Docker launch the server by running the deploy.sh script in the **Server** Terminal session.

Then launch the coordinator by running the run_coordinator.sh script in the **Coordinator** Terminal session.

Finally, run the run_client.sh script in the **Client** Terminal session:

`Client % ./run_client.sh`

### Running the Client locally from the Terminal
In the **Client** Terminals run the following command:

`java -jar target/DatastoreClient.jar <server_hostname> <server_port> <server2_hostname>`

You should see the following output to Terminal:

`Nov 10, 2021 5:26:43:271 [INFO]: Client connected to localhost on port <port>`

You are now ready to begin making requests to the Server.
