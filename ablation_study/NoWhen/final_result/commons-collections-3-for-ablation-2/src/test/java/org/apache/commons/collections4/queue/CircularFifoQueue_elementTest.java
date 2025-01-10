
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
        queue = new CircularFifoQueue<>(5);
    }

    @Test
    public void testElementWhenQueueIsEmpty() {
        assertThrows(NoSuchElementException.class, () -> {
            queue.element();
        });
    }

    @Test
    public void testElementWhenQueueHasElements() {
        queue.add("1");
        queue.add("2");
        queue.add("3");

        assertEquals("1", queue.element());
    }

    @Test
    public void testElementAfterAddingAndRemovingElements() {
        queue.add("1");
        queue.add("2");
        queue.add("3");
        queue.remove();

        assertEquals("2", queue.element());
    }

    @Test
    public void testElementAfterQueueIsFull() {
        queue.add("1");
        queue.add("2");
        queue.add("3");
        queue.add("4");
        queue.add("5");
        queue.add("6");

        assertEquals("2", queue.element());
    }

    @Test
    public void testElementAfterClearingQueue() {
        queue.add("1");
        queue.add("2");
        queue.clear();

        assertThrows(NoSuchElementException.class, () -> {
            queue.element();
        });
    }
}
