
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
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
        assertEquals(1, map.size());
    }

    @Test
    public void testSizeAfterMultiplePuts() {
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");
        assertEquals(3, map.size());
    }

    @Test
    public void testSizeAfterRemove() {
        map.put("key1", "value1");
        map.remove("key1");
        assertEquals(0, map.size());
    }

    @Test
    public void testSizeAfterClear() {
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.clear();
        assertEquals(0, map.size());
    }

    @Test
    public void testSizeAfterPutAndRemoveDifferentBuckets() {
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.remove("key1");
        assertEquals(1, map.size());
    }

    @Test
    public void testSizeAfterPutAndRemoveSameBucket() {
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.remove("key1");
        map.remove("key2");
        assertEquals(0, map.size());
    }

    @Test
    public void testSizeAfterPutAndRemoveNonExistentKey() {
        map.put("key1", "value1");
        map.remove("nonExistentKey");
        assertEquals(1, map.size());
    }

    @Test
    public void testSizeAfterPutNullKey() {
        map.put(null, "value1");
        assertEquals(1, map.size());
    }

    @Test
    public void testSizeAfterRemoveNullKey() {
        map.put(null, "value1");
        map.remove(null);
        assertEquals(0, map.size());
    }
}
