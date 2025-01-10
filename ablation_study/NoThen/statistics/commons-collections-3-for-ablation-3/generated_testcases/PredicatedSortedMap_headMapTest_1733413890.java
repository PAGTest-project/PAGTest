
package org.apache.commons.collections4.map;

import org.apache.commons.collections4.Predicate;
import org.junit.jupiter.api.Test;

import java.util.SortedMap;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PredicatedSortedMap_headMapTest {

    @Test
    public void testHeadMap() {
        // Given
        SortedMap<Integer, String> originalMap = new TreeMap<>();
        originalMap.put(1, "One");
        originalMap.put(2, "Two");
        originalMap.put(3, "Three");

        Predicate<Integer> keyPredicate = key -> key > 0;
        Predicate<String> valuePredicate = value -> value.length() == 3;

        PredicatedSortedMap<Integer, String> predicatedMap = PredicatedSortedMap.predicatedSortedMap(originalMap, keyPredicate, valuePredicate);

        // When
        SortedMap<Integer, String> headMap = predicatedMap.headMap(3);

        // Then
        assertEquals(2, headMap.size());
        assertTrue(headMap.containsKey(1));
        assertTrue(headMap.containsKey(2));
    }
}
