
package com.binance.connector.client.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import org.json.JSONException;
import org.json.JSONObject;
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
        assertEquals("[JSONParser] Failed to get \"invalidKey\" from JSON object", exception.getMessage());
    }

    @Test
    public void testGetJSONStringValueInvalidJson() {
        String invalidJson = "invalidJson";
        JSONException exception = assertThrows(JSONException.class, () -> {
            JSONParser.getJSONStringValue(invalidJson, "key1");
        });
        assertEquals("[JSONParser] Failed to get \"key1\" from JSON object", exception.getMessage());
    }

    @Test
    public void testGetJSONStringValueAfterAddingKey() {
        JSONObject parameters = new JSONObject();
        JSONParser.addKeyValue(parameters, "newKey", "newValue");
        String jsonString = JSONParser.buildJSONString("1", "method", parameters);
        String result = JSONParser.getJSONStringValue(jsonString, "newKey");
        assertEquals("newValue", result);
    }

    @Test
    public void testGetJSONStringValueAfterPullingKey() {
        JSONObject parameters = new JSONObject(mockJson);
        JSONParser.pullValue(parameters, "key1");
        JSONException exception = assertThrows(JSONException.class, () -> {
            JSONParser.getJSONStringValue(parameters.toString(), "key1");
        });
        assertEquals("[JSONParser] Failed to get \"key1\" from JSON object", exception.getMessage());
    }
}
