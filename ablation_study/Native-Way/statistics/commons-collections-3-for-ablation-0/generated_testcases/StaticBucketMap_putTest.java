
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StaticBucketMap_putTest {
    private StaticBucketMap<String, String> map;

    @BeforeEach
    public void setUp() {
        map = new StaticBucketMap<>(17);
    }

    @Test
    public void testPutNewKey() {
        assertNull(map.put("key1", "value1"));
        assertEquals("value1", map.get("key1"));
    }

    @Test
    public void testPutExistingKey() {
        map.put("key1", "value1");
        assertEquals("value1", map.put("key1", "newValue1"));
        assertEquals("newValue1", map.get("key1"));
    }

    @Test
    public void testPutNullKey() {
        assertNull(map.put(null, "nullValue"));
        assertEquals("nullValue", map.get(null));
    }

    @Test
    public void testPutNullValue() {
        assertNull(map.put("key1", null));
        assertNull(map.get("key1"));
    }

    @Test
    public void testPutMultipleKeys() {
        assertNull(map.put("key1", "value1"));
        assertNull(map.put("key2", "value2"));
        assertEquals("value1", map.get("key1"));
        assertEquals("value2", map.get("key2"));
    }

    @Test
    public void testPutSameHashKey() {
        // Assuming "A" and "B" hash to the same bucket
        assertNull(map.put("A", "valueA"));
        assertNull(map.put("B", "valueB"));
        assertEquals("valueA", map.get("A"));
        assertEquals("valueB", map.get("B"));
    }
}
