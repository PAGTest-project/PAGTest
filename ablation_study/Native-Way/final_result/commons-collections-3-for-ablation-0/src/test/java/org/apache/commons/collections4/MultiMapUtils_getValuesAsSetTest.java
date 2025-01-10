
package org.apache.commons.collections4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;
import org.junit.jupiter.api.Test;

public class MultiMapUtils_getValuesAsSetTest {

    @Test
    public void testGetValuesAsSet_NullMap() {
        // Given
        MultiValuedMap<String, String> map = null;
        String key = "testKey";

        // When
        Set<String> result = MultiMapUtils.getValuesAsSet(map, key);

        // Then
        assertNull(result);
    }

    @Test
    public void testGetValuesAsSet_NonSetCollection() {
        // Given
        MultiValuedMap<String, String> map = new ArrayListValuedHashMap<>();
        map.put("testKey", "value1");
        map.put("testKey", "value2");
        String key = "testKey";

        // When
        Set<String> result = MultiMapUtils.getValuesAsSet(map, key);

        // Then
        assertEquals(new HashSet<>(map.get(key)), result);
    }

    @Test
    public void testGetValuesAsSet_SetCollection() {
        // Given
        MultiValuedMap<String, String> map = new HashSetValuedHashMap<>();
        map.put("testKey", "value1");
        map.put("testKey", "value2");
        String key = "testKey";

        // When
        Set<String> result = MultiMapUtils.getValuesAsSet(map, key);

        // Then
        assertEquals(map.get(key), result);
    }
}
