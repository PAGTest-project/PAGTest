
package net.hydromatic.morel.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Pair_compareToTest {

    @Test
    void testCompareTo_DifferentLeft() {
        Pair<Integer, Integer> pair1 = Pair.of(1, 2);
        Pair<Integer, Integer> pair2 = Pair.of(3, 2);
        assertTrue(pair1.compareTo(pair2) < 0);
    }

    @Test
    void testCompareTo_SameLeftDifferentRight() {
        Pair<Integer, Integer> pair1 = Pair.of(1, 2);
        Pair<Integer, Integer> pair2 = Pair.of(1, 3);
        assertTrue(pair1.compareTo(pair2) < 0);
    }

    @Test
    void testCompareTo_SameLeftSameRight() {
        Pair<Integer, Integer> pair1 = Pair.of(1, 2);
        Pair<Integer, Integer> pair2 = Pair.of(1, 2);
        assertEquals(0, pair1.compareTo(pair2));
    }
}
