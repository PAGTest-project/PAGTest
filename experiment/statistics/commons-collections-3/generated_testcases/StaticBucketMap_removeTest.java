
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    public void testRemoveFromEmptyMap() {
        assertNull(map.remove("key3"));
    }

    @Test
    public void testRemoveHeadOfBucket() {
        map.put("key4", "value4");
        map.put("key5", "value5");
        assertTrue(map.containsKey("key4"));
        assertEquals("value4", map.remove("key4"));
        assertFalse(map.containsKey("key4"));
        assertTrue(map.containsKey("key5"));
    }

    @Test
    public void testRemoveMiddleOfBucket() {
        map.put("key6", "value6");
        map.put("key7", "value7");
        map.put("key8", "value8");
        assertTrue(map.containsKey("key7"));
        assertEquals("value7", map.remove("key7"));
        assertFalse(map.containsKey("key7"));
        assertTrue(map.containsKey("key6"));
        assertTrue(map.containsKey("key8"));
    }

    @Test
    public void testRemoveTailOfBucket() {
        map.put("key9", "value9");
        map.put("key10", "value10");
        assertTrue(map.containsKey("key10"));
        assertEquals("value10", map.remove("key10"));
        assertFalse(map.containsKey("key10"));
        assertTrue(map.containsKey("key9"));
    }
}
