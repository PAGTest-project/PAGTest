
package org.apache.commons.collections4.map;

import org.apache.commons.collections4.Transformer;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TransformedMap_transformingMapTest {

    @Test
    public void testTransformingMap() {
        // Given
        Map<String, Integer> originalMap = new HashMap<>();
        originalMap.put("key1", 1);
        originalMap.put("key2", 2);

        Transformer<String, String> keyTransformer = input -> "transformed_" + input;
        Transformer<Integer, Integer> valueTransformer = input -> input * 2;

        // When
        TransformedMap<String, Integer> transformedMap = TransformedMap.transformingMap(originalMap, keyTransformer, valueTransformer);

        // Transform the map entries
        transformedMap.transformMap();

        // Then
        assertNotNull(transformedMap);
        assertEquals(2, transformedMap.size());
        assertEquals(2, transformedMap.get("transformed_key1"));
        assertEquals(4, transformedMap.get("transformed_key2"));
    }
}
