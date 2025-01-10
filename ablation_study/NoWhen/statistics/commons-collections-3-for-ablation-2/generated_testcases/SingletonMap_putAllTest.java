
package org.apache.commons.collections4.map;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class SingletonMap_putAllTest {

    @Test
    public void testPutAll_EmptyMap() {
        SingletonMap<String, String> singletonMap = new SingletonMap<>("key", "value");
        Map<String, String> emptyMap = new HashMap<>();
        singletonMap.putAll(emptyMap);
        assertEquals("value", singletonMap.get("key"));
    }

    @Test
    public void testPutAll_SingleEntryMap() {
        SingletonMap<String, String> singletonMap = new SingletonMap<>("key", "value");
        Map<String, String> singleEntryMap = new HashMap<>();
        singleEntryMap.put("key", "newValue");
        singletonMap.putAll(singleEntryMap);
        assertEquals("newValue", singletonMap.get("key"));
    }

    @Test
    public void testPutAll_MultipleEntriesMap() {
        SingletonMap<String, String> singletonMap = new SingletonMap<>("key", "value");
        Map<String, String> multipleEntriesMap = new HashMap<>();
        multipleEntriesMap.put("key1", "value1");
        multipleEntriesMap.put("key2", "value2");
        assertThrows(IllegalArgumentException.class, () -> singletonMap.putAll(multipleEntriesMap));
    }
}
