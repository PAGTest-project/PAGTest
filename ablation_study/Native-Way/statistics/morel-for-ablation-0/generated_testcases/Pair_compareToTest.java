
package net.hydromatic.morel.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Pair_compareToTest {
    private Pair<Integer, Integer> pair1;
    private Pair<Integer, Integer> pair2;
    private Pair<Integer, Integer> pair3;
    private Pair<Integer, Integer> pair4;

    @BeforeEach
    public void setUp() {
        pair1 = new Pair<>(1, 2);
        pair2 = new Pair<>(1, 3);
        pair3 = new Pair<>(2, 2);
        pair4 = new Pair<>(1, 2);
    }

    @Test
    public void testCompareToEqual() {
        assertEquals(0, pair1.compareTo(pair4));
    }

    @Test
    public void testCompareToLeftLess() {
        assertTrue(pair1.compareTo(pair3) < 0);
    }

    @Test
    public void testCompareToLeftGreater() {
        assertTrue(pair3.compareTo(pair1) > 0);
    }

    @Test
    public void testCompareToRightLess() {
        assertTrue(pair1.compareTo(pair2) < 0);
    }

    @Test
    public void testCompareToRightGreater() {
        assertTrue(pair2.compareTo(pair1) > 0);
    }
}
