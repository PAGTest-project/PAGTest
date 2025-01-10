
package org.apache.commons.collections4.map;

import org.apache.commons.collections4.Transformer;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TransformedMap_transformedMapTest {

    @Test
    public void testTransformedMapWithNonEmptyMap() {
        // Given
        Map<String, Integer> originalMap = new HashMap<>();
        originalMap.put("key1", 1);
        originalMap.put("key2", 2);

        Transformer<String, String> keyTransformer = input -> "transformed_" + input;
        Transformer<Integer, Integer> valueTransformer = input -> input * 2;

        // When
        TransformedMap<String, Integer> transformedMap = TransformedMap.transformedMap(originalMap, keyTransformer, valueTransformer);

        // Then
        assertEquals(2, transformedMap.size());
        assertTrue(transformedMap.containsKey("transformed_key1"));
        assertTrue(transformedMap.containsKey("transformed_key2"));
        assertEquals(2, transformedMap.get("transformed_key1"));
        assertEquals(4, transformedMap.get("transformed_key2"));
    }

    @Test
    public void testTransformedMapWithEmptyMap() {
        // Given
        Map<String, Integer> originalMap = new HashMap<>();

        Transformer<String, String> keyTransformer = input -> "transformed_" + input;
        Transformer<Integer, Integer> valueTransformer = input -> input * 2;

        // When
        TransformedMap<String, Integer> transformedMap = TransformedMap.transformedMap(originalMap, keyTransformer, valueTransformer);

        // Then
        assertTrue(transformedMap.isEmpty());
    }
}
