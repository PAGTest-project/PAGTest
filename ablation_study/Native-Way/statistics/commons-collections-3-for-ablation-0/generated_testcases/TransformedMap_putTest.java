
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections4.Transformer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TransformedMap_putTest {

    private TransformedMap<String, String> transformedMap;
    private Transformer<String, String> keyTransformer;
    private Transformer<String, String> valueTransformer;

    @BeforeEach
    public void setUp() {
        keyTransformer = new Transformer<String, String>() {
            @Override
            public String transform(String input) {
                return "transformed_" + input;
            }
        };

        valueTransformer = new Transformer<String, String>() {
            @Override
            public String transform(String input) {
                return "transformed_" + input;
            }
        };

        Map<String, String> originalMap = new HashMap<>();
        transformedMap = TransformedMap.transformingMap(originalMap, keyTransformer, valueTransformer);
    }

    @Test
    public void testPutWithTransformedKeyAndValue() {
        String result = transformedMap.put("key", "value");
        assertNull(result);
        assertEquals("transformed_value", transformedMap.get("transformed_key"));
    }

    @Test
    public void testPutWithNullKeyTransformer() {
        transformedMap = TransformedMap.transformingMap(new HashMap<>(), null, valueTransformer);
        String result = transformedMap.put("key", "value");
        assertNull(result);
        assertEquals("transformed_value", transformedMap.get("key"));
    }

    @Test
    public void testPutWithNullValueTransformer() {
        transformedMap = TransformedMap.transformingMap(new HashMap<>(), keyTransformer, null);
        String result = transformedMap.put("key", "value");
        assertNull(result);
        assertEquals("value", transformedMap.get("transformed_key"));
    }

    @Test
    public void testPutWithNullTransformers() {
        transformedMap = TransformedMap.transformingMap(new HashMap<>(), null, null);
        String result = transformedMap.put("key", "value");
        assertNull(result);
        assertEquals("value", transformedMap.get("key"));
    }
}
