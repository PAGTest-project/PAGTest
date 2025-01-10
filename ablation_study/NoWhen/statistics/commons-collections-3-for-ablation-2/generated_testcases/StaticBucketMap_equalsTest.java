
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StaticBucketMap_equalsTest {
    private StaticBucketMap<String, String> map;

    @BeforeEach
    public void setUp() {
        map = new StaticBucketMap<>(17);
    }

    @Test
    public void testEquals_SameInstance() {
        assertTrue(map.equals(map));
    }

    @Test
    public void testEquals_DifferentType() {
        assertFalse(map.equals("not a map"));
    }

    @Test
    public void testEquals_EmptyMaps() {
        StaticBucketMap<String, String> otherMap = new StaticBucketMap<>(17);
        assertTrue(map.equals(otherMap));
    }

    @Test
    public void testEquals_NonEmptyMapsWithSameEntries() {
        map.put("key1", "value1");
        map.put("key2", "value2");
        StaticBucketMap<String, String> otherMap = new StaticBucketMap<>(17);
        otherMap.put("key1", "value1");
        otherMap.put("key2", "value2");
        assertTrue(map.equals(otherMap));
    }

    @Test
    public void testEquals_NonEmptyMapsWithDifferentEntries() {
        map.put("key1", "value1");
        map.put("key2", "value2");
        StaticBucketMap<String, String> otherMap = new StaticBucketMap<>(17);
        otherMap.put("key1", "value1");
        otherMap.put("key3", "value3");
        assertFalse(map.equals(otherMap));
    }

    @Test
    public void testEquals_NullValue() {
        map.put("key1", null);
        StaticBucketMap<String, String> otherMap = new StaticBucketMap<>(17);
        otherMap.put("key1", null);
        assertTrue(map.equals(otherMap));
    }

    @Test
    public void testEquals_DifferentBucketSize() {
        map.put("key1", "value1");
        StaticBucketMap<String, String> otherMap = new StaticBucketMap<>(31);
        otherMap.put("key1", "value1");
        assertTrue(map.equals(otherMap));
    }
}
