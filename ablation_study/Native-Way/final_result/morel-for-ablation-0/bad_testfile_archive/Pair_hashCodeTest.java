
package net.hydromatic.morel.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Pair_hashCodeTest {

    @Test
    void testHashCodeWithNonNullValues() {
        Pair<String, Integer> pair = new Pair<>("key", 42);
        int expectedHash = "key".hashCode() ^ 42.hashCode();
        assertEquals(expectedHash, pair.hashCode());
    }

    @Test
    void testHashCodeWithNullLeft() {
        Pair<String, Integer> pair = new Pair<>(null, 42);
        int expectedHash = 0 ^ 42.hashCode();
        assertEquals(expectedHash, pair.hashCode());
    }

    @Test
    void testHashCodeWithNullRight() {
        Pair<String, Integer> pair = new Pair<>("key", null);
        int expectedHash = "key".hashCode() ^ 0;
        assertEquals(expectedHash, pair.hashCode());
    }

    @Test
    void testHashCodeWithBothNull() {
        Pair<String, Integer> pair = new Pair<>(null, null);
        int expectedHash = 0 ^ 0;
        assertEquals(expectedHash, pair.hashCode());
    }
}
