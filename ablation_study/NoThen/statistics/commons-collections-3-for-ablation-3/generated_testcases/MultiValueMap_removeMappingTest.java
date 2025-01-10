
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Iterator;

public class MultiValueMap_removeMappingTest {

    private MultiValueMap<String, String> multiValueMap;

    @BeforeEach
    public void setUp() {
        multiValueMap = new MultiValueMap<>();
        multiValueMap.put("key1", "value1");
        multiValueMap.put("key1", "value2");
        multiValueMap.put("key2", "value3");
    }

    @Test
    public void testRemoveMappingSuccess() {
        assertTrue(multiValueMap.removeMapping("key1", "value1"));
        assertFalse(multiValueMap.containsValue("key1", "value1"));
        assertEquals(2, multiValueMap.totalSize());
    }

    @Test
    public void testRemoveMappingNonExistentValue() {
        assertFalse(multiValueMap.removeMapping("key1", "nonExistentValue"));
        assertEquals(3, multiValueMap.totalSize());
    }

    @Test
    public void testRemoveMappingNonExistentKey() {
        assertFalse(multiValueMap.removeMapping("nonExistentKey", "value1"));
        assertEquals(3, multiValueMap.totalSize());
    }

    @Test
    public void testRemoveMappingLastValue() {
        assertTrue(multiValueMap.removeMapping("key2", "value3"));
        assertFalse(multiValueMap.containsKey("key2"));
        assertEquals(2, multiValueMap.totalSize());
    }

    @Test
    public void testRemoveMappingAndClear() {
        multiValueMap.clear();
        assertFalse(multiValueMap.removeMapping("key1", "value1"));
        assertEquals(0, multiValueMap.totalSize());
    }

    @Test
    public void testRemoveMappingAndIterator() {
        Iterator<String> iterator = multiValueMap.iterator("key1");
        while (iterator.hasNext()) {
            String value = iterator.next();
            if ("value1".equals(value)) {
                iterator.remove();
            }
        }
        assertFalse(multiValueMap.containsValue("key1", "value1"));
        assertEquals(2, multiValueMap.totalSize());
    }
}
