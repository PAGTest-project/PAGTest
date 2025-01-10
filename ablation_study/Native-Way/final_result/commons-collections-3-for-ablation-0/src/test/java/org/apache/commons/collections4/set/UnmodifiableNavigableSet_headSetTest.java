
package org.apache.commons.collections4.set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NavigableSet;
import java.util.SortedSet;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UnmodifiableNavigableSet_headSetTest {

    private UnmodifiableNavigableSet<Integer> set;

    @BeforeEach
    public void setupSet() {
        NavigableSet<Integer> originalSet = new TreeSet<>();
        originalSet.add(1);
        originalSet.add(2);
        originalSet.add(3);
        set = (UnmodifiableNavigableSet<Integer>) UnmodifiableNavigableSet.unmodifiableNavigableSet(originalSet);
    }

    @Test
    public void testHeadSet() {
        SortedSet<Integer> headSet = set.headSet(3);
        assertEquals(2, headSet.size());
        assertEquals(1, headSet.first());
        assertEquals(2, headSet.last());
    }

    @Test
    public void testHeadSetInclusive() {
        NavigableSet<Integer> headSet = set.headSet(3, true);
        assertEquals(3, headSet.size());
        assertEquals(1, headSet.first());
        assertEquals(3, headSet.last());
    }

    @Test
    public void testHeadSetModification() {
        SortedSet<Integer> headSet = set.headSet(3);
        assertThrows(UnsupportedOperationException.class, () -> headSet.add(4));
    }

    @Test
    public void testHeadSetInclusiveModification() {
        NavigableSet<Integer> headSet = set.headSet(3, true);
        assertThrows(UnsupportedOperationException.class, () -> headSet.add(4));
    }
}
