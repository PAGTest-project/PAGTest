
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
        Map<String, String> originalMap = new HashMap<>();
        originalMap.put("key1", "value1");
        originalMap.put("key2", "value2");

        Transformer<String, String> keyTransformer = input -> "transformed_" + input;
        Transformer<String, String> valueTransformer = input -> "transformed_" + input;

        // When
        TransformedMap<String, String> transformedMap = TransformedMap.transformedMap(originalMap, keyTransformer, valueTransformer);

        // Then
        assertEquals(2, transformedMap.size());
        assertTrue(transformedMap.containsKey("transformed_key1"));
        assertTrue(transformedMap.containsValue("transformed_value1"));
        assertTrue(transformedMap.containsKey("transformed_key2"));
        assertTrue(transformedMap.containsValue("transformed_value2"));
    }

    @Test
    public void testTransformedMapWithEmptyMap() {
        // Given
        Map<String, String> originalMap = new HashMap<>();

        Transformer<String, String> keyTransformer = input -> "transformed_" + input;
        Transformer<String, String> valueTransformer = input -> "transformed_" + input;

        // When
        TransformedMap<String, String> transformedMap = TransformedMap.transformedMap(originalMap, keyTransformer, valueTransformer);

        // Then
        assertTrue(transformedMap.isEmpty());
    }
}
