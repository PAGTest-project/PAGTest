
package org.apache.commons.collections4.map;

import org.apache.commons.collections4.set.CompositeSet;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CompositeMap_entrySetTest {

    @Test
    public void testEntrySet() {
        // Given
        Map<String, String> map1 = new HashMap<>();
        map1.put("key1", "value1");
        Map<String, String> map2 = new HashMap<>();
        map2.put("key2", "value2");
        CompositeMap<String, String> compositeMap = new CompositeMap<>(map1, map2);

        // When
        Set<Map.Entry<String, String>> entrySet = compositeMap.entrySet();

        // Then
        assertEquals(2, entrySet.size());
        assertTrue(entrySet.containsAll(map1.entrySet()));
        assertTrue(entrySet.containsAll(map2.entrySet()));
    }
}
