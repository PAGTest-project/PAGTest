
package org.apache.commons.collections4.queue;

import static org.junit.jupiter.api.Assertions.*;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CircularFifoQueue_removeTest {

    private CircularFifoQueue<String> queue;

    @BeforeEach
    public void setUp() {
        queue = new CircularFifoQueue<>(5);
    }

    @Test
    public void testRemoveWhenQueueIsEmpty() {
        assertThrows(NoSuchElementException.class, () -> queue.remove());
    }

    @Test
    public void testRemoveWhenQueueHasOneElement() {
        queue.add("1");
        assertEquals("1", queue.remove());
        assertTrue(queue.isEmpty());
    }

    @Test
    public void testRemoveWhenQueueIsFull() {
        queue.add("1");
        queue.add("2");
        queue.add("3");
        queue.add("4");
        queue.add("5");
        assertEquals("1", queue.remove());
        assertFalse(queue.isFull());
        assertEquals(4, queue.size());
    }

    @Test
    public void testRemoveWhenQueueWrapsAround() {
        queue.add("1");
        queue.add("2");
        queue.add("3");
        queue.add("4");
        queue.add("5");
        queue.add("6");
        assertEquals("2", queue.remove());
        assertEquals(4, queue.size());
        assertEquals("[3, 4, 5, 6]", queue.toString());
    }

    @Test
    public void testRemoveMultipleElements() {
        queue.add("1");
        queue.add("2");
        queue.add("3");
        queue.add("4");
        queue.add("5");
        assertEquals("1", queue.remove());
        assertEquals("2", queue.remove());
        assertEquals("3", queue.remove());
        assertEquals(2, queue.size());
        assertEquals("[4, 5]", queue.toString());
    }
}
