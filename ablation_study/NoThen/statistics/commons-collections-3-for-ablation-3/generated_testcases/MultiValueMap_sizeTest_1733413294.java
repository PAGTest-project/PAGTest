
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

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
    public void testSizeAfterRemoveMapping() {
        multiValueMap.put("key3", "value3");
        multiValueMap.put("key3", "value4");
        multiValueMap.removeMapping("key3", "value3");
        assertEquals(1, multiValueMap.size("key3"));
    }

    @Test
    public void testSizeAfterClear() {
        multiValueMap.put("key4", "value5");
        multiValueMap.put("key4", "value6");
        multiValueMap.clear();
        assertEquals(0, multiValueMap.size("key4"));
    }

    @Test
    public void testSizeWithMultipleKeys() {
        multiValueMap.put("key5", "value7");
        multiValueMap.put("key6", "value8");
        multiValueMap.put("key6", "value9");
        assertEquals(1, multiValueMap.size("key5"));
        assertEquals(2, multiValueMap.size("key6"));
    }

    @Test
    public void testSizeWithEmptyCollection() {
        multiValueMap.put("key7", "value10");
        multiValueMap.removeMapping("key7", "value10");
        assertEquals(0, multiValueMap.size("key7"));
    }

    @Test
    public void testSizeWithNullKey() {
        multiValueMap.put(null, "value11");
        multiValueMap.put(null, "value12");
        assertEquals(2, multiValueMap.size(null));
    }

    @Test
    public void testSizeWithDifferentCollectionTypes() {
        MultiValueMap<String, String> mapWithArrayList = MultiValueMap.multiValueMap(new HashMap<>(), ArrayList.class);
        mapWithArrayList.put("key8", "value13");
        mapWithArrayList.put("key8", "value14");
        assertEquals(2, mapWithArrayList.size("key8"));

        MultiValueMap<String, String> mapWithHashSet = MultiValueMap.multiValueMap(new HashMap<>(), HashSet.class);
        mapWithHashSet.put("key9", "value15");
        mapWithHashSet.put("key9", "value16");
        assertEquals(2, mapWithHashSet.size("key9"));
    }
}
