
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MultiValueMap_valuesTest {

    private MultiValueMap<String, String> multiValueMap;

    @BeforeEach
    public void setUp() {
        multiValueMap = new MultiValueMap<>();
    }

    @Test
    public void testValuesWithEmptyMap() {
        Collection<Object> values = multiValueMap.values();
        assertTrue(values.isEmpty());
    }

    @Test
    public void testValuesWithSingleEntry() {
        multiValueMap.put("key1", "value1");
        Collection<Object> values = multiValueMap.values();
        assertEquals(1, values.size());
        assertTrue(values.contains("value1"));
    }

    @Test
    public void testValuesWithMultipleEntries() {
        multiValueMap.put("key1", "value1");
        multiValueMap.put("key2", "value2");
        multiValueMap.put("key1", "value3");
        Collection<Object> values = multiValueMap.values();
        assertEquals(3, values.size());
        assertTrue(values.contains("value1"));
        assertTrue(values.contains("value2"));
        assertTrue(values.contains("value3"));
    }

    @Test
    public void testValuesWithMultipleValuesForSameKey() {
        multiValueMap.put("key1", "value1");
        multiValueMap.put("key1", "value2");
        Collection<Object> values = multiValueMap.values();
        assertEquals(2, values.size());
        assertTrue(values.contains("value1"));
        assertTrue(values.contains("value2"));
    }

    @Test
    public void testValuesWithDifferentCollections() {
        Map<String, Collection<String>> map = new HashMap<>();
        Collection<String> valuesForKey1 = new ArrayList<>(java.util.Arrays.asList("value1", "value2"));
        Collection<String> valuesForKey2 = new ArrayList<>(java.util.Arrays.asList("value3"));
        map.put("key1", valuesForKey1);
        map.put("key2", valuesForKey2);
        multiValueMap.putAll(map);
        Collection<Object> values = multiValueMap.values();
        assertEquals(3, values.size());
        assertTrue(values.contains("value1"));
        assertTrue(values.contains("value2"));
        assertTrue(values.contains("value3"));
    }
}
