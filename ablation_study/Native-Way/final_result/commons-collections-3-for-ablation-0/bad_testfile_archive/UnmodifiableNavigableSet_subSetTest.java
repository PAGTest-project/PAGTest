
package org.apache.commons.collections4.set;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.NavigableSet;
import java.util.TreeSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UnmodifiableNavigableSet_subSetTest {

    private NavigableSet<Integer> originalSet;
    private UnmodifiableNavigableSet<Integer> unmodifiableSet;

    @BeforeEach
    public void setUp() {
        originalSet = new TreeSet<>(java.util.Arrays.asList(1, 2, 3, 4, 5));
        unmodifiableSet = UnmodifiableNavigableSet.unmodifiableNavigableSet(originalSet);
    }

    @Test
    public void testSubSetInclusive() {
        NavigableSet<Integer> subSet = unmodifiableSet.subSet(2, true, 4, true);
        assertTrue(subSet.containsAll(java.util.Arrays.asList(2, 3, 4)));
    }

    @Test
    public void testSubSetExclusive() {
        NavigableSet<Integer> subSet = unmodifiableSet.subSet(2, false, 4, false);
        assertTrue(subSet.containsAll(java.util.Arrays.asList(3)));
    }

    @Test
    public void testSubSetFromInclusiveToExclusive() {
        NavigableSet<Integer> subSet = unmodifiableSet.subSet(2, true, 4, false);
        assertTrue(subSet.containsAll(java.util.Arrays.asList(2, 3)));
    }

    @Test
    public void testSubSetFromExclusiveToInclusive() {
        NavigableSet<Integer> subSet = unmodifiableSet.subSet(2, false, 4, true);
        assertTrue(subSet.containsAll(java.util.Arrays.asList(3, 4)));
    }

    @Test
    public void testSubSetUnmodifiable() {
        NavigableSet<Integer> subSet = unmodifiableSet.subSet(2, true, 4, true);
        assertThrows(UnsupportedOperationException.class, () -> subSet.add(5));
    }
}
