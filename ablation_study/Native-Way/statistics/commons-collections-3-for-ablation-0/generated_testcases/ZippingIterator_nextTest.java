
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
    public void testNextWithValidIterator() {
        @SuppressWarnings("unchecked")
        final ZippingIterator<Integer> iter = new ZippingIterator<>(evens.iterator());
        for (final Integer even : evens) {
            assertTrue(iter.hasNext());
            assertEquals(even, iter.next());
        }
        assertFalse(iter.hasNext());
    }

    @Test
    public void testNextWithNoSuchElementException() {
        @SuppressWarnings("unchecked")
        final ZippingIterator<Integer> iter = new ZippingIterator<>(evens.iterator());
        // Consume all elements
        while (iter.hasNext()) {
            iter.next();
        }
        // Attempt to call next() when no elements are left
        assertThrows(NoSuchElementException.class, () -> {
            iter.next();
        });
    }

    @Test
    public void testNextWithMultipleIterators() {
        @SuppressWarnings("unchecked")
        final ZippingIterator<Integer> iter = new ZippingIterator<>(evens.iterator(), odds.iterator(), fib.iterator());
        int count = 0;
        while (iter.hasNext()) {
            iter.next();
            count++;
        }
        // Ensure all elements from all iterators are consumed
        assertEquals(evens.size() + odds.size() + fib.size(), count);
    }
}
