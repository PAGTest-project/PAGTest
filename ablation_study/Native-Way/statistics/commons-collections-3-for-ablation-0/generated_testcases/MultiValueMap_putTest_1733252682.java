
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
    public void testPutNewKey() {
        String key = "key1";
        String value = "value1";
        Object result = multiValueMap.put(key, value);
        assertEquals(value, result);
        Collection<String> collection = multiValueMap.getCollection(key);
        assertTrue(collection.contains(value));
    }

    @Test
    public void testPutExistingKey() {
        String key = "key1";
        String value1 = "value1";
        String value2 = "value2";
        multiValueMap.put(key, value1);
        Object result = multiValueMap.put(key, value2);
        assertEquals(value2, result);
        Collection<String> collection = multiValueMap.getCollection(key);
        assertTrue(collection.contains(value1));
        assertTrue(collection.contains(value2));
    }

    @Test
    public void testPutNullValue() {
        String key = "key1";
        String value = null;
        Object result = multiValueMap.put(key, value);
        assertNull(result);
        Collection<String> collection = multiValueMap.getCollection(key);
        assertFalse(collection.contains(value));
    }

    @Test
    public void testPutEmptyCollection() {
        String key = "key1";
        String value = "value1";
        multiValueMap = new MultiValueMap<>(new HashMap<>(), () -> new ArrayList<>());
        Object result = multiValueMap.put(key, value);
        assertNull(result);
        Collection<String> collection = multiValueMap.getCollection(key);
        assertFalse(collection.contains(value));
    }

    @Test
    public void testPutAllNewKey() {
        String key = "key1";
        Collection<String> values = new ArrayList<>();
        values.add("value1");
        values.add("value2");
        boolean result = multiValueMap.putAll(key, values);
        assertTrue(result);
        Collection<String> collection = multiValueMap.getCollection(key);
        assertEquals(values, collection);
    }

    @Test
    public void testPutAllExistingKey() {
        String key = "key1";
        Collection<String> initialValues = new ArrayList<>();
        initialValues.add("value1");
        multiValueMap.putAll(key, initialValues);
        Collection<String> additionalValues = new ArrayList<>();
        additionalValues.add("value2");
        additionalValues.add("value3");
        boolean result = multiValueMap.putAll(key, additionalValues);
        assertTrue(result);
        Collection<String> collection = multiValueMap.getCollection(key);
        assertTrue(collection.containsAll(initialValues));
        assertTrue(collection.containsAll(additionalValues));
    }

    @Test
    public void testRemoveMapping() {
        String key = "key1";
        String value1 = "value1";
        String value2 = "value2";
        multiValueMap.put(key, value1);
        multiValueMap.put(key, value2);
        boolean result = multiValueMap.removeMapping(key, value1);
        assertTrue(result);
        Collection<String> collection = multiValueMap.getCollection(key);
        assertFalse(collection.contains(value1));
        assertTrue(collection.contains(value2));
    }

    @Test
    public void testRemoveMappingLastValue() {
        String key = "key1";
        String value = "value1";
        multiValueMap.put(key, value);
        boolean result = multiValueMap.removeMapping(key, value);
        assertTrue(result);
        Collection<String> collection = multiValueMap.getCollection(key);
        assertNull(collection);
    }

    @Test
    public void testSize() {
        String key = "key1";
        String value1 = "value1";
        String value2 = "value2";
        multiValueMap.put(key, value1);
        multiValueMap.put(key, value2);
        int size = multiValueMap.size(key);
        assertEquals(2, size);
    }

    @Test
    public void testTotalSize() {
        String key1 = "key1";
        String key2 = "key2";
        String value1 = "value1";
        String value2 = "value2";
        String value3 = "value3";
        multiValueMap.put(key1, value1);
        multiValueMap.put(key1, value2);
        multiValueMap.put(key2, value3);
        int totalSize = multiValueMap.totalSize();
        assertEquals(3, totalSize);
    }
}
