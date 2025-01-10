
package com.binance.connector.client.utils;

import org.json.JSONObject;
import org.junit.Test;
import static org.junit.Assert.*;

public class JSONParser_pullValueTest {

    @Test
    public void testPullValueWithNullParameters() {
        assertNull(JSONParser.pullValue(null, "key"));
    }

    @Test
    public void testPullValueWithExistingKey() {
        JSONObject parameters = new JSONObject();
        parameters.put("key", "value");
        assertEquals("value", JSONParser.pullValue(parameters, "key"));
        assertFalse(parameters.has("key"));
    }

    @Test
    public void testPullValueWithNonExistingKey() {
        JSONObject parameters = new JSONObject();
        assertNull(JSONParser.pullValue(parameters, "nonExistingKey"));
        assertFalse(parameters.has("nonExistingKey"));
    }

    @Test
    public void testPullValueWithMultipleKeys() {
        JSONObject parameters = new JSONObject();
        parameters.put("key1", "value1");
        parameters.put("key2", "value2");
        assertEquals("value1", JSONParser.pullValue(parameters, "key1"));
        assertFalse(parameters.has("key1"));
        assertTrue(parameters.has("key2"));
    }
}
