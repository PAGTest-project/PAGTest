
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class SetUtils_intersectionTest {

    @Test
    public void testIntersection() {
        Set<Integer> setA = new HashSet<>(Set.of(1, 2, 3));
        Set<Integer> setB = new HashSet<>(Set.of(2, 3, 4));

        SetView<Integer> intersection = SetUtils.intersection(setA, setB);

        assertTrue(intersection.contains(2));
        assertTrue(intersection.contains(3));
        assertFalse(intersection.contains(1));
        assertFalse(intersection.contains(4));
    }

    @Test
    public void testIntersectionWithNullSetA() {
        Set<Integer> setB = new HashSet<>(Set.of(2, 3, 4));

        assertThrows(NullPointerException.class, () -> SetUtils.intersection(null, setB));
    }

    @Test
    public void testIntersectionWithNullSetB() {
        Set<Integer> setA = new HashSet<>(Set.of(1, 2, 3));

        assertThrows(NullPointerException.class, () -> SetUtils.intersection(setA, null));
    }
}
