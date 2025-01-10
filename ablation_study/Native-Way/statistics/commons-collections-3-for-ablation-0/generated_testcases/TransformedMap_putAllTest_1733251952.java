
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.collections4.Transformer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TransformedMap_putAllTest {

    private TransformedMap<String, String> transformedMap;
    private Transformer<String, String> keyTransformer;
    private Transformer<String, String> valueTransformer;

    @BeforeEach
    public void setUp() {
        keyTransformer = new Transformer<String, String>() {
            @Override
            public String apply(String input) {
                return input.toUpperCase();
            }
        };
        valueTransformer = new Transformer<String, String>() {
            @Override
            public String apply(String input) {
                return input.toLowerCase();
            }
        };
        transformedMap = new TransformedMap<>(new HashMap<>(), keyTransformer, valueTransformer);
    }

    @Test
    public void testPutAll() {
        Map<String, String> mapToCopy = new HashMap<>();
        mapToCopy.put("Key1", "Value1");
        mapToCopy.put("Key2", "Value2");

        transformedMap.putAll(mapToCopy);

        assertEquals("value1", transformedMap.get("KEY1"));
        assertEquals("value2", transformedMap.get("KEY2"));
    }

    @Test
    public void testPutAllWithEmptyMap() {
        Map<String, String> mapToCopy = new HashMap<>();

        transformedMap.putAll(mapToCopy);

        assertEquals(0, transformedMap.size());
    }

    @Test
    public void testPutAllWithNullKey() {
        Map<String, String> mapToCopy = new HashMap<>();
        mapToCopy.put(null, "Value1");

        assertThrows(NullPointerException.class, () -> transformedMap.putAll(mapToCopy));
    }

    @Test
    public void testPutAllWithNullValue() {
        Map<String, String> mapToCopy = new HashMap<>();
        mapToCopy.put("Key1", null);

        assertThrows(NullPointerException.class, () -> transformedMap.putAll(mapToCopy));
    }
}
