
package org.apache.commons.collections4.map;

import org.apache.commons.collections4.Transformer;
import org.junit.jupiter.api.Test;

import java.util.SortedMap;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TransformedSortedMap_transformedSortedMapTest {

    @Test
    public void testTransformedSortedMapWithNonEmptyMap() {
        // Given
        SortedMap<String, Integer> originalMap = new TreeMap<>();
        originalMap.put("key1", 1);
        originalMap.put("key2", 2);

        Transformer<String, String> keyTransformer = input -> input + "_transformed";
        Transformer<Integer, Integer> valueTransformer = input -> input * 2;

        // When
        TransformedSortedMap<String, Integer> transformedMap = TransformedSortedMap.transformedSortedMap(originalMap, keyTransformer, valueTransformer);

        // Then
        assertEquals(2, transformedMap.size());
        assertTrue(transformedMap.containsKey("key1_transformed"));
        assertTrue(transformedMap.containsKey("key2_transformed"));
        assertEquals(2, transformedMap.get("key1_transformed"));
        assertEquals(4, transformedMap.get("key2_transformed"));
    }

    @Test
    public void testTransformedSortedMapWithEmptyMap() {
        // Given
        SortedMap<String, Integer> originalMap = new TreeMap<>();

        Transformer<String, String> keyTransformer = input -> input + "_transformed";
        Transformer<Integer, Integer> valueTransformer = input -> input * 2;

        // When
        TransformedSortedMap<String, Integer> transformedMap = TransformedSortedMap.transformedSortedMap(originalMap, keyTransformer, valueTransformer);

        // Then
        assertTrue(transformedMap.isEmpty());
    }
}
