# DatastoreService
## A Client-Server App for storing single <key, value> pairs via Remote Procedural Calls implemented with gRPC
### Author: Hasti Gheibi Dehnashi

#### Project Structure
```bash
project_3
│
├── README.md
├── Client
├── Coordinator
└── Server

```
#### Requirements
- Java SDK
- [Maven](http://maven.apache.org/download.html)
- [Docker](https://docs.docker.com/get-docker/) (optional)

#### Installation
1. Download the repository to your desktop and unzip it
2. Open three Terminal session and navigate to the Client directory in one, to the Server directory in the second, and to the Coordinator directory in the third like below:

`Desktop % cd gRPC-Client-Server-master/Server`

`Desktop % cd gRPC-Client-Server-master/Coordinator`

`Desktop % cd gRPC-Client-Server-master/Client`

3. In each Terminal session install the  package with Maven by issuing the following command:

`Server % mvn install clean`

`Coordinator % mvn install clean`

`Client % mvn install clean`

That condludes the steps for installing the packages, continue on to the README's in the Server subdirectory, to run the service.
