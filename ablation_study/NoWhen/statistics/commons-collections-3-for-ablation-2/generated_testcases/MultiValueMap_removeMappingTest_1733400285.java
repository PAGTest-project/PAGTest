
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MultiValueMap_removeMappingTest {

    private MultiValueMap<String, String> multiValueMap;

    @BeforeEach
    public void setUp() {
        multiValueMap = new MultiValueMap<>(new HashMap<>(), ArrayList.class);
    }

    @Test
    public void testRemoveMappingSuccess() {
        multiValueMap.put("key1", "value1");
        multiValueMap.put("key1", "value2");
        assertTrue(multiValueMap.removeMapping("key1", "value1"));
        assertFalse(multiValueMap.containsValue("key1", "value1"));
        assertEquals(1, multiValueMap.size("key1"));
    }

    @Test
    public void testRemoveMappingNonExistentKey() {
        assertFalse(multiValueMap.removeMapping("nonExistentKey", "value"));
    }

    @Test
    public void testRemoveMappingNonExistentValue() {
        multiValueMap.put("key1", "value1");
        assertFalse(multiValueMap.removeMapping("key1", "nonExistentValue"));
        assertEquals(1, multiValueMap.size("key1"));
    }

    @Test
    public void testRemoveMappingLastValue() {
        multiValueMap.put("key1", "value1");
        assertTrue(multiValueMap.removeMapping("key1", "value1"));
        assertNull(multiValueMap.getCollection("key1"));
    }

    @Test
    public void testRemoveMappingMultipleValues() {
        multiValueMap.put("key1", "value1");
        multiValueMap.put("key1", "value2");
        multiValueMap.put("key1", "value3");
        assertTrue(multiValueMap.removeMapping("key1", "value2"));
        assertFalse(multiValueMap.containsValue("key1", "value2"));
        assertEquals(2, multiValueMap.size("key1"));
    }
}
