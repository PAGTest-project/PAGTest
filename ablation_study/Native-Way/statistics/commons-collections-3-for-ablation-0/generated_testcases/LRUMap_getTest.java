
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LRUMap_getTest {

    private LRUMap<String, String> lruMap;

    @BeforeEach
    public void setUp() {
        lruMap = new LRUMap<>(10);
    }

    @Test
    public void testGetExistingKeyWithUpdateToMRU() {
        lruMap.put("key1", "value1");
        String value = lruMap.get("key1", true);
        assertEquals("value1", value);
    }

    @Test
    public void testGetExistingKeyWithoutUpdateToMRU() {
        lruMap.put("key1", "value1");
        String value = lruMap.get("key1", false);
        assertEquals("value1", value);
    }

    @Test
    public void testGetNonExistingKey() {
        String value = lruMap.get("key2", true);
        assertNull(value);
    }

    @Test
    public void testGetNullKey() {
        String value = lruMap.get(null, true);
        assertNull(value);
    }

    @Test
    public void testGetWithFullMap() {
        for (int i = 0; i < 10; i++) {
            lruMap.put("key" + i, "value" + i);
        }
        String value = lruMap.get("key0", true);
        assertEquals("value0", value);
    }

    @Test
    public void testGetWithFullMapWithoutUpdateToMRU() {
        for (int i = 0; i < 10; i++) {
            lruMap.put("key" + i, "value" + i);
        }
        String value = lruMap.get("key0", false);
        assertEquals("value0", value);
    }

    @Test
    public void testGetWithEmptyMap() {
        String value = lruMap.get("key1", true);
        assertNull(value);
    }

    @Test
    public void testGetWithMultipleGets() {
        lruMap.put("key1", "value1");
        lruMap.put("key2", "value2");
        lruMap.get("key1", true);
        lruMap.get("key2", true);
        String value = lruMap.get("key1", true);
        assertEquals("value1", value);
    }

    @Test
    public void testGetWithMultipleGetsWithoutUpdateToMRU() {
        lruMap.put("key1", "value1");
        lruMap.put("key2", "value2");
        lruMap.get("key1", false);
        lruMap.get("key2", false);
        String value = lruMap.get("key1", false);
        assertEquals("value1", value);
    }
}
