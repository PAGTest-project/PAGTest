
package org.apache.commons.collections4.iterators;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
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
        final ZippingIterator<Integer> iter = new ZippingIterator<>(evens.iterator(), odds.iterator());
        iter.next(); // This will set nextIterator
        assertTrue(iter.hasNext());
    }

    @Test
    public void testHasNextWithNoNextIteratorSet() {
        final ZippingIterator<Integer> iter = new ZippingIterator<>(evens.iterator(), odds.iterator());
        assertTrue(iter.hasNext());
    }

    @Test
    public void testHasNextWithAllIteratorsExhausted() {
        final ZippingIterator<Integer> iter = new ZippingIterator<>(IteratorUtils.emptyIterator(), IteratorUtils.emptyIterator());
        assertFalse(iter.hasNext());
    }

    @Test
    public void testHasNextWithSomeIteratorsExhausted() {
        final ZippingIterator<Integer> iter = new ZippingIterator<>(evens.iterator(), IteratorUtils.emptyIterator());
        assertTrue(iter.hasNext());
    }
}
