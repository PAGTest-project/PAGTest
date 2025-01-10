
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections4.Factory;
import org.apache.commons.collections4.map.MultiValueMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MultiValueMap_sizeTest {

    private MultiValueMap<String, String> multiValueMap;

    @BeforeEach
    public void setUp() {
        multiValueMap = new MultiValueMap<>();
    }

    @Test
    public void testSizeWithExistingKey() {
        multiValueMap.put("key1", "value1");
        multiValueMap.put("key1", "value2");
        assertEquals(2, multiValueMap.size("key1"));
    }

    @Test
    public void testSizeWithNonExistingKey() {
        assertEquals(0, multiValueMap.size("key2"));
    }

    @Test
    public void testSizeWithEmptyCollection() {
        multiValueMap.put("key3", "value3");
        multiValueMap.removeMapping("key3", "value3");
        assertEquals(0, multiValueMap.size("key3"));
    }

    @Test
    public void testSizeWithMultipleKeys() {
        multiValueMap.put("key4", "value4");
        multiValueMap.put("key4", "value5");
        multiValueMap.put("key5", "value6");
        assertEquals(2, multiValueMap.size("key4"));
        assertEquals(1, multiValueMap.size("key5"));
    }

    @Test
    public void testSizeWithTotalSize() {
        multiValueMap.put("key6", "value7");
        multiValueMap.put("key6", "value8");
        multiValueMap.put("key7", "value9");
        assertEquals(2, multiValueMap.size("key6"));
        assertEquals(1, multiValueMap.size("key7"));
        assertEquals(3, multiValueMap.totalSize());
    }

    @Test
    public void testSizeWithNullKey() {
        multiValueMap.put(null, "value10");
        assertEquals(1, multiValueMap.size(null));
    }

    @Test
    public void testSizeWithNullValue() {
        multiValueMap.put("key8", null);
        assertEquals(1, multiValueMap.size("key8"));
    }

    @Test
    public void testSizeWithEmptyMap() {
        assertEquals(0, multiValueMap.size("key9"));
    }

    @Test
    public void testSizeWithCustomCollection() {
        Map<String, Collection<String>> map = new HashMap<>();
        Collection<String> collection = new ArrayList<>();
        collection.add("value11");
        collection.add("value12");
        map.put("key10", collection);
        Factory<Collection<String>> factory = Factory.instantiateFactory(ArrayList.class, new Class<?>[]{}, new Object[]{});
        multiValueMap = MultiValueMap.multiValueMap(map, factory);
        assertEquals(2, multiValueMap.size("key10"));
    }
}
