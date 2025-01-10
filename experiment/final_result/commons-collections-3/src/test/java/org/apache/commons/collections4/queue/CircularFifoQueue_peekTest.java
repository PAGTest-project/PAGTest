
package org.apache.commons.collections4.queue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CircularFifoQueue_peekTest {

    private CircularFifoQueue<String> queue;

    @BeforeEach
    public void setUp() {
        queue = new CircularFifoQueue<>(3);
    }

    @Test
    public void testPeekOnEmptyQueue() {
        assertNull(queue.peek());
    }

    @Test
    public void testPeekOnNonEmptyQueue() {
        queue.add("Element1");
        queue.add("Element2");
        assertEquals("Element1", queue.peek());
    }

    @Test
    public void testPeekAfterAddingAndRemovingElements() {
        queue.add("Element1");
        queue.add("Element2");
        queue.add("Element3");
        queue.remove();
        assertEquals("Element2", queue.peek());
    }
}
