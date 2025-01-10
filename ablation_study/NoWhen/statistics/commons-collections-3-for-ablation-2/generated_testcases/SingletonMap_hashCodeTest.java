
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class SingletonMap_hashCodeTest {

    @Test
    public void testHashCodeWithNonNullKeyAndValue() {
        SingletonMap<String, String> map = new SingletonMap<>("key", "value");
        int expectedHashCode = "key".hashCode() ^ "value".hashCode();
        assertEquals(expectedHashCode, map.hashCode());
    }

    @Test
    public void testHashCodeWithNullKeyAndNonNullValue() {
        SingletonMap<String, String> map = new SingletonMap<>(null, "value");
        int expectedHashCode = 0 ^ "value".hashCode();
        assertEquals(expectedHashCode, map.hashCode());
    }

    @Test
    public void testHashCodeWithNonNullKeyAndNullValue() {
        SingletonMap<String, String> map = new SingletonMap<>("key", null);
        int expectedHashCode = "key".hashCode() ^ 0;
        assertEquals(expectedHashCode, map.hashCode());
    }

    @Test
    public void testHashCodeWithNullKeyAndNullValue() {
        SingletonMap<String, String> map = new SingletonMap<>(null, null);
        int expectedHashCode = 0 ^ 0;
        assertEquals(expectedHashCode, map.hashCode());
    }
}
