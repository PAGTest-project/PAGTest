
package org.apache.commons.collections4.iterators;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ZippingIterator_nextTest {

    private List<Integer> evens;
    private List<Integer> odds;
    private List<Integer> fib;

    @BeforeEach
    public void setUp() throws Exception {
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
        final ZippingIterator<Integer> iter = new ZippingIterator<>(evens.iterator(), odds.iterator());
        for (int i = 0; i < 20; i++) {
            assertTrue(iter.hasNext());
            assertEquals(i, iter.next());
        }
        assertFalse(iter.hasNext());
    }

    @Test
    public void testNextWithoutElements() {
        final ZippingIterator<Integer> iter = new ZippingIterator<>(new ArrayList<Integer>().iterator());
        assertFalse(iter.hasNext());
        assertThrows(NoSuchElementException.class, () -> iter.next());
    }

    @Test
    public void testNextWithOneEmptyIterator() {
        final ZippingIterator<Integer> iter = new ZippingIterator<>(evens.iterator(), new ArrayList<Integer>().iterator());
        for (final Integer even : evens) {
            assertTrue(iter.hasNext());
            assertEquals(even, iter.next());
        }
        assertFalse(iter.hasNext());
    }

    @Test
    public void testNextWithMultipleIterators() {
        final ZippingIterator<Integer> iter = new ZippingIterator<>(evens.iterator(), odds.iterator(), fib.iterator());
        int expected = 0;
        while (iter.hasNext()) {
            assertTrue(iter.hasNext());
            int actual = iter.next();
            if (expected == 0) {
                assertEquals(1, actual); // First element from fib list
            } else if (expected == 1) {
                assertEquals(1, actual); // Second element from fib list
            } else if (expected == 2) {
                assertEquals(2, actual); // Third element from fib list
            } else if (expected == 3) {
                assertEquals(3, actual); // Fourth element from fib list
            } else if (expected == 4) {
                assertEquals(5, actual); // Fifth element from fib list
            } else if (expected == 5) {
                assertEquals(8, actual); // Sixth element from fib list
            } else if (expected == 6) {
                assertEquals(13, actual); // Seventh element from fib list
            } else if (expected == 7) {
                assertEquals(21, actual); // Eighth element from fib list
            } else {
                assertEquals(expected, actual); // Remaining elements from evens and odds lists
            }
            expected++;
            if (expected % 2 == 0) {
                expected++; // Skip odd numbers to match the expected sequence
            }
        }
        assertFalse(iter.hasNext());
    }
}
