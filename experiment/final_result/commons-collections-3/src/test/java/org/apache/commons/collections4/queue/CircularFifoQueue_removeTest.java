
package org.apache.commons.collections4.queue;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.NoSuchElementException;

public class CircularFifoQueue_removeTest {

    private CircularFifoQueue<String> queue;

    @BeforeEach
    public void setUp() {
        queue = new CircularFifoQueue<>(3);
    }

    @Test
    public void testRemoveWhenQueueIsEmpty() {
        assertThrows(NoSuchElementException.class, () -> queue.remove());
    }

    @Test
    public void testRemoveWhenQueueHasElements() {
        queue.add("1");
        queue.add("2");
        queue.add("3");

        assertEquals("1", queue.remove());
        assertEquals(2, queue.size());
        assertFalse(queue.isFull());

        assertEquals("2", queue.remove());
        assertEquals(1, queue.size());
        assertFalse(queue.isFull());

        assertEquals("3", queue.remove());
        assertEquals(0, queue.size());
        assertTrue(queue.isEmpty());
    }

    @Test
    public void testRemoveWhenQueueWrapsAround() {
        queue.add("1");
        queue.add("2");
        queue.add("3");
        queue.add("4"); // This will overwrite "1"

        assertEquals("2", queue.remove());
        assertEquals(2, queue.size());
        assertFalse(queue.isFull());

        assertEquals("3", queue.remove());
        assertEquals(1, queue.size());
        assertFalse(queue.isFull());

        assertEquals("4", queue.remove());
        assertEquals(0, queue.size());
        assertTrue(queue.isEmpty());
    }
}
