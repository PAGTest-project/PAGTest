
package org.apache.commons.collections4.map;

import org.apache.commons.collections4.Factory;
import org.junit.jupiter.api.Test;

import java.util.SortedMap;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LazySortedMap_headMapTest {

    @Test
    public void testHeadMap() {
        // Given
        SortedMap<Integer, String> originalMap = new TreeMap<>();
        originalMap.put(1, "One");
        originalMap.put(2, "Two");
        originalMap.put(3, "Three");

        Factory<String> factory = () -> "Default";
        LazySortedMap<Integer, String> lazySortedMap = LazySortedMap.lazySortedMap(originalMap, factory);

        // When
        SortedMap<Integer, String> headMap = lazySortedMap.headMap(3);

        // Then
        assertEquals(2, headMap.size());
        assertTrue(headMap.containsKey(1));
        assertTrue(headMap.containsKey(2));
    }
}
