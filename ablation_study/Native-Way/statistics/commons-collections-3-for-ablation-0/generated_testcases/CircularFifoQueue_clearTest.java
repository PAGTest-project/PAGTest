
package org.apache.commons.collections4.queue;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.NoSuchElementException;
import java.util.Iterator;

public class CircularFifoQueue_clearTest {

    private CircularFifoQueue<String> queue;

    @BeforeEach
    public void setUp() {
        queue = new CircularFifoQueue<>(3);
    }

    @Test
    public void testClearEmptyQueue() {
        queue.clear();
        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());
    }

    @Test
    public void testClearNonEmptyQueue() {
        queue.add("A");
        queue.add("B");
        queue.add("C");

        queue.clear();
        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());
    }

    @Test
    public void testClearAndAdd() {
        queue.add("A");
        queue.add("B");
        queue.add("C");

        queue.clear();
        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());

        queue.add("D");
        assertFalse(queue.isEmpty());
        assertEquals(1, queue.size());
        assertEquals("D", queue.peek());
    }

    @Test
    public void testClearAndIterator() {
        queue.add("A");
        queue.add("B");
        queue.add("C");

        queue.clear();
        Iterator<String> iterator = queue.iterator();
        assertFalse(iterator.hasNext());
        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    public void testClearAndRemove() {
        queue.add("A");
        queue.add("B");
        queue.add("C");

        queue.clear();
        assertThrows(NoSuchElementException.class, queue::remove);
    }
}
