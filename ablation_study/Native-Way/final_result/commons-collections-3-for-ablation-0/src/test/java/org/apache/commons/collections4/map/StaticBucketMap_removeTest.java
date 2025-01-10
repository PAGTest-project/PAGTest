
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
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
        assertEquals("value1", map.remove("key1"));
    }

    @Test
    public void testRemoveNonExistingKey() {
        assertNull(map.remove("nonExistingKey"));
    }

    @Test
    public void testRemoveFromEmptyMap() {
        assertNull(map.remove("anyKey"));
    }

    @Test
    public void testRemoveHeadOfBucket() {
        map.put("key1", "value1");
        map.put("key2", "value2");
        assertEquals("value1", map.remove("key1"));
    }

    @Test
    public void testRemoveMiddleOfBucket() {
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");
        assertEquals("value2", map.remove("key2"));
    }

    @Test
    public void testRemoveTailOfBucket() {
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");
        assertEquals("value3", map.remove("key3"));
    }
}
