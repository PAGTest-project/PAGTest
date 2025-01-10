
package com.binance.connector.client.utils;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import static org.junit.Assert.*;

public class JSONParser_buildJSONStringTest {

    @Test
    public void testBuildJSONStringSuccess() {
        JSONObject parameters = new JSONObject();
        parameters.put("param1", "value1");
        parameters.put("param2", 123);

        String expected = "{\"id\":\"123\",\"method\":\"testMethod\",\"params\":{\"param1\":\"value1\",\"param2\":123}}";
        String actual = JSONParser.buildJSONString("123", "testMethod", parameters);

        assertEquals(expected, actual);
    }

    @Test
    public void testBuildJSONStringWithEmptyParameters() {
        JSONObject parameters = new JSONObject();

        String expected = "{\"id\":\"123\",\"method\":\"testMethod\",\"params\":{}}";
        String actual = JSONParser.buildJSONString("123", "testMethod", parameters);

        assertEquals(expected, actual);
    }

    @Test
    public void testBuildJSONStringWithNullParameters() {
        JSONObject parameters = null;

        String expected = "{\"id\":\"123\",\"method\":\"testMethod\",\"params\":null}";
        String actual = JSONParser.buildJSONString("123", "testMethod", parameters);

        assertEquals(expected, actual);
    }

    @Test
    public void testBuildJSONStringWithInvalidParameters() {
        JSONObject parameters = new JSONObject();
        parameters.put("param1", new Object()); // Invalid object that cannot be serialized

        assertThrows(JSONException.class, () -> {
            JSONParser.buildJSONString("123", "testMethod", parameters);
        });
    }
}
