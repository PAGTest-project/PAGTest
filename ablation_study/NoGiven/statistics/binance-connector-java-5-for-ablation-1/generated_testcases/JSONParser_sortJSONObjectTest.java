
package com.binance.connector.client.utils;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import java.util.Map;
import static org.junit.Assert.*;

public class JSONParser_sortJSONObjectTest {

    private JSONObject testJson;

    @Before
    public void setUp() {
        testJson = new JSONObject();
        JSONParser.addKeyValue(testJson, "c", "valueC");
        JSONParser.addKeyValue(testJson, "a", "valueA");
        JSONParser.addKeyValue(testJson, "b", "valueB");
    }

    @Test
    public void testSortJSONObject() {
        Map<String, Object> sortedParams = JSONParser.sortJSONObject(testJson);

        assertEquals("valueA", sortedParams.get("a"));
        assertEquals("valueB", sortedParams.get("b"));
        assertEquals("valueC", sortedParams.get("c"));
    }

    @Test
    public void testSortJSONObjectWithRemovedKey() {
        JSONParser.pullValue(testJson, "b");
        Map<String, Object> sortedParams = JSONParser.sortJSONObject(testJson);

        assertEquals("valueA", sortedParams.get("a"));
        assertNull(sortedParams.get("b"));
        assertEquals("valueC", sortedParams.get("c"));
    }

    @Test
    public void testSortJSONObjectWithEmptyObject() {
        JSONObject emptyJson = new JSONObject();
        Map<String, Object> sortedParams = JSONParser.sortJSONObject(emptyJson);

        assertTrue(sortedParams.isEmpty());
    }
}
