
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StaticBucketMap_sizeTest {

    private StaticBucketMap<String, String> map;

    @BeforeEach
    public void setUp() {
        map = new StaticBucketMap<>(17);
    }

    @Test
    public void testSizeEmptyMap() {
        assertEquals(0, map.size());
    }

    @Test
    public void testSizeAfterPut() {
        map.put("key1", "value1");
        map.put("key2", "value2");
        assertEquals(2, map.size());
    }

    @Test
    public void testSizeAfterRemove() {
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.remove("key1");
        assertEquals(1, map.size());
    }

    @Test
    public void testSizeAfterClear() {
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.clear();
        assertEquals(0, map.size());
    }

    @Test
    public void testSizeWithDuplicateKeys() {
        map.put("key1", "value1");
        map.put("key1", "value2");
        assertEquals(1, map.size());
    }

    @Test
    public void testSizeWithNullValues() {
        map.put("key1", null);
        map.put("key2", "value2");
        assertEquals(2, map.size());
    }

    @Test
    public void testSizeWithNullKeys() {
        map.put(null, "value1");
        map.put("key2", "value2");
        assertEquals(2, map.size());
    }

    @Test
    public void testSizeWithMultiplePutsAndRemoves() {
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.remove("key1");
        map.put("key3", "value3");
        map.remove("key2");
        assertEquals(1, map.size());
    }

    @Test
    public void testSizeWithConcurrentOperations() {
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.remove("key1");
        map.put("key3", "value3");
        map.remove("key2");
        map.put("key4", "value4");
        assertEquals(2, map.size());
    }

    @Test
    public void testSizeWithEmptyBuckets() {
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.remove("key1");
        map.remove("key2");
        assertEquals(0, map.size());
    }
}
