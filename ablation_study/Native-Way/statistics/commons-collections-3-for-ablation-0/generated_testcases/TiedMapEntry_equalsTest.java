
package org.apache.commons.collections4.keyvalue;

import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TiedMapEntry_equalsTest {

    private Map<String, String> map;
    private TiedMapEntry<String, String> entry;

    @BeforeEach
    public void setUp() {
        map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        entry = new TiedMapEntry<>(map, "key1");
    }

    @Test
    public void testEquals_SameObject() {
        assertTrue(entry.equals(entry));
    }

    @Test
    public void testEquals_DifferentClass() {
        assertFalse(entry.equals("not a Map.Entry"));
    }

    @Test
    public void testEquals_DifferentKey() {
        Map.Entry<String, String> otherEntry = new TiedMapEntry<>(map, "key2");
        assertFalse(entry.equals(otherEntry));
    }

    @Test
    public void testEquals_SameKeyDifferentValue() {
        map.put("key1", "value2");
        Map.Entry<String, String> otherEntry = new TiedMapEntry<>(map, "key1");
        assertFalse(entry.equals(otherEntry));
    }

    @Test
    public void testEquals_NullKey() {
        map.put(null, "value1");
        TiedMapEntry<String, String> nullKeyEntry = new TiedMapEntry<>(map, null);
        Map.Entry<String, String> otherEntry = new TiedMapEntry<>(map, null);
        assertTrue(nullKeyEntry.equals(otherEntry));
    }

    @Test
    public void testEquals_NullValue() {
        map.put("key1", null);
        Map.Entry<String, String> otherEntry = new TiedMapEntry<>(map, "key1");
        assertTrue(entry.equals(otherEntry));
    }

    @Test
    public void testEquals_BothNullKeyAndValue() {
        map.put(null, null);
        TiedMapEntry<String, String> nullKeyEntry = new TiedMapEntry<>(map, null);
        Map.Entry<String, String> otherEntry = new TiedMapEntry<>(map, null);
        assertTrue(nullKeyEntry.equals(otherEntry));
    }
}
