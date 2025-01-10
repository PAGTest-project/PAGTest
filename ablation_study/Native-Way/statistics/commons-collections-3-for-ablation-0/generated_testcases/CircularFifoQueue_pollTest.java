
package org.apache.commons.collections4.queue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CircularFifoQueue_pollTest {

    private CircularFifoQueue<String> queue;

    @BeforeEach
    public void setUp() {
        queue = new CircularFifoQueue<>(3);
    }

    @Test
    public void testPollEmptyQueue() {
        assertNull(queue.poll());
    }

    @Test
    public void testPollSingleElement() {
        queue.add("element1");
        assertEquals("element1", queue.poll());
        assertTrue(queue.isEmpty());
    }

    @Test
    public void testPollMultipleElements() {
        queue.add("element1");
        queue.add("element2");
        queue.add("element3");

        assertEquals("element1", queue.poll());
        assertEquals("element2", queue.poll());
        assertEquals("element3", queue.poll());

        assertTrue(queue.isEmpty());
    }

    @Test
    public void testPollAfterWrapAround() {
        queue.add("element1");
        queue.add("element2");
        queue.add("element3");
        queue.add("element4");

        assertEquals("element2", queue.poll());
        assertEquals("element3", queue.poll());
        assertEquals("element4", queue.poll());

        assertTrue(queue.isEmpty());
    }

    @Test
    public void testPollAfterRemove() {
        queue.add("element1");
        queue.add("element2");
        queue.add("element3");

        queue.remove();
        assertEquals("element2", queue.poll());
        assertEquals("element3", queue.poll());

        assertTrue(queue.isEmpty());
    }

    @Test
    public void testPollAfterClear() {
        queue.add("element1");
        queue.add("element2");
        queue.clear();

        assertNull(queue.poll());
        assertTrue(queue.isEmpty());
    }
}
