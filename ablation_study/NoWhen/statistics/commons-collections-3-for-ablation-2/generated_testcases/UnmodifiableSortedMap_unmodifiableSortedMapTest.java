
package org.apache.commons.collections4.map;

import org.apache.commons.collections4.Unmodifiable;
import org.junit.jupiter.api.Test;

import java.util.SortedMap;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UnmodifiableSortedMap_unmodifiableSortedMapTest {

    @Test
    public void testUnmodifiableSortedMapWithUnmodifiableMap() {
        // Given
        SortedMap<String, String> originalMap = new TreeMap<>();
        originalMap.put("key1", "value1");
        SortedMap<String, String> unmodifiableMap = UnmodifiableSortedMap.unmodifiableSortedMap(originalMap);

        // When
        SortedMap<String, String> result = UnmodifiableSortedMap.unmodifiableSortedMap(unmodifiableMap);

        // Then
        assertSame(unmodifiableMap, result);
    }

    @Test
    public void testUnmodifiableSortedMapWithModifiableMap() {
        // Given
        SortedMap<String, String> originalMap = new TreeMap<>();
        originalMap.put("key1", "value1");

        // When
        SortedMap<String, String> result = UnmodifiableSortedMap.unmodifiableSortedMap(originalMap);

        // Then
        assertTrue(result instanceof Unmodifiable);
    }
}
