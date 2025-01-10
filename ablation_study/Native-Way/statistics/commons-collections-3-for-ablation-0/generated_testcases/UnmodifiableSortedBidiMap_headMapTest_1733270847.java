
package org.apache.commons.collections4.bidimap;

import org.apache.commons.collections4.SortedBidiMap;
import org.apache.commons.collections4.map.UnmodifiableSortedMap;
import org.junit.jupiter.api.Test;

import java.util.SortedMap;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UnmodifiableSortedBidiMap_headMapTest {

    @Test
    public void testHeadMap() {
        // Given
        SortedBidiMap<Integer, String> originalMap = new TreeBidiMap<>();
        originalMap.put(1, "One");
        originalMap.put(2, "Two");
        originalMap.put(3, "Three");
        UnmodifiableSortedBidiMap<Integer, String> unmodifiableMap = new UnmodifiableSortedBidiMap<>(originalMap);

        // When
        SortedMap<Integer, String> headMap = unmodifiableMap.headMap(3);

        // Then
        assertEquals(2, headMap.size());
        assertTrue(headMap.containsKey(1));
        assertTrue(headMap.containsKey(2));
        assertTrue(headMap instanceof UnmodifiableSortedMap);
    }
}
