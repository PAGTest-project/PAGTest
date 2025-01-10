
package com.binance.connector.client.utils;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import static org.junit.Assert.*;

public class JSONParser_buildJSONStringTest {

    @Test
    public void testBuildJSONStringSuccess() {
        Object id = 123;
        String method = "testMethod";
        JSONObject parameters = new JSONObject();
        parameters.put("param1", "value1");
        parameters.put("param2", 456);

        String expected = "{\"id\":123,\"method\":\"testMethod\",\"params\":{\"param1\":\"value1\",\"param2\":456}}";
        assertEquals(expected, JSONParser.buildJSONString(id, method, parameters));
    }

    @Test
    public void testBuildJSONStringWithNullParameters() {
        Object id = 123;
        String method = "testMethod";
        JSONObject parameters = null;

        String expected = "{\"id\":123,\"method\":\"testMethod\",\"params\":null}";
        assertEquals(expected, JSONParser.buildJSONString(id, method, parameters));
    }

    @Test
    public void testBuildJSONStringWithEmptyParameters() {
        Object id = 123;
        String method = "testMethod";
        JSONObject parameters = new JSONObject();

        String expected = "{\"id\":123,\"method\":\"testMethod\",\"params\":{}}";
        assertEquals(expected, JSONParser.buildJSONString(id, method, parameters));
    }

    @Test
    public void testBuildJSONStringThrowException() {
        Object id = new Object() {
            @Override
            public String toString() {
                throw new RuntimeException("Forced exception");
            }
        };
        String method = "testMethod";
        JSONObject parameters = new JSONObject();

        assertThrows(JSONException.class, () -> JSONParser.buildJSONString(id, method, parameters));
    }
}
