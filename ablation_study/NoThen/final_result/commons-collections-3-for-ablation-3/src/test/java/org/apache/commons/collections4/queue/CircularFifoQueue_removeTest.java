
package org.apache.commons.collections4.queue;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.NoSuchElementException;

public class CircularFifoQueue_removeTest {
    private CircularFifoQueue<String> queue;

    @BeforeEach
    public void setUp() {
        queue = new CircularFifoQueue<>(5);
    }

    @Test
    public void testRemoveSuccess() {
        queue.add("1");
        queue.add("2");
        queue.add("3");
        assertEquals("1", queue.remove());
        assertEquals(2, queue.size());
    }

    @Test
    public void testRemoveFromEmptyQueue() {
        assertThrows(NoSuchElementException.class, () -> queue.remove());
    }

    @Test
    public void testRemoveWithWrapAround() {
        queue.add("1");
        queue.add("2");
        queue.add("3");
        queue.add("4");
        queue.add("5");
        queue.add("6"); // This will wrap around
        assertEquals("2", queue.remove());
        assertEquals(4, queue.size());
    }

    @Test
    public void testRemoveAllElements() {
        queue.add("1");
        queue.add("2");
        queue.add("3");
        queue.remove();
        queue.remove();
        queue.remove();
        assertTrue(queue.isEmpty());
    }

    @Test
    public void testRemoveAfterClear() {
        queue.add("1");
        queue.add("2");
        queue.clear();
        assertThrows(NoSuchElementException.class, () -> queue.remove());
    }
}
