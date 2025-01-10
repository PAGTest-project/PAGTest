
package com.binance.connector.client.utils;

import org.json.JSONObject;
import org.junit.Test;
import static org.junit.Assert.*;

public class JSONParser_addKeyValueTest {

    @Test
    public void testAddKeyValueWithNullParameters() {
        JSONObject result = JSONParser.addKeyValue(null, "key1", "value1");
        assertEquals("value1", result.get("key1"));
    }

    @Test
    public void testAddKeyValueWithExistingParameters() {
        JSONObject parameters = new JSONObject();
        parameters.put("key2", "value2");
        JSONObject result = JSONParser.addKeyValue(parameters, "key1", "value1");
        assertEquals("value1", result.get("key1"));
        assertEquals("value2", result.get("key2"));
    }

    @Test
    public void testAddKeyValueWithNullValue() {
        JSONObject parameters = new JSONObject();
        JSONObject result = JSONParser.addKeyValue(parameters, "key1", null);
        assertNull(result.opt("key1"));
    }

    @Test
    public void testAddKeyValueWithExistingKey() {
        JSONObject parameters = new JSONObject();
        parameters.put("key1", "oldValue");
        JSONObject result = JSONParser.addKeyValue(parameters, "key1", "newValue");
        assertEquals("newValue", result.get("key1"));
    }
}
