
package com.binance.connector.client.utils;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import java.util.LinkedHashMap;
import java.util.Map;
import static org.junit.Assert.assertEquals;

public class JSONParser_sortJSONObjectTest {

    private JSONObject testJson;

    @Before
    public void setUp() {
        testJson = new JSONObject();
        testJson.put("b", 2);
        testJson.put("a", 1);
        testJson.put("c", 3);
    }

    @Test
    public void testSortJSONObject() {
        Map<String, Object> sortedParams = JSONParser.sortJSONObject(testJson);
        Map<String, Object> expectedSortedParams = new LinkedHashMap<>();
        expectedSortedParams.put("a", 1);
        expectedSortedParams.put("b", 2);
        expectedSortedParams.put("c", 3);

        assertEquals(expectedSortedParams, sortedParams);
    }

    @Test
    public void testSortJSONObjectEmpty() {
        JSONObject emptyJson = new JSONObject();
        Map<String, Object> sortedParams = JSONParser.sortJSONObject(emptyJson);
        Map<String, Object> expectedSortedParams = new LinkedHashMap<>();

        assertEquals(expectedSortedParams, sortedParams);
    }
}
