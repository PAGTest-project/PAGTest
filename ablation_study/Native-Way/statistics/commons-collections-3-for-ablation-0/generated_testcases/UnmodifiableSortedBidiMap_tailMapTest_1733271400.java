
package org.apache.commons.collections4.bidimap;

import org.apache.commons.collections4.SortedBidiMap;
import org.apache.commons.collections4.map.UnmodifiableSortedMap;
import org.junit.jupiter.api.Test;

import java.util.SortedMap;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UnmodifiableSortedBidiMap_tailMapTest {

    @Test
    public void testTailMap() {
        // Given
        SortedBidiMap<Integer, String> originalMap = new TreeBidiMap<>();
        originalMap.put(1, "One");
        originalMap.put(2, "Two");
        originalMap.put(3, "Three");
        UnmodifiableSortedBidiMap<Integer, String> unmodifiableMap = new UnmodifiableSortedBidiMap<>(originalMap);

        // When
        SortedMap<Integer, String> tailMap = unmodifiableMap.tailMap(2);

        // Then
        assertTrue(tailMap instanceof UnmodifiableSortedMap);
        assertEquals(2, tailMap.size());
        assertEquals("Two", tailMap.get(2));
        assertEquals("Three", tailMap.get(3));
    }
}
