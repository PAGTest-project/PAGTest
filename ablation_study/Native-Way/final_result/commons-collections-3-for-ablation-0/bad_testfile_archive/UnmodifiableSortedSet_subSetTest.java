
package org.apache.commons.collections4.set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UnmodifiableSortedSet_subSetTest {

    private SortedSet<Integer> originalSet;
    private UnmodifiableSortedSet<Integer> unmodifiableSet;

    @BeforeEach
    public void setUp() {
        originalSet = new TreeSet<>(Arrays.asList(1, 2, 3, 4, 5));
        unmodifiableSet = UnmodifiableSortedSet.unmodifiableSortedSet(originalSet);
    }

    @Test
    public void testSubSetValidRange() {
        SortedSet<Integer> subSet = unmodifiableSet.subSet(2, 4);
        assertEquals(2, subSet.size());
        assertTrue(subSet.contains(2));
        assertTrue(subSet.contains(3));
    }

    @Test
    public void testSubSetInvalidRange() {
        assertThrows(IllegalArgumentException.class, () -> unmodifiableSet.subSet(4, 2));
    }

    @Test
    public void testSubSetUnmodifiable() {
        SortedSet<Integer> subSet = unmodifiableSet.subSet(2, 4);
        assertThrows(UnsupportedOperationException.class, () -> subSet.add(3));
    }

    @Test
    public void testSubSetEmptyRange() {
        SortedSet<Integer> subSet = unmodifiableSet.subSet(3, 3);
        assertEquals(0, subSet.size());
    }
}
