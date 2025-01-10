
package org.apache.commons.collections4.map;

import org.apache.commons.collections4.Transformer;
import org.junit.jupiter.api.Test;

import java.util.SortedMap;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TransformedSortedMap_subMapTest {

    @Test
    public void testSubMap() {
        // Given
        SortedMap<Integer, String> originalMap = new TreeMap<>();
        originalMap.put(1, "One");
        originalMap.put(2, "Two");
        originalMap.put(3, "Three");

        Transformer<Integer, Integer> keyTransformer = input -> input * 2;
        Transformer<String, String> valueTransformer = input -> input.toUpperCase();

        TransformedSortedMap<Integer, String> transformedMap = new TransformedSortedMap<>(originalMap, keyTransformer, valueTransformer);

        // When
        SortedMap<Integer, String> subMap = transformedMap.subMap(2, 4);

        // Then
        assertEquals(1, subMap.size());
        assertTrue(subMap.containsKey(4));
        assertEquals("TWO", subMap.get(4));
    }
}
