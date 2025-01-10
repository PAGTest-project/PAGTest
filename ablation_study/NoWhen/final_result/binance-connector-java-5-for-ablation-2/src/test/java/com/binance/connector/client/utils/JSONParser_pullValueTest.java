
package com.binance.connector.client.utils;

import org.json.JSONObject;
import org.junit.Test;
import static org.junit.Assert.*;

public class JSONParser_pullValueTest {

    @Test
    public void testPullValueWithExistingKey() {
        JSONObject parameters = new JSONObject();
        parameters.put("key1", "value1");
        Object result = JSONParser.pullValue(parameters, "key1");
        assertEquals("value1", result);
        assertFalse(parameters.has("key1"));
    }

    @Test
    public void testPullValueWithNonExistingKey() {
        JSONObject parameters = new JSONObject();
        parameters.put("key1", "value1");
        Object result = JSONParser.pullValue(parameters, "key2");
        assertNull(result);
        assertTrue(parameters.has("key1"));
    }

    @Test
    public void testPullValueWithNullParameters() {
        JSONObject parameters = null;
        Object result = JSONParser.pullValue(parameters, "key1");
        assertNull(result);
    }

    @Test
    public void testPullValueWithEmptyJSONObject() {
        JSONObject parameters = new JSONObject();
        Object result = JSONParser.pullValue(parameters, "key1");
        assertNull(result);
        assertFalse(parameters.has("key1"));
    }

    @Test
    public void testPullValueWithMultipleKeys() {
        JSONObject parameters = new JSONObject();
        parameters.put("key1", "value1");
        parameters.put("key2", "value2");
        Object result1 = JSONParser.pullValue(parameters, "key1");
        Object result2 = JSONParser.pullValue(parameters, "key2");
        assertEquals("value1", result1);
        assertEquals("value2", result2);
        assertFalse(parameters.has("key1"));
        assertFalse(parameters.has("key2"));
    }
}
