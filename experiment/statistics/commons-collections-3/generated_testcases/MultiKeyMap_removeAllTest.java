
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.*;
import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.keyvalue.MultiKey;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MultiKeyMap_removeAllTest {

    private MultiKeyMap<String, String> map;

    @BeforeEach
    public void setUp() {
        map = new MultiKeyMap<>();
        map.put("key1", "key2", "value1");
        map.put("key3", "key4", "value2");
    }

    @Test
    public void testRemoveAllWithExistingKey() {
        assertTrue(map.removeAll("key1"));
        assertFalse(map.containsKey("key1", "key2"));
    }

    @Test
    public void testRemoveAllWithNonExistingKey() {
        assertFalse(map.removeAll("nonExistingKey"));
        assertEquals(2, map.size());
    }

    @Test
    public void testRemoveAllWithNullKey() {
        map.put(null, "key2", "value3");
        assertTrue(map.removeAll(null));
        assertFalse(map.containsKey(null, "key2"));
    }

    @Test
    public void testRemoveAllWithEmptyMap() {
        MultiKeyMap<String, String> emptyMap = new MultiKeyMap<>();
        assertFalse(emptyMap.removeAll("key1"));
    }
}
