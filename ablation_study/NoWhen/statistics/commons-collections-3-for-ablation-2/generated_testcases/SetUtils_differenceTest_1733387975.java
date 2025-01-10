
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class SetUtils_differenceTest {

    @Test
    public void testDifference() {
        Set<Integer> setA = new HashSet<>(Set.of(1, 2, 3, 4));
        Set<Integer> setB = new HashSet<>(Set.of(3, 4, 5, 6));

        SetView<Integer> result = SetUtils.difference(setA, setB);

        assertTrue(result.contains(1));
        assertTrue(result.contains(2));
        assertFalse(result.contains(3));
        assertFalse(result.contains(4));
        assertFalse(result.contains(5));
        assertFalse(result.contains(6));
    }

    @Test
    public void testDifferenceWithNullSetA() {
        Set<Integer> setB = new HashSet<>(Set.of(3, 4, 5, 6));

        assertThrows(NullPointerException.class, () -> SetUtils.difference(null, setB));
    }

    @Test
    public void testDifferenceWithNullSetB() {
        Set<Integer> setA = new HashSet<>(Set.of(1, 2, 3, 4));

        assertThrows(NullPointerException.class, () -> SetUtils.difference(setA, null));
    }
}
