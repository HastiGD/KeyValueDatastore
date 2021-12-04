package edu.neu.DatastoreService.Learner;

import io.grpc.stub.StreamObserver;
import edu.neu.DatastoreService.Learner.LearnerOuterClass.UpdateResponse;
import edu.neu.DatastoreService.Learner.LearnerOuterClass.UpdateRequest;
import java.util.logging.Logger;

public class Learner extends LearnerGrpc.LearnerImplBase {
    private static final Logger log = Logger.getLogger( "LEARNER");
    private Datastore datastore;

    public Learner(Datastore datastore) {
        this.datastore = datastore;
    }

    @Override
    public void updateDatastore(UpdateRequest request, StreamObserver<UpdateResponse> responseObserver) {
        // Get operation, key, and value from request
        String operation = request.getOperation();
        String key = request.getKey();
        String value = request.getValue();
        log.info(String.format("Received UpdateRequest: %s %s %s", operation, key, value));

        // Build response
        UpdateResponse.Builder updateResponseBuilder = UpdateResponse.newBuilder();

        // Update datastore
        String returnValue = "";
        switch(operation) {
            case "PUT":
                returnValue = datastore.put(key, value);
                if (returnValue.equals("")) {   // Key was added
                    updateResponseBuilder
                            .setCode(200)
                            .setMessage("OK")
                            .setValue("");
                } else {    // Key already exists, return value
                    updateResponseBuilder
                            .setCode(405)
                            .setMessage("Key already exists with Value: " + returnValue)
                            .setValue(returnValue);
                }
                break;
            case "GET":
                returnValue = datastore.get(key);
                if (returnValue.equals("")) {   // Key was added
                    updateResponseBuilder
                            .setCode(404)
                            .setMessage("Key not found")
                            .setValue("");
                } else {    // Key already exists, return value
                    updateResponseBuilder
                            .setCode(200)
                            .setMessage("OK")
                            .setValue(returnValue);
                }
                break;
            case "DELETE":
                returnValue = datastore.delete(key);
                if (returnValue.equals("")) {   // Key was not found
                    updateResponseBuilder
                            .setCode(404)
                            .setMessage("Key not Found")
                            .setValue("");
                } else {    // Key was deleted, return value
                    updateResponseBuilder
                            .setCode(200)
                            .setMessage("OK")
                            .setValue(returnValue);
                }
                break;
            default:
                break;
        }

        // Send response
        log.info(String.format("Sending UpdateResponse to Proposer Response Code: %d Response Message: %s",
                updateResponseBuilder.getCode(),
                updateResponseBuilder.getMessage()));
        responseObserver.onNext(updateResponseBuilder.build());
        responseObserver.onCompleted();
    }
}
