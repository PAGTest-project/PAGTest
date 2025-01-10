
package org.apache.commons.collections4.set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UnmodifiableSortedSet_tailSetTest {

    private SortedSet<Integer> originalSet;
    private UnmodifiableSortedSet<Integer> unmodifiableSet;

    @BeforeEach
    public void setUp() {
        originalSet = new TreeSet<>(Arrays.asList(1, 2, 3, 4, 5));
        unmodifiableSet = new UnmodifiableSortedSet<>(originalSet);
    }

    @Test
    public void testTailSet() {
        SortedSet<Integer> tailSet = unmodifiableSet.tailSet(3);
        assertEquals(3, tailSet.first());
        assertEquals(5, tailSet.last());
        assertTrue(tailSet.containsAll(Arrays.asList(3, 4, 5)));
    }

    @Test
    public void testTailSetWithInvalidElement() {
        assertThrows(IllegalArgumentException.class, () -> unmodifiableSet.tailSet(6));
    }

    @Test
    public void testTailSetWithNullElement() {
        assertThrows(NullPointerException.class, () -> unmodifiableSet.tailSet(null));
    }
}
