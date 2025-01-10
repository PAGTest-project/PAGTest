
package org.apache.commons.collections4.iterators;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;

import org.apache.commons.collections4.comparators.ComparableComparator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CollatingIterator_getIteratorIndexTest {

    private Comparator<Integer> comparator;
    private ArrayList<Integer> evens;
    private ArrayList<Integer> odds;
    private CollatingIterator<Integer> iter;

    @BeforeEach
    public void setUp() throws Exception {
        comparator = new ComparableComparator<>();
        evens = new ArrayList<>();
        odds = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            if (0 == i % 2) {
                evens.add(i);
            } else {
                odds.add(i);
            }
        }
        iter = new CollatingIterator<>(comparator);
        iter.addIterator(evens.iterator());
        iter.addIterator(odds.iterator());
    }

    @Test
    public void testGetIteratorIndexSuccess() {
        iter.next(); // Ensure lastReturned is set
        assertEquals(0, iter.getIteratorIndex());
    }

    @Test
    public void testGetIteratorIndexThrowsIllegalStateException() {
        assertThrows(IllegalStateException.class, () -> iter.getIteratorIndex());
    }
}
