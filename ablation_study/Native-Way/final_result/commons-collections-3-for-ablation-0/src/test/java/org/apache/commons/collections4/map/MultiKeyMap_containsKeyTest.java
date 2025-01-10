
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MultiKeyMap_containsKeyTest {
    private MultiKeyMap<String, String> map;

    @BeforeEach
    public void setUp() {
        map = new MultiKeyMap<>();
    }

    @Test
    public void testContainsKeyWithExistingKeys() {
        map.put("key1", "key2", "value");
        assertTrue(map.containsKey("key1", "key2"));
    }

    @Test
    public void testContainsKeyWithNonExistingKeys() {
        map.put("key1", "key2", "value");
        assertFalse(map.containsKey("key3", "key4"));
    }

    @Test
    public void testContainsKeyWithNullKeys() {
        map.put(null, "key2", "value");
        assertTrue(map.containsKey(null, "key2"));
        assertFalse(map.containsKey("key1", null));
    }

    @Test
    public void testContainsKeyWithEmptyMap() {
        assertFalse(map.containsKey("key1", "key2"));
    }

    @Test
    public void testContainsKeyWithMultipleEntries() {
        map.put("key1", "key2", "value1");
        map.put("key3", "key4", "value2");
        assertTrue(map.containsKey("key1", "key2"));
        assertTrue(map.containsKey("key3", "key4"));
        assertFalse(map.containsKey("key1", "key4"));
    }
}
