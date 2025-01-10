
package org.apache.commons.collections4.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LRUMap_getTest {

    private LRUMap<String, String> lruMap;

    @BeforeEach
    public void setUp() {
        lruMap = new LRUMap<>(3);
    }

    @Test
    public void testGet_ExistingKey_UpdateToMRU() {
        lruMap.put("key1", "value1");
        lruMap.put("key2", "value2");
        lruMap.put("key3", "value3");

        // Get an existing key with updateToMRU set to true
        String value = lruMap.get("key1", true);

        // Assert the value is correct
        assertEquals("value1", value);

        // Assert the order of keys after the get operation
        assertArrayEquals(new String[]{"key2", "key3", "key1"}, lruMap.keySet().toArray(new String[0]));
    }

    @Test
    public void testGet_ExistingKey_DoNotUpdateToMRU() {
        lruMap.put("key1", "value1");
        lruMap.put("key2", "value2");
        lruMap.put("key3", "value3");

        // Get an existing key with updateToMRU set to false
        String value = lruMap.get("key1", false);

        // Assert the value is correct
        assertEquals("value1", value);

        // Assert the order of keys remains unchanged
        assertArrayEquals(new String[]{"key1", "key2", "key3"}, lruMap.keySet().toArray(new String[0]));
    }

    @Test
    public void testGet_NonExistingKey() {
        lruMap.put("key1", "value1");
        lruMap.put("key2", "value2");
        lruMap.put("key3", "value3");

        // Get a non-existing key
        String value = lruMap.get("key4", true);

        // Assert the value is null
        assertNull(value);

        // Assert the order of keys remains unchanged
        assertArrayEquals(new String[]{"key1", "key2", "key3"}, lruMap.keySet().toArray(new String[0]));
    }
}
