
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

        Transformer<String, String> keyTransformer = key -> key + "_transformed";
        Transformer<Integer, Integer> valueTransformer = value -> value * 2;

        // When
        TransformedMap<String, Integer> transformedMap = TransformedMap.transformedMap(originalMap, keyTransformer, valueTransformer);

        // Then
        Map<String, Integer> expectedMap = new HashMap<>();
        expectedMap.put("key1_transformed", 2);
        expectedMap.put("key2_transformed", 4);

        assertEquals(expectedMap, transformedMap.decorated());
    }

    @Test
    public void testTransformedMapWithEmptyMap() {
        // Given
        Map<String, Integer> originalMap = new HashMap<>();

        Transformer<String, String> keyTransformer = key -> key + "_transformed";
        Transformer<Integer, Integer> valueTransformer = value -> value * 2;

        // When
        TransformedMap<String, Integer> transformedMap = TransformedMap.transformedMap(originalMap, keyTransformer, valueTransformer);

        // Then
        assertTrue(transformedMap.decorated().isEmpty());
    }
}
