
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

public class SingletonMap_putAllTest {

    @Test
    public void testPutAllWithEmptyMap() {
        SingletonMap<String, String> singletonMap = new SingletonMap<>("key", "value");
        Map<String, String> emptyMap = new HashMap<>();
        singletonMap.putAll(emptyMap);
        assertEquals("value", singletonMap.get("key"));
    }

    @Test
    public void testPutAllWithSingleEntryMap() {
        SingletonMap<String, String> singletonMap = new SingletonMap<>("key", "value");
        Map<String, String> singleEntryMap = new HashMap<>();
        singleEntryMap.put("key", "newValue");
        singletonMap.putAll(singleEntryMap);
        assertEquals("newValue", singletonMap.get("key"));
    }

    @Test
    public void testPutAllWithMultipleEntriesMap() {
        SingletonMap<String, String> singletonMap = new SingletonMap<>("key", "value");
        Map<String, String> multipleEntriesMap = new HashMap<>();
        multipleEntriesMap.put("key1", "value1");
        multipleEntriesMap.put("key2", "value2");
        assertThrows(IllegalArgumentException.class, () -> singletonMap.putAll(multipleEntriesMap));
    }
}
