
package org.apache.commons.collections4.queue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
    public void testRemoveSingleElement() {
        queue.add("1");
        assertEquals("1", queue.remove());
        assertEquals(0, queue.size());
    }

    @Test
    public void testRemoveMultipleElements() {
        queue.add("1");
        queue.add("2");
        queue.add("3");
        assertEquals("1", queue.remove());
        assertEquals("2", queue.remove());
        assertEquals("3", queue.peek());
        assertEquals(1, queue.size());
    }

    @Test
    public void testRemoveWithWrapAround() {
        queue.add("1");
        queue.add("2");
        queue.add("3");
        queue.add("4");
        queue.add("5");
        queue.add("6");
        assertEquals("2", queue.remove());
        assertEquals("3", queue.remove());
        assertEquals("4", queue.peek());
        assertEquals(3, queue.size());
    }

    @Test
    public void testRemoveAllElements() {
        queue.add("1");
        queue.add("2");
        queue.add("3");
        queue.add("4");
        queue.add("5");
        assertEquals("1", queue.remove());
        assertEquals("2", queue.remove());
        assertEquals("3", queue.remove());
        assertEquals("4", queue.remove());
        assertEquals("5", queue.remove());
        assertEquals(0, queue.size());
        assertThrows(NoSuchElementException.class, () -> queue.remove());
    }
}
