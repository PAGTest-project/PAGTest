
package com.binance.connector.client.utils;

import org.json.JSONObject;
import org.junit.Test;
import java.util.Map;
import static org.junit.Assert.assertEquals;

public class JSONParser_sortJSONObjectTest {

    @Test
    public void testSortJSONObject() {
        // Given
        JSONObject parameters = new JSONObject();
        parameters.put("c", "valueC");
        parameters.put("a", "valueA");
        parameters.put("b", "valueB");

        // When
        Map<String, Object> sortedParams = JSONParser.sortJSONObject(parameters);

        // Then
        assertEquals("valueA", sortedParams.get("a"));
        assertEquals("valueB", sortedParams.get("b"));
        assertEquals("valueC", sortedParams.get("c"));
    }

    @Test
    public void testSortJSONObjectWithEmptyObject() {
        // Given
        JSONObject parameters = new JSONObject();

        // When
        Map<String, Object> sortedParams = JSONParser.sortJSONObject(parameters);

        // Then
        assertEquals(0, sortedParams.size());
    }

    @Test
    public void testSortJSONObjectWithSingleKey() {
        // Given
        JSONObject parameters = new JSONObject();
        parameters.put("key", "value");

        // When
        Map<String, Object> sortedParams = JSONParser.sortJSONObject(parameters);

        // Then
        assertEquals(1, sortedParams.size());
        assertEquals("value", sortedParams.get("key"));
    }
}
