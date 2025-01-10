
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;

class MultiMapUtils_getValuesAsSetTest {

    @Test
    void testGetValuesAsSet_WithNullMap() {
        // Given
        MultiValuedMap<String, String> map = null;
        String key = "testKey";

        // When
        Set<String> result = MultiMapUtils.getValuesAsSet(map, key);

        // Then
        assertNull(result);
    }

    @Test
    void testGetValuesAsSet_WithNonSetCollection() {
        // Given
        MultiValuedMap<String, String> map = new ArrayListValuedHashMap<>();
        map.put("testKey", "value1");
        map.put("testKey", "value2");
        String key = "testKey";

        // When
        Set<String> result = MultiMapUtils.getValuesAsSet(map, key);

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains("value1"));
        assertTrue(result.contains("value2"));
    }

    @Test
    void testGetValuesAsSet_WithSetCollection() {
        // Given
        MultiValuedMap<String, String> map = new HashSetValuedHashMap<>();
        map.put("testKey", "value1");
        map.put("testKey", "value2");
        String key = "testKey";

        // When
        Set<String> result = MultiMapUtils.getValuesAsSet(map, key);

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains("value1"));
        assertTrue(result.contains("value2"));
    }
}
