
package org.apache.commons.collections4.map;

import org.apache.commons.collections4.OrderedMap;
import org.apache.commons.collections4.set.UnmodifiableSet;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UnmodifiableOrderedMap_keySetTest {

    @Test
    public void testKeySet() {
        // Given
        Map<String, String> originalMap = new HashMap<>();
        originalMap.put("key1", "value1");
        originalMap.put("key2", "value2");
        OrderedMap<String, String> orderedMap = UnmodifiableOrderedMap.unmodifiableOrderedMap(new org.apache.commons.collections4.map.ListOrderedMap<>(originalMap));

        // When
        Set<String> keySet = orderedMap.keySet();

        // Then
        assertEquals(2, keySet.size());
        assertTrue(keySet.contains("key1"));
        assertTrue(keySet.contains("key2"));
        assertTrue(keySet instanceof UnmodifiableSet);
    }
}
