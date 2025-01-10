
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
    public void testPeekWithEmptyQueue() {
        assertNull(queue.peek());
    }

    @Test
    public void testPeekWithNonEmptyQueue() {
        queue.add("1");
        queue.add("2");
        assertEquals("1", queue.peek());
    }

    @Test
    public void testPeekAfterAddingAndRemovingElements() {
        queue.add("1");
        queue.add("2");
        queue.add("3");
        queue.remove();
        assertEquals("2", queue.peek());
    }

    @Test
    public void testPeekAfterFullQueue() {
        queue.add("1");
        queue.add("2");
        queue.add("3");
        queue.add("4");
        assertEquals("2", queue.peek());
    }

    @Test
    public void testPeekAfterClear() {
        queue.add("1");
        queue.add("2");
        queue.clear();
        assertNull(queue.peek());
    }
}
