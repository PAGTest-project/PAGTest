
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class SetUtils_differenceTest {

    @Test
    void testDifference() {
        Set<Integer> setA = new HashSet<>(Set.of(1, 2, 3));
        Set<Integer> setB = new HashSet<>(Set.of(2, 3, 4));

        SetView<Integer> result = SetUtils.difference(setA, setB);

        assertTrue(result.contains(1));
        assertFalse(result.contains(2));
        assertFalse(result.contains(3));
        assertFalse(result.contains(4));
    }

    @Test
    void testDifferenceWithNullSetA() {
        Set<Integer> setB = new HashSet<>(Set.of(2, 3, 4));

        assertThrows(NullPointerException.class, () -> SetUtils.difference(null, setB));
    }

    @Test
    void testDifferenceWithNullSetB() {
        Set<Integer> setA = new HashSet<>(Set.of(1, 2, 3));

        assertThrows(NullPointerException.class, () -> SetUtils.difference(setA, null));
    }
}
