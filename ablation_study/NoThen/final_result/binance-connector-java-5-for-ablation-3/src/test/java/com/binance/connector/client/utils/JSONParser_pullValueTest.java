
package com.binance.connector.client.utils;

import org.json.JSONObject;
import org.junit.Test;
import static org.junit.Assert.*;

public class JSONParser_pullValueTest {

    @Test
    public void testPullValue_NullParameters() {
        // Given
        JSONObject parameters = null;
        String key = "testKey";

        // When
        Object result = JSONParser.pullValue(parameters, key);

        // Then
        assertNull(result);
    }

    @Test
    public void testPullValue_ExistingKey() {
        // Given
        JSONObject parameters = new JSONObject();
        String key = "testKey";
        String value = "testValue";
        parameters.put(key, value);

        // When
        Object result = JSONParser.pullValue(parameters, key);

        // Then
        assertEquals(value, result);
        assertFalse(parameters.has(key));
    }

    @Test
    public void testPullValue_NonExistingKey() {
        // Given
        JSONObject parameters = new JSONObject();
        String key = "testKey";

        // When
        Object result = JSONParser.pullValue(parameters, key);

        // Then
        assertNull(result);
        assertFalse(parameters.has(key));
    }
}
