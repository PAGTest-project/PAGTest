
package org.apache.commons.collections4.iterators;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ZippingIterator_removeTest {

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
    public void testRemoveSuccess() {
        @SuppressWarnings("unchecked")
        final ZippingIterator<Integer> iter = new ZippingIterator<>(evens.iterator());
        iter.next(); // Move to the first element
        iter.remove(); // Remove the first element
        assertFalse(evens.contains(0));
    }

    @Test
    public void testRemoveWithoutNext() {
        @SuppressWarnings("unchecked")
        final ZippingIterator<Integer> iter = new ZippingIterator<>(evens.iterator());
        assertThrows(IllegalStateException.class, () -> iter.remove());
    }

    @Test
    public void testRemoveTwice() {
        @SuppressWarnings("unchecked")
        final ZippingIterator<Integer> iter = new ZippingIterator<>(evens.iterator());
        iter.next(); // Move to the first element
        iter.remove(); // Remove the first element
        assertThrows(IllegalStateException.class, () -> iter.remove());
    }

    @Test
    public void testRemoveAfterExhausted() {
        @SuppressWarnings("unchecked")
        final ZippingIterator<Integer> iter = new ZippingIterator<>(evens.iterator());
        while (iter.hasNext()) {
            iter.next();
        }
        assertThrows(IllegalStateException.class, () -> iter.remove());
    }
}
