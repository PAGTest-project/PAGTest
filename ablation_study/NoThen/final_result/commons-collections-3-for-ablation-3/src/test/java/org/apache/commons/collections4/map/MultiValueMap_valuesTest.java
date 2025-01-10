
package org.apache.commons.collections4.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    public void testValuesAfterClear() {
        multiValueMap.put("key1", "value1");
        multiValueMap.put("key2", "value2");
        multiValueMap.clear();
        Collection<Object> values = multiValueMap.values();
        assertTrue(values.isEmpty());
    }

    @Test
    public void testValuesAfterRemove() {
        multiValueMap.put("key1", "value1");
        multiValueMap.put("key2", "value2");
        multiValueMap.remove("key1");
        Collection<Object> values = multiValueMap.values();
        assertEquals(1, values.size());
        assertTrue(values.contains("value2"));
    }

    @Test
    public void testValuesWithCustomCollection() {
        Map<String, Collection<String>> map = new HashMap<>();
        map.put("key1", new ArrayList<>(java.util.Arrays.asList("value1", "value2")));
        MultiValueMap<String, String> customMap = MultiValueMap.multiValueMap(map, ArrayList::new);
        Collection<Object> values = customMap.values();
        assertEquals(2, values.size());
        assertTrue(values.contains("value1"));
        assertTrue(values.contains("value2"));
    }
}
