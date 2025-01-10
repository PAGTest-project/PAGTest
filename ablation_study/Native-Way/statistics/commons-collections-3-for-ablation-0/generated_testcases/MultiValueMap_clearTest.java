
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MultiValueMap_clearTest {

    private MultiValueMap<String, String> multiValueMap;

    @BeforeEach
    public void setUp() {
        multiValueMap = new MultiValueMap<>();
    }

    @Test
    public void testClear() {
        multiValueMap.put("key1", "value1");
        multiValueMap.put("key1", "value2");
        multiValueMap.put("key2", "value3");

        assertEquals(2, multiValueMap.size());
        assertEquals(3, multiValueMap.totalSize());

        multiValueMap.clear();

        assertEquals(0, multiValueMap.size());
        assertEquals(0, multiValueMap.totalSize());
    }

    @Test
    public void testClearWithEmptyMap() {
        assertEquals(0, multiValueMap.size());
        assertEquals(0, multiValueMap.totalSize());

        multiValueMap.clear();

        assertEquals(0, multiValueMap.size());
        assertEquals(0, multiValueMap.totalSize());
    }

    @Test
    public void testClearWithNonEmptyMap() {
        multiValueMap.put("key1", "value1");
        multiValueMap.put("key1", "value2");
        multiValueMap.put("key2", "value3");

        assertEquals(2, multiValueMap.size());
        assertEquals(3, multiValueMap.totalSize());

        multiValueMap.clear();

        assertEquals(0, multiValueMap.size());
        assertEquals(0, multiValueMap.totalSize());
    }

    @Test
    public void testClearWithMultipleKeys() {
        multiValueMap.put("key1", "value1");
        multiValueMap.put("key1", "value2");
        multiValueMap.put("key2", "value3");
        multiValueMap.put("key2", "value4");

        assertEquals(2, multiValueMap.size());
        assertEquals(4, multiValueMap.totalSize());

        multiValueMap.clear();

        assertEquals(0, multiValueMap.size());
        assertEquals(0, multiValueMap.totalSize());
    }

    @Test
    public void testClearWithDifferentCollectionTypes() {
        MultiValueMap<String, String> mapWithArrayList = MultiValueMap.multiValueMap(new HashMap<>(), ArrayList.class);
        mapWithArrayList.put("key1", "value1");
        mapWithArrayList.put("key1", "value2");

        assertEquals(1, mapWithArrayList.size());
        assertEquals(2, mapWithArrayList.totalSize());

        mapWithArrayList.clear();

        assertEquals(0, mapWithArrayList.size());
        assertEquals(0, mapWithArrayList.totalSize());
    }
}
