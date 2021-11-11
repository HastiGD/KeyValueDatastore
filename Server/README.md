# DatastoreServer
## Server side code for the DatastoreService
This package contains the source code, deployment script, and Dockerfile for the server of this app, please complete the instructions at the top-level README before proceeding to this step.

### Project Structure
```bash
DatastoreServer
│
├── deploy.sh
├── Dockerfile
├── pom.xml
├── README.md
├── src/main
│   ├── java/neu/edu
│   │   ├── DatastoreServer
│   │   └── DatastoreService
│   └── resources
│       ├── CoordinatorService.proto
│       └── DatastoreService.proto  
│
└── target
    └── DatastoreServer.jar
```

If you have completed the installation step correctly in the top-level README, you should now see a file called DatastoreServer.jar in the target/ subdirectory, please confirm this file was generated as it is needed to run the server.

### Running the Server in Docker (NOT WORKING)
Once you have installed Docker launch the server by running the deploy.sh script in the **Server** Terminal session:

`Server % ./deploy.sh`

The deploy.sh script has created a network and a Docker image for the server. Next it runs the server in a container. 

### Running the Server locally from the Terminal
In the **Server** Terminal run the following command:

`java -jar target/DatastoreServer.jar <port>`

You should see the following output to Terminal:

`Nov 10, 2021 5:24:30:788 [INFO]: Enter coordinator hostname`

Here you should enter the hostname of the computer that will be running the Coordinator app. You will see the following printed to Terminal

`Nov 10, 2021 5:24:34:363 [INFO]: Server started on port <port>`

You are now ready to launch the Coordinator. Skip ahead to the README in the DatastoreCoordinator subdirectory and follow the instructions under the **Running the Coordinator locally from the Terminal** heading.
