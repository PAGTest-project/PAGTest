
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
        String key = "A";
        String value = "AA";
        Object result = multiValueMap.put(key, value);
        assertEquals(value, result);
        Collection<String> collection = multiValueMap.getCollection(key);
        assertTrue(collection.contains(value));
    }

    @Test
    public void testPut_ExistingKey() {
        String key = "A";
        String value1 = "AA";
        String value2 = "AB";
        multiValueMap.put(key, value1);
        Object result = multiValueMap.put(key, value2);
        assertEquals(value2, result);
        Collection<String> collection = multiValueMap.getCollection(key);
        assertTrue(collection.contains(value1));
        assertTrue(collection.contains(value2));
    }

    @Test
    public void testPut_NullValue() {
        String key = "A";
        String value = null;
        Object result = multiValueMap.put(key, value);
        assertNull(result);
        Collection<String> collection = multiValueMap.getCollection(key);
        assertFalse(collection.contains(value));
    }

    @Test
    public void testPut_EmptyCollection() {
        String key = "A";
        String value = "AA";
        multiValueMap.put(key, value);
        multiValueMap.removeMapping(key, value);
        Object result = multiValueMap.put(key, value);
        assertEquals(value, result);
        Collection<String> collection = multiValueMap.getCollection(key);
        assertTrue(collection.contains(value));
    }

    @Test
    public void testPutAll_NewKey() {
        String key = "A";
        Collection<String> values = new ArrayList<>();
        values.add("AA");
        values.add("AB");
        boolean result = multiValueMap.putAll(key, values);
        assertTrue(result);
        Collection<String> collection = multiValueMap.getCollection(key);
        assertEquals(values, collection);
    }

    @Test
    public void testPutAll_ExistingKey() {
        String key = "A";
        Collection<String> values1 = new ArrayList<>();
        values1.add("AA");
        values1.add("AB");
        Collection<String> values2 = new ArrayList<>();
        values2.add("AC");
        values2.add("AD");
        multiValueMap.putAll(key, values1);
        boolean result = multiValueMap.putAll(key, values2);
        assertTrue(result);
        Collection<String> collection = multiValueMap.getCollection(key);
        assertTrue(collection.containsAll(values1));
        assertTrue(collection.containsAll(values2));
    }

    @Test
    public void testPutAll_NullValues() {
        String key = "A";
        Collection<String> values = null;
        boolean result = multiValueMap.putAll(key, values);
        assertFalse(result);
        Collection<String> collection = multiValueMap.getCollection(key);
        assertNull(collection);
    }

    @Test
    public void testRemoveMapping_ExistingKey() {
        String key = "A";
        String value1 = "AA";
        String value2 = "AB";
        multiValueMap.put(key, value1);
        multiValueMap.put(key, value2);
        boolean result = multiValueMap.removeMapping(key, value1);
        assertTrue(result);
        Collection<String> collection = multiValueMap.getCollection(key);
        assertFalse(collection.contains(value1));
        assertTrue(collection.contains(value2));
    }

    @Test
    public void testRemoveMapping_NonExistingKey() {
        String key = "A";
        String value = "AA";
        boolean result = multiValueMap.removeMapping(key, value);
        assertFalse(result);
        Collection<String> collection = multiValueMap.getCollection(key);
        assertNull(collection);
    }

    @Test
    public void testRemoveMapping_LastValue() {
        String key = "A";
        String value = "AA";
        multiValueMap.put(key, value);
        boolean result = multiValueMap.removeMapping(key, value);
        assertTrue(result);
        Collection<String> collection = multiValueMap.getCollection(key);
        assertNull(collection);
    }
}
