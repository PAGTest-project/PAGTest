
package com.binance.connector.client.utils;

import org.json.JSONObject;
import org.junit.Test;
import static org.junit.Assert.*;

public class JSONParser_addKeyValueTest {

    @Test
    public void testAddKeyValueWithNullParameters() {
        JSONObject result = JSONParser.addKeyValue(null, "key", "value");
        assertEquals("value", result.get("key"));
    }

    @Test
    public void testAddKeyValueWithExistingParameters() {
        JSONObject parameters = new JSONObject();
        parameters.put("existingKey", "existingValue");
        JSONObject result = JSONParser.addKeyValue(parameters, "newKey", "newValue");
        assertEquals("existingValue", result.get("existingKey"));
        assertEquals("newValue", result.get("newKey"));
    }

    @Test
    public void testAddKeyValueWithNullValue() {
        JSONObject parameters = new JSONObject();
        JSONObject result = JSONParser.addKeyValue(parameters, "key", null);
        assertNull(result.get("key"));
    }

    @Test
    public void testAddKeyValueWithExistingKey() {
        JSONObject parameters = new JSONObject();
        parameters.put("key", "oldValue");
        JSONObject result = JSONParser.addKeyValue(parameters, "key", "newValue");
        assertEquals("newValue", result.get("key"));
    }
}
