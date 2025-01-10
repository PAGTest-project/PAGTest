
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StaticBucketMap_getTest {

    private StaticBucketMap<String, String> map;

    @BeforeEach
    public void setUp() {
        map = new StaticBucketMap<>(17);
    }

    @Test
    public void testGetExistingKey() {
        map.put("key1", "value1");
        assertEquals("value1", map.get("key1"));
    }

    @Test
    public void testGetNonExistingKey() {
        assertNull(map.get("key2"));
    }

    @Test
    public void testGetNullKey() {
        map.put(null, "nullValue");
        assertEquals("nullValue", map.get(null));
    }

    @Test
    public void testGetAfterRemove() {
        map.put("key3", "value3");
        map.remove("key3");
        assertNull(map.get("key3"));
    }

    @Test
    public void testGetWithMultipleEntries() {
        map.put("key4", "value4");
        map.put("key5", "value5");
        assertEquals("value4", map.get("key4"));
        assertEquals("value5", map.get("key5"));
    }

    @Test
    public void testGetWithCollision() {
        // Assuming "key6" and "key7" hash to the same bucket
        map.put("key6", "value6");
        map.put("key7", "value7");
        assertEquals("value6", map.get("key6"));
        assertEquals("value7", map.get("key7"));
    }

    @Test
    public void testGetWithEmptyMap() {
        assertNull(map.get("key8"));
    }

    @Test
    public void testGetWithSameKeyDifferentValue() {
        map.put("key9", "value9");
        map.put("key9", "newValue9");
        assertEquals("newValue9", map.get("key9"));
    }

    @Test
    public void testGetWithNullValue() {
        map.put("key10", null);
        assertNull(map.get("key10"));
    }

    @Test
    public void testGetWithNullKeyAndNullValue() {
        map.put(null, null);
        assertNull(map.get(null));
    }
}
