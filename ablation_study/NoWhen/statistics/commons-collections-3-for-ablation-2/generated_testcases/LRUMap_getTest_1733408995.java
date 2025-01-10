
package org.apache.commons.collections4.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class LRUMap_getTest {

    private LRUMap<String, String> map;

    @BeforeEach
    void setUp() {
        map = new LRUMap<>(3);
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");
    }

    @Test
    void testGet_ExistingKey_UpdateToMRU() {
        // Given
        String key = "key1";

        // When
        String result = map.get(key, true);

        // Then
        assertEquals("value1", result);
        assertEquals("key1", map.firstKey());
    }

    @Test
    void testGet_ExistingKey_NoUpdateToMRU() {
        // Given
        String key = "key2";

        // When
        String result = map.get(key, false);

        // Then
        assertEquals("value2", result);
        assertEquals("key1", map.firstKey());
    }

    @Test
    void testGet_NonExistingKey() {
        // Given
        String key = "key4";

        // When
        String result = map.get(key, true);

        // Then
        assertNull(result);
    }
}
