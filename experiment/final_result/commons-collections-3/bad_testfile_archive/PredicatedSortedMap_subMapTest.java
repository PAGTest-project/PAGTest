
package org.apache.commons.collections4.map;

import org.apache.commons.collections4.Predicate;
import org.junit.jupiter.api.Test;

import java.util.SortedMap;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PredicatedSortedMap_subMapTest {

    @Test
    public void testSubMap() {
        // Given
        SortedMap<Integer, String> originalMap = new TreeMap<>();
        originalMap.put(1, "one");
        originalMap.put(2, "two");
        originalMap.put(3, "three");
        originalMap.put(4, "four");

        Predicate<Integer> keyPredicate = mock(Predicate.class);
        Predicate<String> valuePredicate = mock(Predicate.class);

        when(keyPredicate.evaluate(1)).thenReturn(true);
        when(keyPredicate.evaluate(2)).thenReturn(true);
        when(keyPredicate.evaluate(3)).thenReturn(true);
        when(valuePredicate.evaluate("one")).thenReturn(true);
        when(valuePredicate.evaluate("two")).thenReturn(true);
        when(valuePredicate.evaluate("three")).thenReturn(true);

        PredicatedSortedMap<Integer, String> predicatedMap = new PredicatedSortedMap<>(originalMap, keyPredicate, valuePredicate);

        // When
        SortedMap<Integer, String> subMap = predicatedMap.subMap(2, 4);

        // Then
        assertEquals(2, subMap.size());
        assertEquals("two", subMap.get(2));
        assertEquals("three", subMap.get(3));
    }
}
