
package com.binance.connector.client.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import org.json.JSONException;
import org.junit.Test;

public class JSONParser_getJSONStringValueTest {

    private final String mockJson = "{\"key1\":\"value1\",\"key2\":42}";

    @Test
    public void testGetJSONStringValueValid() {
        String result = JSONParser.getJSONStringValue(mockJson, "key1");
        assertEquals("value1", result);
    }

    @Test
    public void testGetJSONStringValueInvalidKey() {
        JSONException exception = assertThrows(JSONException.class, () -> {
            JSONParser.getJSONStringValue(mockJson, "invalidKey");
        });
        assertEquals("[JSONParser] Failed to get \"invalidKey\" from JSON object", exception.getMessage().trim());
    }

    @Test
    public void testGetJSONStringValueInvalidJson() {
        JSONException exception = assertThrows(JSONException.class, () -> {
            JSONParser.getJSONStringValue("invalidJson", "key1");
        });
        assertEquals("[JSONParser] Failed to get \"key1\" from JSON object", exception.getMessage().trim());
    }
}
