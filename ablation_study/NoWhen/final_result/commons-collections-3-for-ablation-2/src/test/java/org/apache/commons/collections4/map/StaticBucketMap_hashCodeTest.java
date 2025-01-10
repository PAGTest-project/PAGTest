
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
    public void testHashCodeWithEmptyMap() {
        int hashCode = map.hashCode();
        assertEquals(0, hashCode);
    }

    @Test
    public void testHashCodeWithSingleEntry() {
        map.put("key1", "value1");
        int hashCode = map.hashCode();
        assertEquals("key1".hashCode() ^ "value1".hashCode(), hashCode);
    }

    @Test
    public void testHashCodeWithMultipleEntries() {
        map.put("key1", "value1");
        map.put("key2", "value2");
        int hashCode = map.hashCode();
        int expectedHashCode = ("key1".hashCode() ^ "value1".hashCode()) + ("key2".hashCode() ^ "value2".hashCode());
        assertEquals(expectedHashCode, hashCode);
    }

    @Test
    public void testHashCodeWithNullKey() {
        map.put(null, "value1");
        int hashCode = map.hashCode();
        assertEquals(0 ^ "value1".hashCode(), hashCode);
    }

    @Test
    public void testHashCodeWithNullValue() {
        map.put("key1", null);
        int hashCode = map.hashCode();
        assertEquals("key1".hashCode() ^ 0, hashCode);
    }
}
