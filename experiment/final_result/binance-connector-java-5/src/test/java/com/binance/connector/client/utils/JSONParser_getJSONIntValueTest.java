
package com.binance.connector.client.utils;

import org.json.JSONException;
import org.junit.Test;
import static org.junit.Assert.*;

public class JSONParser_getJSONIntValueTest {

    @Test
    public void testGetJSONIntValue_Success() {
        String json = "{\"key\": 42}";
        int result = JSONParser.getJSONIntValue(json, "key");
        assertEquals(42, result);
    }

    @Test(expected = JSONException.class)
    public void testGetJSONIntValue_Failure() {
        String json = "{\"key\": \"notAnInt\"}";
        JSONParser.getJSONIntValue(json, "key");
    }
}
