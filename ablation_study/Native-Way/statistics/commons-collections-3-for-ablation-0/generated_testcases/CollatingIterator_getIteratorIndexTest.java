
package org.apache.commons.collections4.iterators;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.apache.commons.collections4.comparators.ComparableComparator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CollatingIterator_getIteratorIndexTest {

    private Comparator<Integer> comparator;
    private List<Integer> evens;
    private List<Integer> odds;
    private List<Integer> fib;

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
        fib = new ArrayList<>();
        fib.add(1);
        fib.add(1);
        fib.add(2);
        fib.add(3);
        fib.add(5);
        fib.add(8);
        fib.add(13);
        fib.add(21);
    }

    @Test
    public void testGetIteratorIndexSuccess() {
        final CollatingIterator<Integer> iter = new CollatingIterator<>(comparator);
        iter.addIterator(evens.iterator());
        iter.addIterator(odds.iterator());

        iter.next(); // This should set lastReturned to 0 (first iterator)
        assertEquals(0, iter.getIteratorIndex());
    }

    @Test
    public void testGetIteratorIndexNoValueReturnedYet() {
        final CollatingIterator<Integer> iter = new CollatingIterator<>(comparator);
        iter.addIterator(evens.iterator());

        assertThrows(IllegalStateException.class, () -> {
            iter.getIteratorIndex();
        });
    }
}
