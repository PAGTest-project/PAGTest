
package org.apache.commons.collections4.iterators;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ZippingIterator_hasNextTest {

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
    public void testHasNextWithNextIteratorSet() {
        @SuppressWarnings("unchecked")
        final ZippingIterator<Integer> iter = new ZippingIterator<>(evens.iterator());
        iter.next(); // This will set nextIterator
        assertTrue(iter.hasNext());
    }

    @Test
    public void testHasNextWithNoNextIteratorSet() {
        @SuppressWarnings("unchecked")
        final ZippingIterator<Integer> iter = new ZippingIterator<>(evens.iterator());
        assertFalse(iter.hasNext()); // No nextIterator set initially
    }

    @Test
    public void testHasNextWithAllIteratorsExhausted() {
        @SuppressWarnings("unchecked")
        final ZippingIterator<Integer> iter = new ZippingIterator<>(evens.iterator());
        while (iter.hasNext()) {
            iter.next();
        }
        assertFalse(iter.hasNext());
    }

    @Test
    public void testHasNextWithMultipleIterators() {
        @SuppressWarnings("unchecked")
        final ZippingIterator<Integer> iter = new ZippingIterator<>(evens.iterator(), odds.iterator());
        assertTrue(iter.hasNext());
    }

    @Test
    public void testHasNextWithMixedExhaustedIterators() {
        @SuppressWarnings("unchecked")
        final ZippingIterator<Integer> iter = new ZippingIterator<>(evens.iterator(), odds.iterator());
        while (iter.hasNext()) {
            iter.next();
        }
        assertFalse(iter.hasNext());
    }
}
