
package com.binance.connector.client.utils;

import org.json.JSONException;
import org.junit.Test;
import static org.junit.Assert.*;

public class JSONParser_getJSONStringValueTest {

    @Test
    public void testGetJSONStringValue_Success() {
        String json = "{\"key\":\"value\"}";
        String key = "key";
        String expected = "value";
        String actual = JSONParser.getJSONStringValue(json, key);
        assertEquals(expected, actual);
    }

    @Test(expected = JSONException.class)
    public void testGetJSONStringValue_Failure() {
        String json = "{\"key\":\"value\"}";
        String key = "invalidKey";
        JSONParser.getJSONStringValue(json, key);
    }
}
