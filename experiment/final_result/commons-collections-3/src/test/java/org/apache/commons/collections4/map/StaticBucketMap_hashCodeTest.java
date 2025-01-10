
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StaticBucketMap_hashCodeTest {
    private StaticBucketMap<String, String> map;

    @BeforeEach
    public void setUp() {
        map = new StaticBucketMap<>(17);
    }

    @Test
    public void testHashCodeWithSingleEntry() {
        map.put("key1", "value1");
        int expectedHashCode = "key1".hashCode() ^ "value1".hashCode();
        assertEquals(expectedHashCode, map.hashCode());
    }

    @Test
    public void testHashCodeWithMultipleEntries() {
        map.put("key1", "value1");
        map.put("key2", "value2");
        int expectedHashCode = ("key1".hashCode() ^ "value1".hashCode()) + ("key2".hashCode() ^ "value2".hashCode());
        assertEquals(expectedHashCode, map.hashCode());
    }

    @Test
    public void testHashCodeWithRemovedEntry() {
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.remove("key1");
        int expectedHashCode = "key2".hashCode() ^ "value2".hashCode();
        assertEquals(expectedHashCode, map.hashCode());
    }
}
