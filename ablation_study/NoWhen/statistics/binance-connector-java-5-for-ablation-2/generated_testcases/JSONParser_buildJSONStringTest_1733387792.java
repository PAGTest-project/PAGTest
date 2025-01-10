
package com.binance.connector.client.utils;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import static org.junit.Assert.*;

public class JSONParser_buildJSONStringTest {

    @Test
    public void testBuildJSONString() {
        JSONObject parameters = new JSONObject();
        parameters.put("param1", "value1");
        parameters.put("param2", 123);

        String expectedJsonString = "{\"id\":\"1\",\"method\":\"testMethod\",\"params\":{\"param1\":\"value1\",\"param2\":123}}";
        String actualJsonString = JSONParser.buildJSONString("1", "testMethod", parameters);

        assertEquals(expectedJsonString, actualJsonString);
    }

    @Test
    public void testBuildJSONStringWithEmptyParameters() {
        JSONObject parameters = new JSONObject();

        String expectedJsonString = "{\"id\":\"2\",\"method\":\"testMethod\",\"params\":{}}";
        String actualJsonString = JSONParser.buildJSONString("2", "testMethod", parameters);

        assertEquals(expectedJsonString, actualJsonString);
    }

    @Test
    public void testBuildJSONStringWithNullParameters() {
        JSONObject parameters = null;

        String expectedJsonString = "{\"id\":\"3\",\"method\":\"testMethod\",\"params\":null}";
        String actualJsonString = JSONParser.buildJSONString("3", "testMethod", parameters);

        assertEquals(expectedJsonString, actualJsonString);
    }

    @Test
    public void testBuildJSONStringThrowsJSONException() {
        JSONObject parameters = new JSONObject();
        parameters.put("param1", new Object()); // This will cause JSONException

        assertThrows(JSONException.class, () -> {
            JSONParser.buildJSONString("4", "testMethod", parameters);
        });
    }
}
