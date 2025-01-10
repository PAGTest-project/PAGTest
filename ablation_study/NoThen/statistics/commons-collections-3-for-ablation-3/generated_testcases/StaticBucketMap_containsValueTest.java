
package org.apache.commons.collections4.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StaticBucketMap_containsValueTest {

    private StaticBucketMap<String, String> map;

    @BeforeEach
    void setUp() {
        map = new StaticBucketMap<>(1); // Using 1 bucket for simplicity
    }

    @Test
    void testContainsValue_ValuePresent() {
        // Given
        map.put("key1", "value1");
        map.put("key2", "value2");

        // When
        boolean result = map.containsValue("value1");

        // Then
        assertTrue(result);
    }

    @Test
    void testContainsValue_ValueNotPresent() {
        // Given
        map.put("key1", "value1");
        map.put("key2", "value2");

        // When
        boolean result = map.containsValue("value3");

        // Then
        assertFalse(result);
    }

    @Test
    void testContainsValue_EmptyMap() {
        // Given
        // Map is empty

        // When
        boolean result = map.containsValue("value1");

        // Then
        assertFalse(result);
    }
}
