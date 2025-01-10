
package org.apache.commons.collections4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;
import org.apache.commons.collections4.multimap.TransformedMultiValuedMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MultiMapUtils_transformedMultiValuedMapTest {

    private MultiValuedMap<String, String> originalMap;
    private Transformer<String, String> keyTransformer;
    private Transformer<String, String> valueTransformer;

    @BeforeEach
    public void setUp() {
        originalMap = new ArrayListValuedHashMap<>();
        originalMap.put("key1", "value1");
        originalMap.put("key2", "value2");

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
    }

    @Test
    public void testTransformedMultiValuedMap() {
        MultiValuedMap<String, String> transformedMap = MultiMapUtils.transformedMultiValuedMap(originalMap, keyTransformer, valueTransformer);

        assertEquals("value1", transformedMap.get("KEY1").iterator().next());
        assertEquals("value2", transformedMap.get("KEY2").iterator().next());
    }

    @Test
    public void testTransformedMultiValuedMapWithEmptyMap() {
        originalMap.clear();
        MultiValuedMap<String, String> transformedMap = MultiMapUtils.transformedMultiValuedMap(originalMap, keyTransformer, valueTransformer);

        assertTrue(transformedMap.isEmpty());
    }

    @Test
    public void testTransformedMultiValuedMapWithNullTransformers() {
        MultiValuedMap<String, String> transformedMap = MultiMapUtils.transformedMultiValuedMap(originalMap, null, null);

        assertEquals("value1", transformedMap.get("key1").iterator().next());
        assertEquals("value2", transformedMap.get("key2").iterator().next());
    }

    @Test
    public void testTransformedMultiValuedMapWithNullMap() {
        assertThrows(NullPointerException.class, () -> {
            MultiMapUtils.transformedMultiValuedMap(null, keyTransformer, valueTransformer);
        });
    }
}
