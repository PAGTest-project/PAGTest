
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class SetUtils_unionTest {

    @Test
    public void testUnion() {
        Set<Integer> setA = new HashSet<>(Set.of(1, 2, 3));
        Set<Integer> setB = new HashSet<>(Set.of(3, 4, 5));

        SetView<Integer> result = SetUtils.union(setA, setB);

        assertEquals(5, result.size());
        assertTrue(result.contains(1));
        assertTrue(result.contains(2));
        assertTrue(result.contains(3));
        assertTrue(result.contains(4));
        assertTrue(result.contains(5));
    }

    @Test
    public void testUnionWithEmptySets() {
        Set<Integer> setA = new HashSet<>();
        Set<Integer> setB = new HashSet<>();

        SetView<Integer> result = SetUtils.union(setA, setB);

        assertTrue(result.isEmpty());
    }

    @Test
    public void testUnionWithNullSetA() {
        Set<Integer> setA = null;
        Set<Integer> setB = new HashSet<>(Set.of(1, 2, 3));

        assertThrows(NullPointerException.class, () -> SetUtils.union(setA, setB));
    }

    @Test
    public void testUnionWithNullSetB() {
        Set<Integer> setA = new HashSet<>(Set.of(1, 2, 3));
        Set<Integer> setB = null;

        assertThrows(NullPointerException.class, () -> SetUtils.union(setA, setB));
    }
}
