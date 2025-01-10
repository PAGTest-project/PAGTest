
package org.apache.commons.collections4.queue;

import static org.junit.jupiter.api.Assertions.*;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CircularFifoQueue_iteratorTest {

    private CircularFifoQueue<String> queue;

    @BeforeEach
    public void setUp() {
        queue = new CircularFifoQueue<>(3);
        queue.add("A");
        queue.add("B");
        queue.add("C");
    }

    @Test
    public void testIteratorHasNext() {
        Iterator<String> iterator = queue.iterator();
        assertTrue(iterator.hasNext());
        iterator.next();
        assertTrue(iterator.hasNext());
        iterator.next();
        assertTrue(iterator.hasNext());
        iterator.next();
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testIteratorNext() {
        Iterator<String> iterator = queue.iterator();
        assertEquals("A", iterator.next());
        assertEquals("B", iterator.next());
        assertEquals("C", iterator.next());
        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    public void testIteratorRemove() {
        Iterator<String> iterator = queue.iterator();
        assertEquals("A", iterator.next());
        iterator.remove();
        assertEquals(2, queue.size());
        assertEquals("B", queue.peek());

        assertEquals("B", iterator.next());
        iterator.remove();
        assertEquals(1, queue.size());
        assertEquals("C", queue.peek());

        assertEquals("C", iterator.next());
        iterator.remove();
        assertTrue(queue.isEmpty());
    }

    @Test
    public void testIteratorRemoveWithoutNext() {
        Iterator<String> iterator = queue.iterator();
        assertThrows(IllegalStateException.class, iterator::remove);
    }

    @Test
    public void testIteratorRemoveTwice() {
        Iterator<String> iterator = queue.iterator();
        iterator.next();
        iterator.remove();
        assertThrows(IllegalStateException.class, iterator::remove);
    }
}
