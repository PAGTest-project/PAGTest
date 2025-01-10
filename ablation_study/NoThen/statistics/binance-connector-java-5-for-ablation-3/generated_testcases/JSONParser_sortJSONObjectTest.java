
package com.binance.connector.client.utils;

import org.json.JSONObject;
import org.junit.Test;
import java.util.LinkedHashMap;
import java.util.Map;
import static org.junit.Assert.assertEquals;

public class JSONParser_sortJSONObjectTest {

    @Test
    public void testSortJSONObject() {
        // Given
        JSONObject parameters = new JSONObject();
        JSONParser.addKeyValue(parameters, "c", 3);
        JSONParser.addKeyValue(parameters, "a", 1);
        JSONParser.addKeyValue(parameters, "b", 2);

        // When
        Map<String, Object> sortedParams = JSONParser.sortJSONObject(parameters);

        // Then
        Map<String, Object> expectedSortedParams = new LinkedHashMap<>();
        expectedSortedParams.put("a", 1);
        expectedSortedParams.put("b", 2);
        expectedSortedParams.put("c", 3);

        assertEquals(expectedSortedParams, sortedParams);
    }
}
