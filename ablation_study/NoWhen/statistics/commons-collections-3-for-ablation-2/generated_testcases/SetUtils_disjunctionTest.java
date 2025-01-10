
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.Set;
import java.util.HashSet;
import static org.junit.jupiter.api.Assertions.*;

public class SetUtils_disjunctionTest {

    @Test
    public void testDisjunction() {
        Set<Integer> setA = new HashSet<>(Set.of(1, 2, 3));
        Set<Integer> setB = new HashSet<>(Set.of(3, 4, 5));

        SetView<Integer> result = SetUtils.disjunction(setA, setB);

        assertTrue(result.contains(1));
        assertTrue(result.contains(2));
        assertTrue(result.contains(4));
        assertTrue(result.contains(5));
        assertFalse(result.contains(3));
        assertEquals(4, result.size());
    }

    @Test
    public void testDisjunctionWithEmptySets() {
        Set<Integer> setA = new HashSet<>();
        Set<Integer> setB = new HashSet<>();

        SetView<Integer> result = SetUtils.disjunction(setA, setB);

        assertTrue(result.isEmpty());
        assertEquals(0, result.size());
    }

    @Test
    public void testDisjunctionWithNullSetA() {
        Set<Integer> setA = null;
        Set<Integer> setB = new HashSet<>(Set.of(1, 2, 3));

        assertThrows(NullPointerException.class, () -> SetUtils.disjunction(setA, setB));
    }

    @Test
    public void testDisjunctionWithNullSetB() {
        Set<Integer> setA = new HashSet<>(Set.of(1, 2, 3));
        Set<Integer> setB = null;

        assertThrows(NullPointerException.class, () -> SetUtils.disjunction(setA, setB));
    }
}
