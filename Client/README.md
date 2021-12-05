# DatastoreClient
## Client side code for the DatastoreService
This package contains the source code, deployment script, and Dockerfile for clients of this app, please complete the instructions at the top-level README, and the DatastoreServer README before proceeding to this step.

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
│   │   │   └── Client
│   │   └── DatastoreService
│   └── resources
│       └── Proposer.proto  
│
└── target
    └── DatastoreClient.jar
```

### Running the Client locally from the Terminal
Open up a new Terminal session and cd into the DatastoreClient directory. Then run the following command:

`java -jar target/DatastoreClient.jar localhost 11000`

You may connect to either of the Server instances, doesn't have to be the one on port 11000 specifically.

You should see the following output to Terminal:

`Dec 04, 2021 3:43:30:984 [INFO]: Connecting to localhost on port 11000`

The Client will immediately begin to run a test to demonstrate the API. You are now ready to begin making requests to the Server.

Enter requests as an operation followed by key and, if necessary, a value all separated by spaces. For example Enter:

`put white black`

Produces the following response:

`Dec 04, 2021 3:43:31:018 [INFO]: Request: PUT <White, Black>`

`Dec 04, 2021 3:43:32:001 [INFO]: Response Code: 200 Response Message: OK`

When you are finished you may enter 'close' to exit the Client.

### Running the Client in Docker (NOT WORKING)
Once you have installed Docker launch the server by running the deploy.sh script in the **Server** Terminal session.

Then launch the coordinator by running the run_coordinator.sh script in the **Coordinator** Terminal session.

Finally, run the run_client.sh script in the **Client** Terminal session:

`Client % ./run_client.sh`
