
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

class MultiMapUtils_getValuesAsSetTest {

    @Test
    void testGetValuesAsSet() {
        // Given
        MultiValuedMap<String, String> map = new HashSetValuedHashMap<>();
        map.put("key1", "value1");
        map.put("key1", "value2");

        // When
        Set<String> result = MultiMapUtils.getValuesAsSet(map, "key1");

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains("value1"));
        assertTrue(result.contains("value2"));
    }

    @Test
    void testGetValuesAsSet_NullMap() {
        // Given
        MultiValuedMap<String, String> map = null;

        // When
        Set<String> result = MultiMapUtils.getValuesAsSet(map, "key1");

        // Then
        assertNull(result);
    }

    @Test
    void testGetValuesAsSet_NonSetCollection() {
        // Given
        MultiValuedMap<String, String> map = new ArrayListValuedHashMap<>();
        map.put("key1", "value1");
        map.put("key1", "value2");

        // When
        Set<String> result = MultiMapUtils.getValuesAsSet(map, "key1");

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains("value1"));
        assertTrue(result.contains("value2"));
    }
}
