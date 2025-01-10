
package net.hydromatic.morel.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Pair_hashCodeTest {

    @Test
    void testHashCode() {
        Pair<String, Integer> pair1 = new Pair<>("key", 42);
        Pair<String, Integer> pair2 = new Pair<>(null, 42);
        Pair<String, Integer> pair3 = new Pair<>("key", null);
        Pair<String, Integer> pair4 = new Pair<>(null, null);

        assertEquals(pair1.hashCode(), "key".hashCode() ^ 42.hashCode());
        assertEquals(pair2.hashCode(), 0 ^ 42.hashCode());
        assertEquals(pair3.hashCode(), "key".hashCode() ^ 0);
        assertEquals(pair4.hashCode(), 0 ^ 0);
    }
}
