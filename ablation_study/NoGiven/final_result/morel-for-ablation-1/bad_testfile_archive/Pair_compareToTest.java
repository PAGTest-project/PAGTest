
package net.hydromatic.morel.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Pair_compareToTest {

    @Test
    void testCompareTo() {
        Pair<Integer, Integer> pair1 = new Pair<>(1, 2);
        Pair<Integer, Integer> pair2 = new Pair<>(1, 3);
        Pair<Integer, Integer> pair3 = new Pair<>(2, 2);
        Pair<Integer, Integer> pair4 = new Pair<>(1, 2);

        // Test left comparison
        assertEquals(-1, pair1.compareTo(pair3));

        // Test right comparison
        assertEquals(-1, pair1.compareTo(pair2));

        // Test equality
        assertEquals(0, pair1.compareTo(pair4));
    }
}
