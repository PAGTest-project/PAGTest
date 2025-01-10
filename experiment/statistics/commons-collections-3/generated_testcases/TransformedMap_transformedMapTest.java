
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections4.Transformer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TransformedMap_transformedMapTest {

    private Transformer<String, String> keyTransformer;
    private Transformer<String, String> valueTransformer;

    @BeforeEach
    public void setUp() {
        keyTransformer = input -> "transformed_" + input;
        valueTransformer = input -> "transformed_" + input;
    }

    @Test
    public void testTransformedMapWithEmptyMap() {
        Map<String, String> emptyMap = new HashMap<>();
        TransformedMap<String, String> transformedMap = TransformedMap.transformedMap(emptyMap, keyTransformer, valueTransformer);
        assertTrue(transformedMap.isEmpty());
    }

    @Test
    public void testTransformedMapWithNonEmptyMap() {
        Map<String, String> baseMap = new HashMap<>();
        baseMap.put("key1", "value1");
        baseMap.put("key2", "value2");

        TransformedMap<String, String> transformedMap = TransformedMap.transformedMap(baseMap, keyTransformer, valueTransformer);

        assertEquals(2, transformedMap.size());
        assertEquals("transformed_value1", transformedMap.get("transformed_key1"));
        assertEquals("transformed_value2", transformedMap.get("transformed_key2"));
    }
}
