
package org.apache.commons.collections4.iterators;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
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
        CollatingIterator<Integer> iter = new CollatingIterator<>(comparator);
        iter.addIterator(evens.iterator());
        iter.addIterator(odds.iterator());

        List<Integer> expected = new ArrayList<>(evens);
        expected.addAll(odds);
        expected.sort(comparator);

        for (Integer value : expected) {
            assertEquals(value, iter.next());
        }
    }

    @Test
    public void testNextWithoutElements() {
        CollatingIterator<Integer> iter = new CollatingIterator<>(comparator);
        assertThrows(NoSuchElementException.class, iter::next);
    }

    @Test
    public void testNextWithEmptyIterator() {
        CollatingIterator<Integer> iter = new CollatingIterator<>(comparator);
        iter.addIterator(evens.iterator());
        iter.addIterator(Arrays.asList().iterator());

        List<Integer> expected = new ArrayList<>(evens);
        expected.sort(comparator);

        for (Integer value : expected) {
            assertEquals(value, iter.next());
        }
    }

    @Test
    public void testNextWithSingleIterator() {
        CollatingIterator<Integer> iter = new CollatingIterator<>(comparator);
        iter.addIterator(evens.iterator());

        List<Integer> expected = new ArrayList<>(evens);
        expected.sort(comparator);

        for (Integer value : expected) {
            assertEquals(value, iter.next());
        }
    }

    @Test
    public void testNextWithMultipleIterators() {
        CollatingIterator<Integer> iter = new CollatingIterator<>(comparator);
        iter.addIterator(evens.iterator());
        iter.addIterator(odds.iterator());
        iter.addIterator(fib.iterator());

        List<Integer> expected = new ArrayList<>(evens);
        expected.addAll(odds);
        expected.addAll(fib);
        expected.sort(comparator);

        for (Integer value : expected) {
            assertEquals(value, iter.next());
        }
    }
}
