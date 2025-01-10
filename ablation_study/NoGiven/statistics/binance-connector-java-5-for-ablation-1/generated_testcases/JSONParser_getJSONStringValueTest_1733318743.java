
package com.binance.connector.client.utils;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import static org.junit.Assert.*;

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
        assertEquals("[JSONParser] Failed to get \"invalidKey\" from JSON object", exception.getMessage());
    }

    @Test
    public void testGetJSONStringValueInvalidJson() {
        JSONException exception = assertThrows(JSONException.class, () -> {
            JSONParser.getJSONStringValue("invalidJson", "key1");
        });
        assertEquals("[JSONParser] Failed to get \"key1\" from JSON object", exception.getMessage());
    }

    @Test
    public void testGetJSONStringValueFromBuiltJson() {
        JSONObject parameters = new JSONObject();
        parameters.put("key1", "value1");
        String builtJson = JSONParser.buildJSONString("id", "method", parameters);
        String result = JSONParser.getJSONStringValue(builtJson, "key1");
        assertEquals("value1", result);
    }
}
