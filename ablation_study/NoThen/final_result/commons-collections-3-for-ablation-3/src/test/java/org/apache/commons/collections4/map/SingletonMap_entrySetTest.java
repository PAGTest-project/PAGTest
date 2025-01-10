
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections4.BoundedMap;
import org.apache.commons.collections4.KeyValue;
import org.apache.commons.collections4.OrderedMap;
import org.apache.commons.collections4.keyvalue.TiedMapEntry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SingletonMap_entrySetTest {

    private SingletonMap<String, String> singletonMap;

    @BeforeEach
    public void setUp() {
        singletonMap = new SingletonMap<>("key", "value");
    }

    @Test
    public void testEntrySet() {
        Set<Map.Entry<String, String>> entrySet = singletonMap.entrySet();
        assertEquals(1, entrySet.size());
        Map.Entry<String, String> entry = entrySet.iterator().next();
        assertInstanceOf(TiedMapEntry.class, entry);
        assertEquals("key", entry.getKey());
        assertEquals("value", entry.getValue());
    }

    @Test
    public void testEntrySetAfterPut() {
        singletonMap.put("key", "newValue");
        Set<Map.Entry<String, String>> entrySet = singletonMap.entrySet();
        assertEquals(1, entrySet.size());
        Map.Entry<String, String> entry = entrySet.iterator().next();
        assertInstanceOf(TiedMapEntry.class, entry);
        assertEquals("key", entry.getKey());
        assertEquals("newValue", entry.getValue());
    }

    @Test
    public void testEntrySetWithNullKey() {
        SingletonMap<String, String> nullKeyMap = new SingletonMap<>(null, "value");
        Set<Map.Entry<String, String>> entrySet = nullKeyMap.entrySet();
        assertEquals(1, entrySet.size());
        Map.Entry<String, String> entry = entrySet.iterator().next();
        assertInstanceOf(TiedMapEntry.class, entry);
        assertEquals(null, entry.getKey());
        assertEquals("value", entry.getValue());
    }

    @Test
    public void testEntrySetWithNullValue() {
        SingletonMap<String, String> nullValueMap = new SingletonMap<>("key", null);
        Set<Map.Entry<String, String>> entrySet = nullValueMap.entrySet();
        assertEquals(1, entrySet.size());
        Map.Entry<String, String> entry = entrySet.iterator().next();
        assertInstanceOf(TiedMapEntry.class, entry);
        assertEquals("key", entry.getKey());
        assertEquals(null, entry.getValue());
    }
}
