
package org.apache.commons.collections4.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StaticBucketMap_containsKeyTest {

    private StaticBucketMap<String, String> map;

    @BeforeEach
    void setUp() {
        map = new StaticBucketMap<>(1); // Using 1 bucket for simplicity
    }

    @Test
    void testContainsKey_KeyExists() {
        map.put("key1", "value1");
        assertTrue(map.containsKey("key1"));
    }

    @Test
    void testContainsKey_KeyDoesNotExist() {
        assertFalse(map.containsKey("key2"));
    }

    @Test
    void testContainsKey_KeyRemoved() {
        map.put("key3", "value3");
        map.remove("key3");
        assertFalse(map.containsKey("key3"));
    }
}
