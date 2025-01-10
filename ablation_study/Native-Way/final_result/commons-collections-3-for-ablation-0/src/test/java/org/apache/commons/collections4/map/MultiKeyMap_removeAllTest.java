
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.*;
import org.apache.commons.collections4.keyvalue.MultiKey;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MultiKeyMap_removeAllTest {
    private MultiKeyMap<String, String> multiKeyMap;

    @BeforeEach
    public void setUp() {
        multiKeyMap = new MultiKeyMap<>();
    }

    @Test
    public void testRemoveAllWithExistingKey() {
        multiKeyMap.put("key1", "key2", "value1");
        multiKeyMap.put("key1", "key3", "value2");
        multiKeyMap.put("key4", "key5", "value3");

        assertTrue(multiKeyMap.removeAll("key1"));
        assertEquals(1, multiKeyMap.size());
        assertFalse(multiKeyMap.containsKey("key1", "key2"));
        assertFalse(multiKeyMap.containsKey("key1", "key3"));
        assertTrue(multiKeyMap.containsKey("key4", "key5"));
    }

    @Test
    public void testRemoveAllWithNonExistingKey() {
        multiKeyMap.put("key1", "key2", "value1");
        multiKeyMap.put("key1", "key3", "value2");
        multiKeyMap.put("key4", "key5", "value3");

        assertFalse(multiKeyMap.removeAll("key6"));
        assertEquals(3, multiKeyMap.size());
        assertTrue(multiKeyMap.containsKey("key1", "key2"));
        assertTrue(multiKeyMap.containsKey("key1", "key3"));
        assertTrue(multiKeyMap.containsKey("key4", "key5"));
    }

    @Test
    public void testRemoveAllWithNullKey() {
        multiKeyMap.put(null, "key2", "value1");
        multiKeyMap.put("key1", "key3", "value2");
        multiKeyMap.put("key4", "key5", "value3");

        assertTrue(multiKeyMap.removeAll(null));
        assertEquals(2, multiKeyMap.size());
        assertFalse(multiKeyMap.containsKey(null, "key2"));
        assertTrue(multiKeyMap.containsKey("key1", "key3"));
        assertTrue(multiKeyMap.containsKey("key4", "key5"));
    }

    @Test
    public void testRemoveAllWithEmptyMap() {
        assertFalse(multiKeyMap.removeAll("key1"));
        assertEquals(0, multiKeyMap.size());
    }

    @Test
    public void testRemoveAllWithMultipleRemovals() {
        multiKeyMap.put("key1", "key2", "value1");
        multiKeyMap.put("key1", "key3", "value2");
        multiKeyMap.put("key4", "key5", "value3");

        assertTrue(multiKeyMap.removeAll("key1"));
        assertEquals(1, multiKeyMap.size());
        assertFalse(multiKeyMap.containsKey("key1", "key2"));
        assertFalse(multiKeyMap.containsKey("key1", "key3"));
        assertTrue(multiKeyMap.containsKey("key4", "key5"));

        assertTrue(multiKeyMap.removeAll("key4"));
        assertEquals(0, multiKeyMap.size());
        assertFalse(multiKeyMap.containsKey("key4", "key5"));
    }
}
