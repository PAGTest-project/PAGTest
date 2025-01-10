
package org.apache.commons.collections4.iterators;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import org.apache.commons.collections4.comparators.ComparableComparator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CollatingIterator_getIteratorIndexTest {

    private Comparator<Integer> comparator;
    private ArrayList<Integer> evens;
    private ArrayList<Integer> odds;

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
    }

    @Test
    public void testGetIteratorIndexSuccess() {
        CollatingIterator<Integer> iter = new CollatingIterator<>(comparator);
        iter.addIterator(evens.iterator());
        iter.addIterator(odds.iterator());

        iter.next(); // This should set lastReturned
        int index = iter.getIteratorIndex();
        assertEquals(0, index); // Assuming the first iterator (evens) returned the first element
    }

    @Test
    public void testGetIteratorIndexNoValueReturnedYet() {
        CollatingIterator<Integer> iter = new CollatingIterator<>(comparator);
        iter.addIterator(evens.iterator());
        iter.addIterator(odds.iterator());

        assertThrows(IllegalStateException.class, () -> {
            iter.getIteratorIndex();
        });
    }
}
