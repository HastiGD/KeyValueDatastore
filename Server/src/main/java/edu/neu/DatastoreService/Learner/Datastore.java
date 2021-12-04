package edu.neu.DatastoreService.Learner;

import java.util.HashMap;
import java.util.Map;

public class Datastore {
    private Map<String, String> datastore = new HashMap<String, String>();

    /**
     * Adds a new key to the datastore and returns null
     * If key exists returns the current value
     * @param key a unique String
     * @param value the String associated with the key
     * @return null or value of existing key
     */
    public String put(String key, String value) {
        String result = datastore.putIfAbsent(key, value);
        if (result == null) {
            result = "";
        }
        return result;
    }

    /**
     * Returns the value associated with the key
     * If key does not exist returns null
     * @param key the value associated with this key is returned
     * @return null or value of existing key
     */
    public String get(String key) {
        String result = datastore.get(key);
        if (result == null) {
            result = "";
        }
        return result;
    }

    /**
     * Deletes the record associated with the key
     * @param key the key of the record to delete
     * @return null or value of key to delete
     */
    public String delete(String key) {
        String result = datastore.remove(key);
        if (result == null) {
            result = "";
        }
        return result;
    }
}
