
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MultiValueMap_removeMappingTest {

    private MultiValueMap<String, String> map;

    @BeforeEach
    public void setUp() {
        map = new MultiValueMap<>();
    }

    @Test
    public void testRemoveMapping_KeyExistsAndValueExists() {
        map.put("key1", "value1");
        map.put("key1", "value2");
        assertTrue(map.removeMapping("key1", "value1"));
        assertFalse(map.containsValue("key1", "value1"));
        assertTrue(map.containsValue("key1", "value2"));
    }

    @Test
    public void testRemoveMapping_KeyExistsAndValueDoesNotExist() {
        map.put("key1", "value1");
        assertFalse(map.removeMapping("key1", "value2"));
        assertTrue(map.containsValue("key1", "value1"));
    }

    @Test
    public void testRemoveMapping_KeyDoesNotExist() {
        assertFalse(map.removeMapping("key1", "value1"));
    }

    @Test
    public void testRemoveMapping_KeyExistsAndCollectionBecomesEmpty() {
        map.put("key1", "value1");
        assertTrue(map.removeMapping("key1", "value1"));
        assertNull(map.getCollection("key1"));
    }

    @Test
    public void testRemoveMapping_MultipleValuesAndCollectionRemainsNonEmpty() {
        map.put("key1", "value1");
        map.put("key1", "value2");
        assertTrue(map.removeMapping("key1", "value1"));
        assertFalse(map.containsValue("key1", "value1"));
        assertTrue(map.containsValue("key1", "value2"));
        assertNotNull(map.getCollection("key1"));
    }

    @Test
    public void testRemoveMapping_MultipleKeysAndValues() {
        map.put("key1", "value1");
        map.put("key1", "value2");
        map.put("key2", "value3");
        assertTrue(map.removeMapping("key1", "value1"));
        assertFalse(map.containsValue("key1", "value1"));
        assertTrue(map.containsValue("key1", "value2"));
        assertTrue(map.containsValue("key2", "value3"));
    }
}
