
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        multiValueMap.put("key2", new ArrayList<>());
        assertEquals(0, multiValueMap.totalSize());
    }

    @Test
    public void testTotalSizeWithMixedCollections() {
        List<String> list1 = new ArrayList<>();
        list1.add("value1");
        list1.add("value2");
        List<String> list2 = new ArrayList<>();
        list2.add("value3");
        multiValueMap.put("key1", list1);
        multiValueMap.put("key2", list2);
        assertEquals(3, multiValueMap.totalSize());
    }

    @Test
    public void testTotalSizeWithNestedCollections() {
        List<String> list1 = new ArrayList<>();
        list1.add("value1");
        list1.add("value2");
        List<String> list2 = new ArrayList<>();
        list2.add("value3");
        list2.add(list1);
        multiValueMap.put("key1", list2);
        assertEquals(3, multiValueMap.totalSize());
    }
}
