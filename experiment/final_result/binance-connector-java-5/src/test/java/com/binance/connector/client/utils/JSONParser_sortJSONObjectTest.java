
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
        parameters.put("b", 2);
        parameters.put("a", 1);
        parameters.put("c", 3);

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
