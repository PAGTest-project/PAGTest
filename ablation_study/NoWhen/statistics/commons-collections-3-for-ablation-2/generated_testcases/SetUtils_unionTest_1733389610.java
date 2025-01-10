
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class SetUtils_unionTest {

    @Test
    void testUnion() {
        Set<Integer> setA = new HashSet<>(Set.of(1, 2, 3));
        Set<Integer> setB = new HashSet<>(Set.of(3, 4, 5));

        SetView<Integer> unionSet = SetUtils.union(setA, setB);

        assertTrue(unionSet.contains(1));
        assertTrue(unionSet.contains(2));
        assertTrue(unionSet.contains(3));
        assertTrue(unionSet.contains(4));
        assertTrue(unionSet.contains(5));
        assertFalse(unionSet.contains(6));

        assertEquals(5, unionSet.size());
        assertFalse(unionSet.isEmpty());
    }

    @Test
    void testUnionWithEmptySets() {
        Set<Integer> setA = new HashSet<>();
        Set<Integer> setB = new HashSet<>();

        SetView<Integer> unionSet = SetUtils.union(setA, setB);

        assertTrue(unionSet.isEmpty());
        assertEquals(0, unionSet.size());
    }

    @Test
    void testUnionWithNullSetA() {
        Set<Integer> setA = null;
        Set<Integer> setB = new HashSet<>(Set.of(1, 2, 3));

        assertThrows(NullPointerException.class, () -> SetUtils.union(setA, setB));
    }

    @Test
    void testUnionWithNullSetB() {
        Set<Integer> setA = new HashSet<>(Set.of(1, 2, 3));
        Set<Integer> setB = null;

        assertThrows(NullPointerException.class, () -> SetUtils.union(setA, setB));
    }
}
