
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
    public void testGetWithSameHashDifferentKey() {
        // Assuming "A" and "B" hash to the same bucket
        map.put("A", "valueA");
        map.put("B", "valueB");
        assertEquals("valueA", map.get("A"));
        assertEquals("valueB", map.get("B"));
    }

    @Test
    public void testGetWithCollision() {
        // Assuming "C" and "D" hash to the same bucket
        map.put("C", "valueC");
        map.put("D", "valueD");
        assertEquals("valueC", map.get("C"));
        assertEquals("valueD", map.get("D"));
    }

    @Test
    public void testGetWithNullValue() {
        map.put("key6", null);
        assertNull(map.get("key6"));
    }

    @Test
    public void testGetWithNullKeyAndNullValue() {
        map.put(null, null);
        assertNull(map.get(null));
    }

    @Test
    public void testGetWithEmptyMap() {
        assertNull(map.get("nonExistentKey"));
    }

    @Test
    public void testGetWithConcurrentModification() {
        map.put("key7", "value7");
        map.put("key8", "value8");
        map.remove("key7");
        assertNull(map.get("key7"));
        assertEquals("value8", map.get("key8"));
    }
}
