
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StaticBucketMap_containsValueTest {
    private StaticBucketMap<String, String> map;

    @BeforeEach
    public void setUp() {
        map = new StaticBucketMap<>(17);
    }

    @Test
    public void testContainsValue_ValuePresent() {
        map.put("key1", "value1");
        map.put("key2", "value2");
        assertTrue(map.containsValue("value1"));
    }

    @Test
    public void testContainsValue_ValueNotPresent() {
        map.put("key1", "value1");
        assertFalse(map.containsValue("value3"));
    }

    @Test
    public void testContainsValue_NullValuePresent() {
        map.put("key1", null);
        assertTrue(map.containsValue(null));
    }

    @Test
    public void testContainsValue_EmptyMap() {
        assertFalse(map.containsValue("value1"));
    }

    @Test
    public void testContainsValue_AfterRemove() {
        map.put("key1", "value1");
        map.remove("key1");
        assertFalse(map.containsValue("value1"));
    }
}
