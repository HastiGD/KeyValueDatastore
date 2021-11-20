package edu.neu.CoordinatorService;

import edu.neu.DatastoreService.DatastoreServiceOuterClass;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

class OperateRequestStreamObserverTest {

    static OperateRequestStreamObserver streamObserver;
    static Map<String, DatastoreServiceOuterClass.DatastoreResponse> updatesMade = new HashMap<>();

    @BeforeEach
    void setUp() {
        DatastoreServiceOuterClass.DatastoreResponse operateResponse =
                DatastoreServiceOuterClass
                        .DatastoreResponse
                        .newBuilder()
                        .setCode(200)
                        .setMessage("OK")
                        .setValue("")
                        .build();

        IntStream stream = IntStream.range(0,5);
        stream.forEach(i -> updatesMade.put(String.valueOf(i), operateResponse));

        streamObserver = new OperateRequestStreamObserver(null, null);
    }

    @Test
    @DisplayName("Detecting different OperateResponses should work")
    void verifyUpdates() {

        Map<String, DatastoreServiceOuterClass.DatastoreResponse> expectedMap = new HashMap<>();

        DatastoreServiceOuterClass.DatastoreResponse operateResponse =
                DatastoreServiceOuterClass
                        .DatastoreResponse
                        .newBuilder()
                        .setCode(200)
                        .setMessage("OK")
                        .setValue("")
                        .build();

        expectedMap.put(String.valueOf(0), operateResponse);

        DatastoreServiceOuterClass.DatastoreResponse differentOperateResponse =
                DatastoreServiceOuterClass
                        .DatastoreResponse
                        .newBuilder()
                        .setCode(405)
                        .setMessage("Key already exists")
                        .setValue("TestValue")
                        .build();

        updatesMade.put(String.valueOf(5), differentOperateResponse);
        expectedMap.put(String.valueOf(5), differentOperateResponse);

        DatastoreServiceOuterClass.DatastoreResponse anotherDifferentOperateResponse =
                DatastoreServiceOuterClass
                        .DatastoreResponse
                        .newBuilder()
                        .setCode(404)
                        .setMessage("Key not found")
                        .setValue("")
                        .build();

        updatesMade.put(String.valueOf(6), anotherDifferentOperateResponse);
        expectedMap.put(String.valueOf(6), anotherDifferentOperateResponse);

        assertEquals(expectedMap, streamObserver.verifyUpdates("", "", "", updatesMade));
    }
}