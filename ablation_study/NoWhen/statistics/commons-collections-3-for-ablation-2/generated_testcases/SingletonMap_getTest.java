
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SingletonMap_getTest {
    private SingletonMap<String, String> singletonMap;

    @BeforeEach
    public void setUp() {
        singletonMap = new SingletonMap<>("key", "value");
    }

    @Test
    public void testGetWithMatchingKey() {
        assertEquals("value", singletonMap.get("key"));
    }

    @Test
    public void testGetWithNonMatchingKey() {
        assertNull(singletonMap.get("nonMatchingKey"));
    }

    @Test
    public void testGetWithNullKey() {
        SingletonMap<String, String> nullKeyMap = new SingletonMap<>(null, "value");
        assertEquals("value", nullKeyMap.get(null));
    }

    @Test
    public void testGetWithNullValue() {
        SingletonMap<String, String> nullValueMap = new SingletonMap<>("key", null);
        assertNull(nullValueMap.get("key"));
    }

    @Test
    public void testGetWithNullKeyAndValue() {
        SingletonMap<String, String> nullKeyAndValueMap = new SingletonMap<>(null, null);
        assertNull(nullKeyAndValueMap.get(null));
    }

    @Test
    public void testIsEqualKey() {
        assertTrue(singletonMap.isEqualKey("key"));
    }

    @Test
    public void testContainsKey() {
        assertTrue(singletonMap.containsKey("key"));
    }

    @Test
    public void testGetValue() {
        assertEquals("value", singletonMap.getValue());
    }
}
