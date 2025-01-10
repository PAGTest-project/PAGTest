
package org.apache.commons.collections4.map;

import org.apache.commons.collections4.Predicate;
import org.junit.jupiter.api.Test;

import java.util.SortedMap;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PredicatedSortedMap_tailMapTest {

    @Test
    public void testTailMap() {
        // Given
        SortedMap<Integer, String> originalMap = new TreeMap<>();
        originalMap.put(1, "One");
        originalMap.put(2, "Two");
        originalMap.put(3, "Three");

        Predicate<Integer> keyPredicate = key -> key > 0;
        Predicate<String> valuePredicate = value -> value.length() > 2;

        PredicatedSortedMap<Integer, String> predicatedMap = PredicatedSortedMap.predicatedSortedMap(originalMap, keyPredicate, valuePredicate);

        // When
        SortedMap<Integer, String> tailMap = predicatedMap.tailMap(2);

        // Then
        assertEquals(2, tailMap.size());
        assertTrue(tailMap.containsKey(2));
        assertTrue(tailMap.containsKey(3));
    }
}
