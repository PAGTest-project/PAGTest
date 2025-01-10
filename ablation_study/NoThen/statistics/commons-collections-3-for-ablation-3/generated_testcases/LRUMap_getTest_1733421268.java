
package org.apache.commons.collections4.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LRUMap_getTest {

    private LRUMap<String, String> lruMap;

    @BeforeEach
    public void setUp() {
        lruMap = new LRUMap<>(2);
    }

    @Test
    public void testGetExistingKeyWithUpdateToMRU() {
        lruMap.put("key1", "value1");
        lruMap.put("key2", "value2");

        assertEquals("value1", lruMap.get("key1", true));
        assertEquals("value2", lruMap.get("key2", true));

        // Ensure "key1" is now the most recently used
        lruMap.put("key3", "value3");
        assertNull(lruMap.get("key2"));
        assertEquals("value1", lruMap.get("key1"));
        assertEquals("value3", lruMap.get("key3"));
    }

    @Test
    public void testGetExistingKeyWithoutUpdateToMRU() {
        lruMap.put("key1", "value1");
        lruMap.put("key2", "value2");

        assertEquals("value1", lruMap.get("key1", false));
        assertEquals("value2", lruMap.get("key2", false));

        // Ensure the order remains the same
        lruMap.put("key3", "value3");
        assertNull(lruMap.get("key1"));
        assertEquals("value2", lruMap.get("key2"));
        assertEquals("value3", lruMap.get("key3"));
    }

    @Test
    public void testGetNonExistingKey() {
        lruMap.put("key1", "value1");
        lruMap.put("key2", "value2");

        assertNull(lruMap.get("key3", true));
        assertNull(lruMap.get("key3", false));
    }

    @Test
    public void testGetWithEmptyMap() {
        assertNull(lruMap.get("key1", true));
        assertNull(lruMap.get("key1", false));
    }

    @Test
    public void testGetWithFullMapAndUpdateToMRU() {
        lruMap.put("key1", "value1");
        lruMap.put("key2", "value2");

        assertEquals("value1", lruMap.get("key1", true));
        assertEquals("value2", lruMap.get("key2", true));

        // Ensure "key1" is now the most recently used
        lruMap.put("key3", "value3");
        assertNull(lruMap.get("key2"));
        assertEquals("value1", lruMap.get("key1"));
        assertEquals("value3", lruMap.get("key3"));
    }

    @Test
    public void testGetWithFullMapWithoutUpdateToMRU() {
        lruMap.put("key1", "value1");
        lruMap.put("key2", "value2");

        assertEquals("value1", lruMap.get("key1", false));
        assertEquals("value2", lruMap.get("key2", false));

        // Ensure the order remains the same
        lruMap.put("key3", "value3");
        assertNull(lruMap.get("key1"));
        assertEquals("value2", lruMap.get("key2"));
        assertEquals("value3", lruMap.get("key3"));
    }
}
