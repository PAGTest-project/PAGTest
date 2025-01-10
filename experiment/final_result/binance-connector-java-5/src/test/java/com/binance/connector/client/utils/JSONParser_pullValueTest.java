
package com.binance.connector.client.utils;

import org.json.JSONObject;
import org.junit.Test;
import static org.junit.Assert.*;

public class JSONParser_pullValueTest {

    @Test
    public void testPullValue_WithExistingKey() {
        JSONObject parameters = new JSONObject();
        parameters.put("key1", "value1");

        Object result = JSONParser.pullValue(parameters, "key1");

        assertEquals("value1", result);
        assertFalse(parameters.has("key1"));
    }

    @Test
    public void testPullValue_WithNonExistingKey() {
        JSONObject parameters = new JSONObject();

        Object result = JSONParser.pullValue(parameters, "key1");

        assertNull(result);
        assertFalse(parameters.has("key1"));
    }

    @Test
    public void testPullValue_WithNullParameters() {
        Object result = JSONParser.pullValue(null, "key1");

        assertNull(result);
    }
}
