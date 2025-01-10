
package org.apache.commons.collections4.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
    public void testTotalSizeAfterRemove() {
        multiValueMap.put("key1", "value1");
        multiValueMap.put("key1", "value2");
        multiValueMap.put("key2", "value3");
        multiValueMap.put("key2", "value4");
        multiValueMap.removeMapping("key1", "value1");
        assertEquals(3, multiValueMap.totalSize());
    }

    @Test
    public void testTotalSizeAfterClear() {
        multiValueMap.put("key1", "value1");
        multiValueMap.put("key1", "value2");
        multiValueMap.put("key2", "value3");
        multiValueMap.put("key2", "value4");
        multiValueMap.clear();
        assertEquals(0, multiValueMap.totalSize());
    }

    @Test
    public void testTotalSizeWithPutAll() {
        Map<String, List<String>> map = new HashMap<>();
        List<String> list1 = new ArrayList<>(Arrays.asList("value1", "value2"));
        List<String> list2 = new ArrayList<>(Arrays.asList("value3", "value4"));
        map.put("key1", list1);
        map.put("key2", list2);
        multiValueMap.putAll(map);
        assertEquals(4, multiValueMap.totalSize());
    }
}
