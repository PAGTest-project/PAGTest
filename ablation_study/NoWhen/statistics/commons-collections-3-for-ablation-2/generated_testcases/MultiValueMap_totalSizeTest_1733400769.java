
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MultiValueMap_totalSizeTest {

    private MultiValueMap<String, String> multiValueMap;

    @BeforeEach
    public void setUp() {
        multiValueMap = new MultiValueMap<>();
    }

    @Test
    public void testTotalSizeEmptyMap() {
        assertEquals(0, multiValueMap.totalSize());
    }

    @Test
    public void testTotalSizeSingleKeySingleValue() {
        multiValueMap.put("key1", "value1");
        assertEquals(1, multiValueMap.totalSize());
    }

    @Test
    public void testTotalSizeSingleKeyMultipleValues() {
        multiValueMap.put("key1", "value1");
        multiValueMap.put("key1", "value2");
        assertEquals(2, multiValueMap.totalSize());
    }

    @Test
    public void testTotalSizeMultipleKeysMultipleValues() {
        multiValueMap.put("key1", "value1");
        multiValueMap.put("key1", "value2");
        multiValueMap.put("key2", "value3");
        multiValueMap.put("key2", "value4");
        assertEquals(4, multiValueMap.totalSize());
    }

    @Test
    public void testTotalSizeWithEmptyCollections() {
        multiValueMap.put("key1", new ArrayList<>());
        multiValueMap.put("key2", new HashSet<>());
        assertEquals(0, multiValueMap.totalSize());
    }

    @Test
    public void testTotalSizeWithMixedCollections() {
        multiValueMap.put("key1", "value1");
        multiValueMap.put("key1", "value2");
        multiValueMap.put("key2", new ArrayList<>());
        multiValueMap.put("key3", "value3");
        assertEquals(3, multiValueMap.totalSize());
    }

    @Test
    public void testTotalSizeWithPutAll() {
        Collection<String> values = new ArrayList<>();
        values.add("value1");
        values.add("value2");
        multiValueMap.putAll("key1", values);
        assertEquals(2, multiValueMap.totalSize());
    }

    @Test
    public void testTotalSizeWithPutAllMultipleKeys() {
        Collection<String> values1 = new ArrayList<>();
        values1.add("value1");
        values1.add("value2");
        multiValueMap.putAll("key1", values1);

        Collection<String> values2 = new ArrayList<>();
        values2.add("value3");
        values2.add("value4");
        multiValueMap.putAll("key2", values2);

        assertEquals(4, multiValueMap.totalSize());
    }

    @Test
    public void testTotalSizeWithPutAllAndSinglePut() {
        Collection<String> values = new ArrayList<>();
        values.add("value1");
        values.add("value2");
        multiValueMap.putAll("key1", values);
        multiValueMap.put("key2", "value3");
        assertEquals(3, multiValueMap.totalSize());
    }

    @Test
    public void testTotalSizeWithPutAllEmptyCollection() {
        Collection<String> values = new ArrayList<>();
        multiValueMap.putAll("key1", values);
        assertEquals(0, multiValueMap.totalSize());
    }

    @Test
    public void testTotalSizeWithPutAllAndRemove() {
        Collection<String> values = new ArrayList<>();
        values.add("value1");
        values.add("value2");
        multiValueMap.putAll("key1", values);
        multiValueMap.removeMapping("key1", "value1");
        assertEquals(1, multiValueMap.totalSize());
    }

    @Test
    public void testTotalSizeWithPutAllAndClear() {
        Collection<String> values = new ArrayList<>();
        values.add("value1");
        values.add("value2");
        multiValueMap.putAll("key1", values);
        multiValueMap.clear();
        assertEquals(0, multiValueMap.totalSize());
    }
}
