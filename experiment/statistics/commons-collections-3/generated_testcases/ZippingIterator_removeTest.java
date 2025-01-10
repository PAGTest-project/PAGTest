
package org.apache.commons.collections4.iterators;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Iterator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ZippingIterator_removeTest {

    private ArrayList<Integer> evens;
    private ZippingIterator<Integer> zippingIterator;

    @BeforeEach
    public void setUp() throws Exception {
        evens = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            if (0 == i % 2) {
                evens.add(i);
            }
        }
        zippingIterator = new ZippingIterator<>(evens.iterator(), evens.iterator());
    }

    @Test
    public void testRemoveWithoutNext() {
        assertThrows(IllegalStateException.class, () -> zippingIterator.remove());
    }

    @Test
    public void testRemoveAfterNext() {
        zippingIterator.next(); // Ensure lastReturned is set
        zippingIterator.remove();
        assertThrows(IllegalStateException.class, () -> zippingIterator.remove());
    }
}
