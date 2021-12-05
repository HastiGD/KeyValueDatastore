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
│   │   │   └── Server
│   │   └── DatastoreService
│   │       ├── Acceptor
│   │       ├── Learner
│   │       └── Proposer
│   └── resources
│       ├── Acceptor.proto
│       ├── Learner.proto
│       └── Proposer.proto  
│
└── target
    └── DatastoreServer.jar
```

### Running the Server locally from the Terminal
**IMPORTANT** - In order to use the DatastoreService you need to run (at least) three separate Server instances. Before starting the Servers you need to know:

- The hostname of each Server
- The port each Server will use to expose its service

For example, if you wish to have three Server instances running on your local computer, you would have the following mapping:

SERVER 1 : localhost 11000

SERVER 2 : localhost 11001

SERVER 3 : localhost 11002

Open up three new Terminal sessions and cd into the DatastoreServer directory. Then in each Terminal run one of the following command:

Terminal 1

`java -jar target/DatastoreServer.jar localhost 11000 localhost 11001 localhost 11002`

Terminal 2

`java -jar target/DatastoreServer.jar localhost 11001 localhost 11002 localhost 11000`

Terminal 3

`java -jar target/DatastoreServer.jar localhost 11002 localhost 11000 localhost 11001`

You should see the following output to each Terminal:

`Dec 04, 2021 3:00:05:922 [INFO]: edu.neu.DatastoreServer.Server start - Server started on port 1100X`

You are now ready to launch the Client. Skip ahead to the README in the DatastoreClient subdirectory and follow the instructions under the **Running the Client locally from the Terminal** heading.

### Running the Server in Docker (NOT WORKING)
Once you have installed Docker launch the server by running the deploy.sh script in the **Server** Terminal session:

`Server % ./deploy.sh`

The deploy.sh script has created a network and a Docker image for the server. Next it runs the server in a container. 