
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StaticBucketMap_removeTest {
    private StaticBucketMap<String, String> map;

    @BeforeEach
    public void setUp() {
        map = new StaticBucketMap<>(17);
    }

    @Test
    public void testRemoveExistingKey() {
        map.put("key1", "value1");
        assertTrue(map.containsKey("key1"));
        assertEquals("value1", map.get("key1"));

        String removedValue = map.remove("key1");
        assertEquals("value1", removedValue);
        assertFalse(map.containsKey("key1"));
        assertNull(map.get("key1"));
    }

    @Test
    public void testRemoveNonExistingKey() {
        assertFalse(map.containsKey("key2"));
        assertNull(map.get("key2"));

        String removedValue = map.remove("key2");
        assertNull(removedValue);
        assertFalse(map.containsKey("key2"));
        assertNull(map.get("key2"));
    }

    @Test
    public void testRemoveNullKey() {
        map.put(null, "nullValue");
        assertTrue(map.containsKey(null));
        assertEquals("nullValue", map.get(null));

        String removedValue = map.remove(null);
        assertEquals("nullValue", removedValue);
        assertFalse(map.containsKey(null));
        assertNull(map.get(null));
    }

    @Test
    public void testRemoveAndSizeDecrease() {
        map.put("key3", "value3");
        map.put("key4", "value4");
        assertEquals(2, map.size());

        map.remove("key3");
        assertEquals(1, map.size());

        map.remove("key4");
        assertEquals(0, map.size());
    }
}
