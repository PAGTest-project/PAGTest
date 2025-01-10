
package org.apache.commons.collections4.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class MultiValueMap_removeMappingTest {

    private MultiValueMap<String, String> multiValueMap;

    @BeforeEach
    public void setUp() {
        multiValueMap = new MultiValueMap<>(new HashMap<>(), ArrayList.class);
    }

    @Test
    public void testRemoveMapping_KeyExistsAndValueExists() {
        multiValueMap.put("key1", "value1");
        multiValueMap.put("key1", "value2");

        assertTrue(multiValueMap.removeMapping("key1", "value1"));
        assertFalse(multiValueMap.containsValue("key1", "value1"));
        assertTrue(multiValueMap.containsValue("key1", "value2"));
    }

    @Test
    public void testRemoveMapping_KeyExistsAndValueDoesNotExist() {
        multiValueMap.put("key1", "value1");

        assertFalse(multiValueMap.removeMapping("key1", "value2"));
        assertTrue(multiValueMap.containsValue("key1", "value1"));
    }

    @Test
    public void testRemoveMapping_KeyDoesNotExist() {
        assertFalse(multiValueMap.removeMapping("key1", "value1"));
    }

    @Test
    public void testRemoveMapping_LastValueRemoved() {
        multiValueMap.put("key1", "value1");

        assertTrue(multiValueMap.removeMapping("key1", "value1"));
        assertNull(multiValueMap.getCollection("key1"));
    }
}
