
package com.binance.connector.client.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import org.json.JSONException;
import org.junit.Test;

public class JSONParser_getJSONStringValueTest {

    private final String validJson = "{\"key\":\"value\"}";
    private final String invalidJson = "invalidJson";

    @Test
    public void testGetJSONStringValueSuccess() {
        String result = JSONParser.getJSONStringValue(validJson, "key");
        assertEquals("value", result);
    }

    @Test
    public void testGetJSONStringValueInvalidKey() {
        assertThrows(JSONException.class, () -> JSONParser.getJSONStringValue(validJson, "invalidKey"));
    }

    @Test
    public void testGetJSONStringValueInvalidJson() {
        assertThrows(JSONException.class, () -> JSONParser.getJSONStringValue(invalidJson, "key"));
    }
}
