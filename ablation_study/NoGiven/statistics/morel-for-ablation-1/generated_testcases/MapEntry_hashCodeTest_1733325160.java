
package net.hydromatic.morel.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MapEntry_hashCodeTest {

    @Test
    public void testHashCodeWithNonNullKeyAndValue() {
        MapEntry<String, Integer> entry = new MapEntry<>("key", 42);
        int expectedHash = "key".hashCode() ^ 42.hashCode();
        assertEquals(expectedHash, entry.hashCode());
    }

    @Test
    public void testHashCodeWithNullKeyAndNonNullValue() {
        MapEntry<String, Integer> entry = new MapEntry<>(null, 42);
        int expectedHash = 0 ^ 42.hashCode();
        assertEquals(expectedHash, entry.hashCode());
    }

    @Test
    public void testHashCodeWithNonNullKeyAndNullValue() {
        MapEntry<String, Integer> entry = new MapEntry<>("key", null);
        int expectedHash = "key".hashCode() ^ 0;
        assertEquals(expectedHash, entry.hashCode());
    }

    @Test
    public void testHashCodeWithNullKeyAndNullValue() {
        MapEntry<String, Integer> entry = new MapEntry<>(null, null);
        int expectedHash = 0 ^ 0;
        assertEquals(expectedHash, entry.hashCode());
    }
}
