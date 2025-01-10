
package org.apache.commons.collections4.map;

import org.apache.commons.collections4.MultiMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class MultiValueMap_iteratorTest {

    private MultiValueMap<String, String> map;

    @BeforeEach
    public void setUp() {
        map = new MultiValueMap<>();
        map.put("key1", "value1");
        map.put("key1", "value2");
        map.put("key2", "value3");
    }

    @Test
    public void testIterator() {
        Iterator<Map.Entry<String, String>> iterator = map.iterator();
        List<Map.Entry<String, String>> entries = new ArrayList<>();
        while (iterator.hasNext()) {
            entries.add(iterator.next());
        }

        assertEquals(3, entries.size());
        assertTrue(entries.contains(new AbstractMap.SimpleEntry<>("key1", "value1")));
        assertTrue(entries.contains(new AbstractMap.SimpleEntry<>("key1", "value2")));
        assertTrue(entries.contains(new AbstractMap.SimpleEntry<>("key2", "value3")));
    }

    @Test
    public void testIteratorWithEmptyMap() {
        MultiValueMap<String, String> emptyMap = new MultiValueMap<>();
        Iterator<Map.Entry<String, String>> iterator = emptyMap.iterator();
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testIteratorWithSingleEntry() {
        MultiValueMap<String, String> singleEntryMap = new MultiValueMap<>();
        singleEntryMap.put("key", "value");
        Iterator<Map.Entry<String, String>> iterator = singleEntryMap.iterator();

        assertTrue(iterator.hasNext());
        Map.Entry<String, String> entry = iterator.next();
        assertEquals("key", entry.getKey());
        assertEquals("value", entry.getValue());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testIteratorWithMultipleValuesForSingleKey() {
        Iterator<Map.Entry<String, String>> iterator = map.iterator();
        List<Map.Entry<String, String>> entries = new ArrayList<>();
        while (iterator.hasNext()) {
            entries.add(iterator.next());
        }

        assertEquals(3, entries.size());
        assertTrue(entries.contains(new AbstractMap.SimpleEntry<>("key1", "value1")));
        assertTrue(entries.contains(new AbstractMap.SimpleEntry<>("key1", "value2")));
        assertTrue(entries.contains(new AbstractMap.SimpleEntry<>("key2", "value3")));
    }

    @Test
    public void testIteratorRemoveNotSupported() {
        Iterator<Map.Entry<String, String>> iterator = map.iterator();
        assertTrue(iterator.hasNext());
        assertThrows(UnsupportedOperationException.class, () -> {
            iterator.next(); // Move to the first element
            iterator.remove();
        });
    }
}
