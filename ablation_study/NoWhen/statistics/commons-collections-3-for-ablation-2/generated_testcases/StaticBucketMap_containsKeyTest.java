
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StaticBucketMap_containsKeyTest {
    private StaticBucketMap<String, String> map;

    @BeforeEach
    public void setUp() {
        map = new StaticBucketMap<>(17);
    }

    @Test
    public void testContainsKey_KeyExists() {
        map.put("key1", "value1");
        assertTrue(map.containsKey("key1"));
    }

    @Test
    public void testContainsKey_KeyDoesNotExist() {
        map.put("key1", "value1");
        assertFalse(map.containsKey("key2"));
    }

    @Test
    public void testContainsKey_NullKey() {
        map.put(null, "value1");
        assertTrue(map.containsKey(null));
    }

    @Test
    public void testContainsKey_NullKeyDoesNotExist() {
        map.put("key1", "value1");
        assertFalse(map.containsKey(null));
    }

    @Test
    public void testContainsKey_AfterRemove() {
        map.put("key1", "value1");
        map.remove("key1");
        assertFalse(map.containsKey("key1"));
    }
}
