
package org.apache.commons.collections4.multimap;

import org.apache.commons.collections4.Transformer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class TransformedMultiValuedMap_putAllTest {

    private TransformedMultiValuedMap<String, String> transformedMap;
    private Transformer<String, String> keyTransformer;
    private Transformer<String, String> valueTransformer;

    @BeforeEach
    public void setUp() {
        keyTransformer = new Transformer<String, String>() {
            @Override
            public String transform(String input) {
                return input.toUpperCase();
            }
        };
        valueTransformer = new Transformer<String, String>() {
            @Override
            public String transform(String input) {
                return input.toLowerCase();
            }
        };
        transformedMap = new TransformedMultiValuedMap<>(new ArrayListValuedHashMap<>(), keyTransformer, valueTransformer);
    }

    @Test
    public void testPutAllSuccess() {
        boolean result = transformedMap.putAll("key", Arrays.asList("VALUE1", "VALUE2"));
        assertTrue(result);
        assertEquals(1, transformedMap.size());
        assertEquals(Arrays.asList("value1", "value2"), transformedMap.get("KEY"));
    }

    @Test
    public void testPutAllNullValues() {
        assertThrows(NullPointerException.class, () -> {
            transformedMap.putAll("key", null);
        });
    }

    @Test
    public void testPutAllEmptyIterable() {
        boolean result = transformedMap.putAll("key", Collections.emptyList());
        assertFalse(result);
        assertTrue(transformedMap.isEmpty());
    }

    @Test
    public void testPutAllWithNoTransformers() {
        transformedMap = new TransformedMultiValuedMap<>(new ArrayListValuedHashMap<>(), null, null);
        boolean result = transformedMap.putAll("key", Arrays.asList("VALUE1", "VALUE2"));
        assertTrue(result);
        assertEquals(1, transformedMap.size());
        assertEquals(Arrays.asList("VALUE1", "VALUE2"), transformedMap.get("key"));
    }

    @Test
    public void testPutAllWithKeyTransformerOnly() {
        transformedMap = new TransformedMultiValuedMap<>(new ArrayListValuedHashMap<>(), keyTransformer, null);
        boolean result = transformedMap.putAll("key", Arrays.asList("VALUE1", "VALUE2"));
        assertTrue(result);
        assertEquals(1, transformedMap.size());
        assertEquals(Arrays.asList("VALUE1", "VALUE2"), transformedMap.get("KEY"));
    }

    @Test
    public void testPutAllWithValueTransformerOnly() {
        transformedMap = new TransformedMultiValuedMap<>(new ArrayListValuedHashMap<>(), null, valueTransformer);
        boolean result = transformedMap.putAll("key", Arrays.asList("VALUE1", "VALUE2"));
        assertTrue(result);
        assertEquals(1, transformedMap.size());
        assertEquals(Arrays.asList("value1", "value2"), transformedMap.get("key"));
    }
}
