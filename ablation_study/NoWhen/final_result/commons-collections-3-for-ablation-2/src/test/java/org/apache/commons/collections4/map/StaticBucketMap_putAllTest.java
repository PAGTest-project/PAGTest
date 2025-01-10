
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

public class StaticBucketMap_putAllTest {
    private StaticBucketMap<String, String> map;

    @BeforeEach
    public void setUp() {
        map = new StaticBucketMap<>(17);
    }

    @Test
    public void testPutAll_SingleEntry() {
        Map<String, String> entries = new HashMap<>();
        entries.put("key1", "value1");

        map.putAll(entries);

        assertEquals(1, map.size());
        assertTrue(map.containsKey("key1"));
        assertEquals("value1", map.get("key1"));
    }

    @Test
    public void testPutAll_MultipleEntries() {
        Map<String, String> entries = new HashMap<>();
        entries.put("key1", "value1");
        entries.put("key2", "value2");
        entries.put("key3", "value3");

        map.putAll(entries);

        assertEquals(3, map.size());
        assertTrue(map.containsKey("key1"));
        assertTrue(map.containsKey("key2"));
        assertTrue(map.containsKey("key3"));
        assertEquals("value1", map.get("key1"));
        assertEquals("value2", map.get("key2"));
        assertEquals("value3", map.get("key3"));
    }

    @Test
    public void testPutAll_EmptyMap() {
        Map<String, String> entries = new HashMap<>();

        map.putAll(entries);

        assertEquals(0, map.size());
    }

    @Test
    public void testPutAll_OverwriteExistingEntries() {
        map.put("key1", "oldValue1");
        map.put("key2", "oldValue2");

        Map<String, String> entries = new HashMap<>();
        entries.put("key1", "newValue1");
        entries.put("key2", "newValue2");

        map.putAll(entries);

        assertEquals(2, map.size());
        assertTrue(map.containsKey("key1"));
        assertTrue(map.containsKey("key2"));
        assertEquals("newValue1", map.get("key1"));
        assertEquals("newValue2", map.get("key2"));
    }

    @Test
    public void testPutAll_ClearBeforeAdding() {
        map.put("key1", "value1");
        map.put("key2", "value2");

        map.clear();

        Map<String, String> entries = new HashMap<>();
        entries.put("key3", "value3");
        entries.put("key4", "value4");

        map.putAll(entries);

        assertEquals(2, map.size());
        assertTrue(map.containsKey("key3"));
        assertTrue(map.containsKey("key4"));
        assertEquals("value3", map.get("key3"));
        assertEquals("value4", map.get("key4"));
    }
}
