
package org.apache.commons.collections4.iterators;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.commons.collections4.comparators.ComparableComparator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CollatingIterator_nextTest {

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
    public void testNextWithElements() {
        final CollatingIterator<Integer> iter = new CollatingIterator<>(comparator);
        iter.addIterator(evens.iterator());
        iter.addIterator(odds.iterator());

        for (int i = 0; i < 20; i++) {
            assertTrue(iter.hasNext());
            assertEquals(i, iter.next().intValue());
        }
        assertFalse(iter.hasNext());
    }

    @Test
    public void testNextWithoutElements() {
        final CollatingIterator<Integer> iter = new CollatingIterator<>(comparator);
        iter.addIterator(new ArrayList<Integer>().iterator());

        assertFalse(iter.hasNext());
        assertThrows(NoSuchElementException.class, () -> iter.next());
    }

    @Test
    public void testNextWithSingleIterator() {
        final CollatingIterator<Integer> iter = new CollatingIterator<>(comparator);
        iter.addIterator(evens.iterator());

        for (int i = 0; i < evens.size(); i++) {
            assertTrue(iter.hasNext());
            assertEquals(evens.get(i), iter.next());
        }
        assertFalse(iter.hasNext());
    }

    @Test
    public void testNextWithMultipleIterators() {
        final CollatingIterator<Integer> iter = new CollatingIterator<>(comparator);
        iter.addIterator(evens.iterator());
        iter.addIterator(odds.iterator());
        iter.addIterator(fib.iterator());

        List<Integer> combined = new ArrayList<>();
        combined.addAll(evens);
        combined.addAll(odds);
        combined.addAll(fib);
        combined.sort(comparator);

        for (Integer value : combined) {
            assertTrue(iter.hasNext());
            assertEquals(value, iter.next());
        }
        assertFalse(iter.hasNext());
    }

    @Test
    public void testNextWithNoComparator() {
        final CollatingIterator<Integer> iter = new CollatingIterator<>();
        iter.addIterator(evens.iterator());

        assertThrows(IllegalStateException.class, () -> iter.next());
    }
}
