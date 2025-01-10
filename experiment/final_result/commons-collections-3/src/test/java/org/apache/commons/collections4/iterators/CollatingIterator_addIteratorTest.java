
package org.apache.commons.collections4.iterators;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import org.apache.commons.collections4.comparators.ComparableComparator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CollatingIterator_addIteratorTest {

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
    public void testAddIteratorSuccess() {
        CollatingIterator<Integer> iter = new CollatingIterator<>(comparator);
        Iterator<Integer> evenIterator = evens.iterator();
        iter.addIterator(evenIterator);
        assertEquals(1, iter.getIterators().size());
        assertSame(evenIterator, iter.getIterators().get(0));
    }

    @Test
    public void testAddIteratorNull() {
        CollatingIterator<Integer> iter = new CollatingIterator<>(comparator);
        assertThrows(NullPointerException.class, () -> iter.addIterator(null));
    }

    @Test
    public void testAddIteratorAfterStart() {
        CollatingIterator<Integer> iter = new CollatingIterator<>(comparator);
        iter.addIterator(evens.iterator());
        iter.hasNext(); // This will start the iteration
        assertThrows(IllegalStateException.class, () -> iter.addIterator(odds.iterator()));
    }
}
