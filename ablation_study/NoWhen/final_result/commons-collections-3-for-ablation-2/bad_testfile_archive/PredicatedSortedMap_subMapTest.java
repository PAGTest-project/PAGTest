
package org.apache.commons.collections4.map;

import org.apache.commons.collections4.Predicate;
import org.junit.jupiter.api.Test;

import java.util.SortedMap;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PredicatedSortedMap_subMapTest {

    @Test
    public void testSubMap() {
        // Given
        SortedMap<Integer, String> originalMap = new TreeMap<>();
        originalMap.put(1, "One");
        originalMap.put(2, "Two");
        originalMap.put(3, "Three");
        originalMap.put(4, "Four");
        originalMap.put(5, "Five");

        Predicate<Integer> keyPredicate = key -> key > 0;
        Predicate<String> valuePredicate = value -> value.length() == 3;

        SortedMap<Integer, String> predicatedMap = PredicatedSortedMap.predicatedSortedMap(originalMap, keyPredicate, valuePredicate);

        // When
        SortedMap<Integer, String> subMap = predicatedMap.subMap(2, 4);

        // Then
        assertEquals(2, subMap.size());
        assertTrue(subMap.containsKey(2));
        assertTrue(subMap.containsKey(3));
        assertEquals("Two", subMap.get(2));
        assertEquals("Three", subMap.get(3));
    }
}
