
package org.openapitools.openapidiff.core.compare;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class MapKeyDiff_diffTest {

    @Test
    public void testDiff_BothMapsNull() {
        MapKeyDiff<String, String> result = MapKeyDiff.diff(null, null);
        assertTrue(result.getIncreased().isEmpty());
        assertTrue(result.getMissing().isEmpty());
        assertTrue(result.getSharedKey().isEmpty());
    }

    @Test
    public void testDiff_LeftMapNull() {
        Map<String, String> rightMap = new HashMap<>();
        rightMap.put("key1", "value1");

        MapKeyDiff<String, String> result = MapKeyDiff.diff(null, rightMap);
        assertEquals(rightMap, result.getIncreased());
        assertTrue(result.getMissing().isEmpty());
        assertTrue(result.getSharedKey().isEmpty());
    }

    @Test
    public void testDiff_RightMapNull() {
        Map<String, String> leftMap = new HashMap<>();
        leftMap.put("key1", "value1");

        MapKeyDiff<String, String> result = MapKeyDiff.diff(leftMap, null);
        assertEquals(leftMap, result.getMissing());
        assertTrue(result.getIncreased().isEmpty());
        assertTrue(result.getSharedKey().isEmpty());
    }

    @Test
    public void testDiff_BothMapsNotNull() {
        Map<String, String> leftMap = new HashMap<>();
        leftMap.put("key1", "value1");
        leftMap.put("key2", "value2");

        Map<String, String> rightMap = new HashMap<>();
        rightMap.put("key2", "value2");
        rightMap.put("key3", "value3");

        MapKeyDiff<String, String> result = MapKeyDiff.diff(leftMap, rightMap);
        assertEquals(1, result.getIncreased().size());
        assertEquals("value3", result.getIncreased().get("key3"));
        assertEquals(1, result.getMissing().size());
        assertEquals("value1", result.getMissing().get("key1"));
        assertEquals(1, result.getSharedKey().size());
        assertTrue(result.getSharedKey().contains("key2"));
    }
}
