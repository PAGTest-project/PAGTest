
package net.hydromatic.morel.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Pair_hashCodeTest {

    @Test
    void testHashCode() {
        // Given
        Pair<String, Integer> pair1 = Pair.of("key", 42);
        Pair<String, Integer> pair2 = Pair.of(null, 42);
        Pair<String, Integer> pair3 = Pair.of("key", null);
        Pair<String, Integer> pair4 = Pair.of(null, null);

        // When and Then
        assertEquals("key".hashCode() ^ 42.hashCode(), pair1.hashCode());
        assertEquals(0 ^ 42.hashCode(), pair2.hashCode());
        assertEquals("key".hashCode() ^ 0, pair3.hashCode());
        assertEquals(0 ^ 0, pair4.hashCode());
    }
}
