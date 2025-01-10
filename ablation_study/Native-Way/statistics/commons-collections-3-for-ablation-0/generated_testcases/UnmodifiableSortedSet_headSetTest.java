
package org.apache.commons.collections4.set;

import static org.junit.jupiter.api.Assertions.*;

import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.apache.commons.collections4.set.UnmodifiableSortedSet;

public class UnmodifiableSortedSet_headSetTest {

    private SortedSet<Integer> originalSet;
    private UnmodifiableSortedSet<Integer> unmodifiableSet;

    @BeforeEach
    public void setUp() {
        originalSet = new TreeSet<>(Arrays.asList(1, 2, 3, 4, 5));
        unmodifiableSet = UnmodifiableSortedSet.unmodifiableSortedSet(originalSet);
    }

    @Test
    public void testHeadSet() {
        SortedSet<Integer> headSet = unmodifiableSet.headSet(3);
        assertEquals(2, headSet.size());
        assertTrue(headSet.contains(1));
        assertTrue(headSet.contains(2));
        assertFalse(headSet.contains(3));
        assertFalse(headSet.contains(4));
        assertFalse(headSet.contains(5));
    }

    @Test
    public void testHeadSetWithBoundaryElement() {
        SortedSet<Integer> headSet = unmodifiableSet.headSet(1);
        assertEquals(0, headSet.size());
        assertFalse(headSet.contains(1));
        assertFalse(headSet.contains(2));
        assertFalse(headSet.contains(3));
        assertFalse(headSet.contains(4));
        assertFalse(headSet.contains(5));
    }

    @Test
    public void testHeadSetWithNonExistentElement() {
        SortedSet<Integer> headSet = unmodifiableSet.headSet(0);
        assertEquals(0, headSet.size());
        assertFalse(headSet.contains(1));
        assertFalse(headSet.contains(2));
        assertFalse(headSet.contains(3));
        assertFalse(headSet.contains(4));
        assertFalse(headSet.contains(5));
    }

    @Test
    public void testHeadSetWithUnmodifiableResult() {
        SortedSet<Integer> headSet = unmodifiableSet.headSet(3);
        assertThrows(UnsupportedOperationException.class, () -> headSet.add(0));
    }
}
