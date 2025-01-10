
package com.binance.connector.client.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import org.json.JSONException;
import org.junit.Test;

public class JSONParser_getJSONIntValueTest {

    private final String mockJson = "{\"key1\": 123, \"key2\": \"value2\"}";

    @Test
    public void testGetJSONIntValueSuccess() {
        int result = JSONParser.getJSONIntValue(mockJson, "key1");
        assertEquals(123, result);
    }

    @Test
    public void testGetJSONIntValueThrowException() {
        assertThrows(JSONException.class, () -> JSONParser.getJSONIntValue(mockJson, "InvalidKey"));
    }

    @Test
    public void testGetJSONIntValueInvalidJson() {
        String invalidJson = "invalid json";
        assertThrows(JSONException.class, () -> JSONParser.getJSONIntValue(invalidJson, "key1"));
    }
}
