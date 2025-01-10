
package com.binance.connector.client.utils;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class JSONParser_pullValueTest {

    private JSONObject parameters;

    @Before
    public void setUp() {
        parameters = new JSONObject();
        parameters.put("key1", "value1");
        parameters.put("key2", 123);
    }

    @Test
    public void testPullValueExistingKey() {
        Object value = JSONParser.pullValue(parameters, "key1");
        assertEquals("value1", value);
        assertFalse(parameters.has("key1"));
    }

    @Test
    public void testPullValueNonExistingKey() {
        Object value = JSONParser.pullValue(parameters, "key3");
        assertNull(value);
        assertFalse(parameters.has("key3"));
    }

    @Test
    public void testPullValueNullParameters() {
        Object value = JSONParser.pullValue(null, "key1");
        assertNull(value);
    }

    @Test
    public void testPullValueMultipleKeys() {
        Object value1 = JSONParser.pullValue(parameters, "key1");
        Object value2 = JSONParser.pullValue(parameters, "key2");
        assertEquals("value1", value1);
        assertEquals(123, value2);
        assertFalse(parameters.has("key1"));
        assertFalse(parameters.has("key2"));
    }
}
