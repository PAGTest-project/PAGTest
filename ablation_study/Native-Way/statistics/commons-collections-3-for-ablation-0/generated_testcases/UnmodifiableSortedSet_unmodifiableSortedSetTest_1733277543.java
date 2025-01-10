
package org.apache.commons.collections4.set;

import static org.junit.jupiter.api.Assertions.*;

import java.util.SortedSet;
import java.util.TreeSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UnmodifiableSortedSet_unmodifiableSortedSetTest {

    private SortedSet<Integer> set;

    @BeforeEach
    public void setUp() {
        set = new TreeSet<>(Arrays.asList(1, 2, 3, 4, 5));
    }

    @Test
    public void testUnmodifiableSortedSetWithUnmodifiableSet() {
        SortedSet<Integer> unmodifiableSet = UnmodifiableSortedSet.unmodifiableSortedSet(set);
        assertSame(unmodifiableSet, UnmodifiableSortedSet.unmodifiableSortedSet(unmodifiableSet));
    }

    @Test
    public void testUnmodifiableSortedSetWithModifiableSet() {
        SortedSet<Integer> unmodifiableSet = UnmodifiableSortedSet.unmodifiableSortedSet(set);
        assertNotSame(set, unmodifiableSet);
        assertTrue(unmodifiableSet instanceof Unmodifiable);
    }

    @Test
    public void testUnmodifiableSortedSetWithNullSet() {
        assertThrows(NullPointerException.class, () -> {
            UnmodifiableSortedSet.unmodifiableSortedSet(null);
        });
    }
}
