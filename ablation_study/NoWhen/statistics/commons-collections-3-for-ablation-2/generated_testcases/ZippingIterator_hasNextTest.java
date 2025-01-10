
package org.apache.commons.collections4.iterators;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Iterator;

import org.apache.commons.collections4.IteratorUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ZippingIterator_hasNextTest {

    private ArrayList<Integer> evens;
    private ArrayList<Integer> odds;
    private ArrayList<Integer> fib;

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
    public void testHasNextWithElements() {
        final ZippingIterator<Integer> iter = new ZippingIterator<>(fib.iterator(), evens.iterator(), odds.iterator());
        assertTrue(iter.hasNext());
    }

    @Test
    public void testHasNextWithoutElements() {
        final ZippingIterator<Integer> iter = new ZippingIterator<>(IteratorUtils.emptyIterator(), IteratorUtils.emptyIterator(), IteratorUtils.emptyIterator());
        assertFalse(iter.hasNext());
    }

    @Test
    public void testHasNextWithOneExhaustedIterator() {
        final ArrayList<Integer> emptyList = new ArrayList<>();
        final ZippingIterator<Integer> iter = new ZippingIterator<>(emptyList.iterator(), evens.iterator(), odds.iterator());
        assertTrue(iter.hasNext());
    }

    @Test
    public void testHasNextWithAllExhaustedIterators() {
        final ArrayList<Integer> emptyList = new ArrayList<>();
        final ZippingIterator<Integer> iter = new ZippingIterator<>(emptyList.iterator(), emptyList.iterator(), emptyList.iterator());
        assertFalse(iter.hasNext());
    }

    @Test
    public void testHasNextWithMultipleCalls() {
        final ZippingIterator<Integer> iter = new ZippingIterator<>(fib.iterator(), evens.iterator(), odds.iterator());
        assertTrue(iter.hasNext());
        assertTrue(iter.hasNext());
        assertTrue(iter.hasNext());
    }
}
