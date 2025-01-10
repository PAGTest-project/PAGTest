
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class SetUtils_unionTest {

    @Test
    void testUnion() {
        // Given
        Set<Integer> setA = new HashSet<>(Set.of(1, 2, 3));
        Set<Integer> setB = new HashSet<>(Set.of(3, 4, 5));

        // When
        SetView<Integer> result = SetUtils.union(setA, setB);

        // Then
        assertEquals(5, result.size());
        assertTrue(result.contains(1));
        assertTrue(result.contains(2));
        assertTrue(result.contains(3));
        assertTrue(result.contains(4));
        assertTrue(result.contains(5));
    }

    @Test
    void testUnionWithEmptySets() {
        // Given
        Set<Integer> setA = new HashSet<>();
        Set<Integer> setB = new HashSet<>();

        // When
        SetView<Integer> result = SetUtils.union(setA, setB);

        // Then
        assertTrue(result.isEmpty());
    }

    @Test
    void testUnionWithNullSetA() {
        // Given
        Set<Integer> setA = null;
        Set<Integer> setB = new HashSet<>(Set.of(1, 2, 3));

        // When & Then
        assertThrows(NullPointerException.class, () -> SetUtils.union(setA, setB));
    }

    @Test
    void testUnionWithNullSetB() {
        // Given
        Set<Integer> setA = new HashSet<>(Set.of(1, 2, 3));
        Set<Integer> setB = null;

        // When & Then
        assertThrows(NullPointerException.class, () -> SetUtils.union(setA, setB));
    }
}
