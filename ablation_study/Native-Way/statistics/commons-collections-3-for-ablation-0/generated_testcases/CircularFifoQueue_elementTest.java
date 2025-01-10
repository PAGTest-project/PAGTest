
package org.apache.commons.collections4.queue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CircularFifoQueue_elementTest {
    private CircularFifoQueue<String> queue;

    @BeforeEach
    public void setUp() {
        queue = new CircularFifoQueue<>(3);
    }

    @Test
    public void testElementWhenQueueIsEmpty() {
        assertThrows(NoSuchElementException.class, () -> {
            queue.element();
        });
    }

    @Test
    public void testElementWhenQueueHasElements() {
        queue.add("element1");
        queue.add("element2");

        assertEquals("element1", queue.element());
    }

    @Test
    public void testElementAfterAddingAndRemovingElements() {
        queue.add("element1");
        queue.add("element2");
        queue.add("element3");
        queue.remove();

        assertEquals("element2", queue.element());
    }
}
