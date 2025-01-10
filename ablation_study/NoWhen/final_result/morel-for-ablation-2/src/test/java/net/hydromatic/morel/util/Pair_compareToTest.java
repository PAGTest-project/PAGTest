
package net.hydromatic.morel.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Pair_compareToTest {

    @Test
    void testCompareTo_DifferentLeft() {
        Pair<Integer, Integer> pair1 = Pair.of(1, 2);
        Pair<Integer, Integer> pair2 = Pair.of(2, 2);
        assertEquals(-1, pair1.compareTo(pair2));
    }

    @Test
    void testCompareTo_SameLeftDifferentRight() {
        Pair<Integer, Integer> pair1 = Pair.of(1, 2);
        Pair<Integer, Integer> pair2 = Pair.of(1, 3);
        assertEquals(-1, pair1.compareTo(pair2));
    }

    @Test
    void testCompareTo_SameLeftRight() {
        Pair<Integer, Integer> pair1 = Pair.of(1, 2);
        Pair<Integer, Integer> pair2 = Pair.of(1, 2);
        assertEquals(0, pair1.compareTo(pair2));
    }
}
