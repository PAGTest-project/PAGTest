
package org.apache.commons.collections4.iterators;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ZippingIterator_nextTest {

    private ArrayList<Integer> evens;

    @BeforeEach
    public void setUp() throws Exception {
        evens = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            if (0 == i % 2) {
                evens.add(i);
            }
        }
    }

    @Test
    public void testNextWithElements() {
        final ZippingIterator<Integer> iter = new ZippingIterator<>(evens.iterator(), evens.iterator());
        assertTrue(iter.hasNext());
        assertEquals(0, iter.next());
        assertTrue(iter.hasNext());
        assertEquals(0, iter.next());
    }

    @Test
    public void testNextWithoutElements() {
        final ZippingIterator<Integer> iter = new ZippingIterator<>(new ArrayList<Integer>().iterator());
        assertFalse(iter.hasNext());
        assertThrows(NoSuchElementException.class, () -> iter.next());
    }
}
