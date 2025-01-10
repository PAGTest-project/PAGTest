
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MultiValueMap_putTest {

    private MultiValueMap<String, String> multiValueMap;

    @BeforeEach
    public void setUp() {
        multiValueMap = new MultiValueMap<>();
    }

    @Test
    public void testPut_NewKey() {
        String key = "key1";
        String value = "value1";

        Object result = multiValueMap.put(key, value);

        assertEquals(value, result);
        assertTrue(multiValueMap.containsValue(key, value));
        assertEquals(1, multiValueMap.size(key));
    }

    @Test
    public void testPut_ExistingKey() {
        String key = "key1";
        String value1 = "value1";
        String value2 = "value2";

        multiValueMap.put(key, value1);
        Object result = multiValueMap.put(key, value2);

        assertEquals(value2, result);
        assertTrue(multiValueMap.containsValue(key, value1));
        assertTrue(multiValueMap.containsValue(key, value2));
        assertEquals(2, multiValueMap.size(key));
    }

    @Test
    public void testPut_NullValue() {
        String key = "key1";
        String value = null;

        Object result = multiValueMap.put(key, value);

        assertNull(result);
        assertFalse(multiValueMap.containsValue(key, value));
        assertEquals(0, multiValueMap.size(key));
    }

    @Test
    public void testPut_EmptyCollection() {
        String key = "key1";
        String value = "value1";

        // Mocking createCollection to return an empty collection
        MultiValueMap<String, String> map = new MultiValueMap<String, String>(new HashMap<>(), () -> new ArrayList<>()) {
            @Override
            protected Collection<String> createCollection(int size) {
                return new ArrayList<>();
            }
        };

        Object result = map.put(key, value);

        assertNull(result);
        assertFalse(map.containsValue(key, value));
        assertEquals(0, map.size(key));
    }

    @Test
    public void testPut_MultipleValues() {
        String key = "key1";
        String value1 = "value1";
        String value2 = "value2";
        String value3 = "value3";

        multiValueMap.put(key, value1);
        multiValueMap.put(key, value2);
        multiValueMap.put(key, value3);

        assertTrue(multiValueMap.containsValue(key, value1));
        assertTrue(multiValueMap.containsValue(key, value2));
        assertTrue(multiValueMap.containsValue(key, value3));
        assertEquals(3, multiValueMap.size(key));
    }
}
