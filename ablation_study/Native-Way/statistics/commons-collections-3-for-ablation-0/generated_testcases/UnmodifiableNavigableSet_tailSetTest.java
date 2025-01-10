
package org.apache.commons.collections4.set;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.NavigableSet;
import java.util.TreeSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UnmodifiableNavigableSet_tailSetTest {

    private UnmodifiableNavigableSet<Integer> set;

    @BeforeEach
    public void setupSet() {
        NavigableSet<Integer> originalSet = new TreeSet<>(java.util.Arrays.asList(1, 2, 3, 4, 5));
        set = (UnmodifiableNavigableSet<Integer>) UnmodifiableNavigableSet.unmodifiableNavigableSet(originalSet);
    }

    @Test
    public void testTailSet() {
        NavigableSet<Integer> tailSet = set.tailSet(3);
        assertTrue(tailSet.contains(3));
        assertTrue(tailSet.contains(4));
        assertTrue(tailSet.contains(5));
        assertThrows(UnsupportedOperationException.class, () -> tailSet.add(6));
    }

    @Test
    public void testTailSetInclusive() {
        NavigableSet<Integer> tailSet = set.tailSet(3, true);
        assertTrue(tailSet.contains(3));
        assertTrue(tailSet.contains(4));
        assertTrue(tailSet.contains(5));
        assertThrows(UnsupportedOperationException.class, () -> tailSet.add(6));
    }

    @Test
    public void testTailSetExclusive() {
        NavigableSet<Integer> tailSet = set.tailSet(3, false);
        assertTrue(tailSet.contains(4));
        assertTrue(tailSet.contains(5));
        assertThrows(UnsupportedOperationException.class, () -> tailSet.add(6));
    }
}
