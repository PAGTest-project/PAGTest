
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
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
    public void testPutAll_EmptyMap() {
        Map<String, String> emptyMap = new HashMap<>();
        map.putAll(emptyMap);
        assertTrue(map.isEmpty());
    }

    @Test
    public void testPutAll_SingleEntry() {
        Map<String, String> singleEntryMap = new HashMap<>();
        singleEntryMap.put("key1", "value1");
        map.putAll(singleEntryMap);
        assertEquals(1, map.size());
        assertTrue(map.containsKey("key1"));
        assertEquals("value1", map.get("key1"));
    }

    @Test
    public void testPutAll_MultipleEntries() {
        Map<String, String> multipleEntriesMap = new HashMap<>();
        multipleEntriesMap.put("key1", "value1");
        multipleEntriesMap.put("key2", "value2");
        multipleEntriesMap.put("key3", "value3");
        map.putAll(multipleEntriesMap);
        assertEquals(3, map.size());
        assertTrue(map.containsKey("key1"));
        assertTrue(map.containsKey("key2"));
        assertTrue(map.containsKey("key3"));
        assertEquals("value1", map.get("key1"));
        assertEquals("value2", map.get("key2"));
        assertEquals("value3", map.get("key3"));
    }

    @Test
    public void testPutAll_OverwriteExistingEntries() {
        map.put("key1", "oldValue");
        Map<String, String> overwriteMap = new HashMap<>();
        overwriteMap.put("key1", "newValue");
        map.putAll(overwriteMap);
        assertEquals(1, map.size());
        assertTrue(map.containsKey("key1"));
        assertEquals("newValue", map.get("key1"));
    }

    @Test
    public void testPutAll_NullKey() {
        Map<String, String> nullKeyMap = new HashMap<>();
        nullKeyMap.put(null, "nullValue");
        map.putAll(nullKeyMap);
        assertEquals(1, map.size());
        assertTrue(map.containsKey(null));
        assertEquals("nullValue", map.get(null));
    }

    @Test
    public void testPutAll_NullValue() {
        Map<String, String> nullValueMap = new HashMap<>();
        nullValueMap.put("key1", null);
        map.putAll(nullValueMap);
        assertEquals(1, map.size());
        assertTrue(map.containsKey("key1"));
        assertNull(map.get("key1"));
    }

    @Test
    public void testPutAll_NullMap() {
        Map<String, String> nullMap = null;
        map.putAll(nullMap);
        assertTrue(map.isEmpty());
    }
}
