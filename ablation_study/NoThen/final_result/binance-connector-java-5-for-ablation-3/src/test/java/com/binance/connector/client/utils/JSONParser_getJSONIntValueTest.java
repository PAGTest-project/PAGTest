
package com.binance.connector.client.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import org.json.JSONException;
import org.junit.Test;

public class JSONParser_getJSONIntValueTest {

    private final String mockJson = "{\"key1\":42, \"key2\":\"value2\"}";

    @Test
    public void testGetJSONIntValueSuccess() {
        assertEquals(42, JSONParser.getJSONIntValue(mockJson, "key1"));
    }

    @Test
    public void testGetJSONIntValueMissingKey() {
        assertThrows(JSONException.class, () -> JSONParser.getJSONIntValue(mockJson, "missingKey"));
    }

    @Test
    public void testGetJSONIntValueInvalidJson() {
        String invalidJson = "{invalidJson}";
        assertThrows(JSONException.class, () -> JSONParser.getJSONIntValue(invalidJson, "key1"));
    }

    @Test
    public void testGetJSONIntValueNonIntValue() {
        assertThrows(JSONException.class, () -> JSONParser.getJSONIntValue(mockJson, "key2"));
    }
}
