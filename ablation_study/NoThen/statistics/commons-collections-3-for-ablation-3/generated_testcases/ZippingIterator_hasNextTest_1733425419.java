
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
    public void testHasNextWithSingleIterator() {
        final ZippingIterator<Integer> iter = new ZippingIterator<>(evens.iterator());
        for (final Integer even : evens) {
            assertTrue(iter.hasNext());
            assertEquals(even, iter.next());
        }
        assertFalse(iter.hasNext());
    }

    @Test
    public void testHasNextWithMultipleIterators() {
        final ZippingIterator<Integer> iter = new ZippingIterator<>(evens.iterator(), odds.iterator(), fib.iterator());
        for (int i = 0; i < 20; i++) {
            assertTrue(iter.hasNext());
            iter.next();
        }
        assertFalse(iter.hasNext());
    }

    @Test
    public void testHasNextWithExhaustedIterator() {
        final List<Integer> shortList = new ArrayList<>();
        shortList.add(1);
        final ZippingIterator<Integer> iter = new ZippingIterator<>(shortList.iterator(), evens.iterator());
        assertTrue(iter.hasNext());
        assertEquals(1, iter.next());
        for (final Integer even : evens) {
            assertTrue(iter.hasNext());
            assertEquals(even, iter.next());
        }
        assertFalse(iter.hasNext());
    }

    @Test
    public void testHasNextWithAllExhaustedIterators() {
        final List<Integer> emptyList = new ArrayList<>();
        final ZippingIterator<Integer> iter = new ZippingIterator<>(emptyList.iterator(), emptyList.iterator());
        assertFalse(iter.hasNext());
    }
}
