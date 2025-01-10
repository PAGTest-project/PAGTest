
package org.apache.commons.collections4.map;

import org.apache.commons.collections4.Predicate;
import org.junit.jupiter.api.Test;

import java.util.SortedMap;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PredicatedSortedMap_subMapTest {

    @Test
    public void testSubMap() {
        // Given
        SortedMap<Integer, String> originalMap = new TreeMap<>();
        originalMap.put(1, "One");
        originalMap.put(2, "Two");
        originalMap.put(3, "Three");
        Predicate<Integer> keyPredicate = key -> key > 0;
        Predicate<String> valuePredicate = value -> value.length() > 2;
        PredicatedSortedMap<Integer, String> predicatedMap = PredicatedSortedMap.predicatedSortedMap(originalMap, keyPredicate, valuePredicate);

        // When
        SortedMap<Integer, String> subMap = predicatedMap.subMap(2, 4);

        // Then
        assertNotNull(subMap);
        assertEquals(1, subMap.size());
        assertEquals("Two", subMap.get(2));
    }
}
