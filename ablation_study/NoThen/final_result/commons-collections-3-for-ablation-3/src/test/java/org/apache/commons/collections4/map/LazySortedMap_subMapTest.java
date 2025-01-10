
package org.apache.commons.collections4.map;

import org.apache.commons.collections4.Factory;
import org.junit.jupiter.api.Test;
import java.util.SortedMap;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LazySortedMap_subMapTest {

    @Test
    public void testSubMap() {
        // Given
        SortedMap<Integer, String> originalMap = new TreeMap<>();
        originalMap.put(1, "One");
        originalMap.put(2, "Two");
        originalMap.put(3, "Three");
        originalMap.put(4, "Four");
        originalMap.put(5, "Five");

        Factory<String> factory = () -> "Default";
        LazySortedMap<Integer, String> lazySortedMap = new LazySortedMap<>(originalMap, factory);

        // When
        SortedMap<Integer, String> subMap = lazySortedMap.subMap(2, 4);

        // Then
        assertNotNull(subMap);
        assertEquals(2, subMap.size());
        assertEquals("Two", subMap.get(2));
        assertEquals("Three", subMap.get(3));
    }
}
