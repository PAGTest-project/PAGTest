
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class MapUtils_getMapTest {

    @Test
    public void testGetMap_withNestedMap() {
        // Given
        Map<String, Object> nestedMap = new HashMap<>();
        nestedMap.put("key1", "value1");
        Map<String, Object> outerMap = new HashMap<>();
        outerMap.put("key2", nestedMap);

        // When
        Map<?, ?> result = MapUtils.getMap(outerMap, "key2");

        // Then
        assertEquals(nestedMap, result);
    }

    @Test
    public void testGetMap_withNonMapValue() {
        // Given
        Map<String, Object> map = new HashMap<>();
        map.put("key1", "value1");

        // When
        Map<?, ?> result = MapUtils.getMap(map, "key1");

        // Then
        assertNull(result);
    }

    @Test
    public void testGetMap_withNullMap() {
        // Given
        Map<String, Object> map = null;

        // When
        Map<?, ?> result = MapUtils.getMap(map, "key1");

        // Then
        assertNull(result);
    }
}
