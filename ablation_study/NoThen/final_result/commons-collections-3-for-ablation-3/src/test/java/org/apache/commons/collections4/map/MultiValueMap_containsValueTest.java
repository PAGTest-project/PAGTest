
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MultiValueMap_containsValueTest {

    private MultiValueMap<String, String> multiValueMap;

    @BeforeEach
    public void setUp() {
        multiValueMap = new MultiValueMap<>();
    }

    @Test
    public void testContainsValue_ValuePresent() {
        multiValueMap.put("key1", "value1");
        multiValueMap.put("key1", "value2");
        multiValueMap.put("key2", "value3");

        assertTrue(multiValueMap.containsValue("value1"));
        assertTrue(multiValueMap.containsValue("value2"));
        assertTrue(multiValueMap.containsValue("value3"));
    }

    @Test
    public void testContainsValue_ValueNotPresent() {
        multiValueMap.put("key1", "value1");
        multiValueMap.put("key1", "value2");
        multiValueMap.put("key2", "value3");

        assertFalse(multiValueMap.containsValue("value4"));
        assertFalse(multiValueMap.containsValue("value5"));
    }

    @Test
    public void testContainsValue_EmptyMap() {
        assertFalse(multiValueMap.containsValue("value1"));
    }

    @Test
    public void testContainsValue_AfterRemove() {
        multiValueMap.put("key1", "value1");
        multiValueMap.put("key1", "value2");
        multiValueMap.put("key2", "value3");

        multiValueMap.removeMapping("key1", "value1");
        assertFalse(multiValueMap.containsValue("value1"));
        assertTrue(multiValueMap.containsValue("value2"));
        assertTrue(multiValueMap.containsValue("value3"));
    }

    @Test
    public void testContainsValue_AfterClear() {
        multiValueMap.put("key1", "value1");
        multiValueMap.put("key1", "value2");
        multiValueMap.put("key2", "value3");

        multiValueMap.clear();
        assertFalse(multiValueMap.containsValue("value1"));
        assertFalse(multiValueMap.containsValue("value2"));
        assertFalse(multiValueMap.containsValue("value3"));
    }
}
