
package com.binance.connector.client.utils;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import static org.junit.Assert.*;

public class JSONParser_buildJSONStringTest {

    @Test
    public void testBuildJSONString_Success() {
        // Given
        Object id = 123;
        String method = "testMethod";
        JSONObject parameters = new JSONObject();
        parameters.put("param1", "value1");

        // When
        String result = JSONParser.buildJSONString(id, method, parameters);

        // Then
        assertEquals("{\"method\":\"testMethod\",\"id\":123,\"params\":{\"param1\":\"value1\"}}", result);
    }

    @Test(expected = JSONException.class)
    public void testBuildJSONString_JSONException() {
        // Given
        Object id = new Object() {
            @Override
            public String toString() {
                throw new RuntimeException("Simulated exception");
            }
        };
        String method = "testMethod";
        JSONObject parameters = new JSONObject();

        // When
        JSONParser.buildJSONString(id, method, parameters);
    }
}
