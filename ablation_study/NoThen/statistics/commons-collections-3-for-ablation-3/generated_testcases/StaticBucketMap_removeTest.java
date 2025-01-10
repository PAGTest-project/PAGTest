
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StaticBucketMap_removeTest {

    private StaticBucketMap<String, String> map;

    @BeforeEach
    public void setUp() {
        map = new StaticBucketMap<>(17);
    }

    @Test
    public void testRemoveExistingKey() {
        map.put("key1", "value1");
        assertTrue(map.containsKey("key1"));
        assertEquals("value1", map.remove("key1"));
        assertFalse(map.containsKey("key1"));
    }

    @Test
    public void testRemoveNonExistingKey() {
        assertFalse(map.containsKey("key2"));
        assertNull(map.remove("key2"));
    }

    @Test
    public void testRemoveNullKey() {
        map.put(null, "nullValue");
        assertTrue(map.containsKey(null));
        assertEquals("nullValue", map.remove(null));
        assertFalse(map.containsKey(null));
    }

    @Test
    public void testRemoveFromEmptyMap() {
        assertFalse(map.containsKey("key3"));
        assertNull(map.remove("key3"));
    }

    @Test
    public void testRemoveWithMultipleEntriesInBucket() {
        map.put("key4", "value4");
        map.put("key5", "value5");
        assertTrue(map.containsKey("key4"));
        assertTrue(map.containsKey("key5"));
        assertEquals("value4", map.remove("key4"));
        assertFalse(map.containsKey("key4"));
        assertTrue(map.containsKey("key5"));
    }
}
