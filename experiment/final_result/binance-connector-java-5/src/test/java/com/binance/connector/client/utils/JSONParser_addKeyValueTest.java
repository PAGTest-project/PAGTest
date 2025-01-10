
package com.binance.connector.client.utils;

import org.json.JSONObject;
import org.junit.Test;
import static org.junit.Assert.*;

public class JSONParser_addKeyValueTest {

    @Test
    public void testAddKeyValue_NullParameters() {
        JSONObject result = JSONParser.addKeyValue(null, "key", "value");
        assertEquals("value", result.get("key"));
    }

    @Test
    public void testAddKeyValue_NonNullParameters() {
        JSONObject parameters = new JSONObject();
        JSONObject result = JSONParser.addKeyValue(parameters, "key", "value");
        assertEquals("value", result.get("key"));
    }
}
