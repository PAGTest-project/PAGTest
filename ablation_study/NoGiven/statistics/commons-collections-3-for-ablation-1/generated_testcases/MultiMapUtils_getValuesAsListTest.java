
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;

import static org.junit.jupiter.api.Assertions.*;

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
        map.put("testKey", "value1");
        map.put("testKey", "value2");
        String key = "testKey";

        // When
        List<String> result = MultiMapUtils.getValuesAsList(map, key);

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains("value1"));
        assertTrue(result.contains("value2"));
    }

    @Test
    void testGetValuesAsList_WithListCollection() {
        // Given
        MultiValuedMap<String, String> map = new ArrayListValuedHashMap<>();
        map.put("testKey", "value1");
        map.put("testKey", "value2");
        String key = "testKey";

        // When
        List<String> result = MultiMapUtils.getValuesAsList(map, key);

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains("value1"));
        assertTrue(result.contains("value2"));
    }
}
