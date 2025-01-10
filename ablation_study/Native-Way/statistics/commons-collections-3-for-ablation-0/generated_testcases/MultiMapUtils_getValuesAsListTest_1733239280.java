
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.apache.commons.collections4.multimap.HashSetValuedHashMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;

class MultiMapUtils_getValuesAsListTest {

    @Test
    void testGetValuesAsList_WithNullMap() {
        // Given
        MultiValuedMap<String, String> map = null;
        String key = "testKey";

        // When
        List<String> result = MultiMapUtils.getValuesAsList(map, key);

        // Then
        assertNull(result);
    }

    @Test
    void testGetValuesAsList_WithNonListCollection() {
        // Given
        MultiValuedMap<String, String> map = new HashSetValuedHashMap<>();
        String key = "testKey";
        map.put(key, "value1");
        map.put(key, "value2");

        // When
        List<String> result = MultiMapUtils.getValuesAsList(map, key);

        // Then
        assertEquals(2, result.size());
        assertEquals("value1", result.get(0));
        assertEquals("value2", result.get(1));
    }

    @Test
    void testGetValuesAsList_WithListCollection() {
        // Given
        MultiValuedMap<String, String> map = new ArrayListValuedHashMap<>();
        String key = "testKey";
        map.put(key, "value1");
        map.put(key, "value2");

        // When
        List<String> result = MultiMapUtils.getValuesAsList(map, key);

        // Then
        assertEquals(2, result.size());
        assertEquals("value1", result.get(0));
        assertEquals("value2", result.get(1));
    }
}
