
package org.apache.commons.collections4.queue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CircularFifoQueue_pollTest {

    private CircularFifoQueue<String> queue;

    @BeforeEach
    public void setUp() {
        queue = new CircularFifoQueue<>(5);
    }

    @Test
    public void testPollWhenQueueIsEmpty() {
        assertNull(queue.poll());
    }

    @Test
    public void testPollWhenQueueHasElements() {
        queue.add("1");
        queue.add("2");
        queue.add("3");

        assertEquals("1", queue.poll());
        assertEquals("2", queue.poll());
        assertEquals("3", queue.poll());
        assertTrue(queue.isEmpty());
    }

    @Test
    public void testPollWhenQueueIsFull() {
        queue.add("1");
        queue.add("2");
        queue.add("3");
        queue.add("4");
        queue.add("5");

        assertEquals("1", queue.poll());
        assertEquals("2", queue.poll());
        assertEquals("3", queue.poll());
        assertEquals("4", queue.poll());
        assertEquals("5", queue.poll());
        assertTrue(queue.isEmpty());
    }

    @Test
    public void testPollAfterAddingAndRemovingElements() {
        queue.add("1");
        queue.add("2");
        queue.add("3");
        queue.add("4");
        queue.add("5");
        queue.add("6");

        assertEquals("2", queue.poll());
        assertEquals("3", queue.poll());
        assertEquals("4", queue.poll());
        assertEquals("5", queue.poll());
        assertEquals("6", queue.poll());
        assertTrue(queue.isEmpty());
    }
}
