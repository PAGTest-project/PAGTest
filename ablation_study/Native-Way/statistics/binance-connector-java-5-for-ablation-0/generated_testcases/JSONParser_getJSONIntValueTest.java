
package com.binance.connector.client.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import org.json.JSONException;
import org.junit.Test;

public class JSONParser_getJSONIntValueTest {

    private final String mockJson = "{\"key\": 42}";

    @Test
    public void testGetJSONIntValueSuccess() {
        int result = JSONParser.getJSONIntValue(mockJson, "key");
        assertEquals(42, result);
    }

    @Test
    public void testGetJSONIntValueThrowException() {
        assertThrows(JSONException.class, () -> JSONParser.getJSONIntValue(mockJson, "InvalidKey"));
    }
}
