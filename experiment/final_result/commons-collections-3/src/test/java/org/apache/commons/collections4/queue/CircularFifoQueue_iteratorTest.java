
package org.apache.commons.collections4.queue;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.NoSuchElementException;
import java.util.Iterator;

public class CircularFifoQueue_iteratorTest {

    private CircularFifoQueue<Integer> queue;

    @BeforeEach
    public void setUp() {
        queue = new CircularFifoQueue<>(3);
    }

    @Test
    public void testIteratorHasNextAndNext() {
        queue.add(1);
        queue.add(2);
        queue.add(3);

        Iterator<Integer> iterator = queue.iterator();

        assertTrue(iterator.hasNext());
        assertEquals(1, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(2, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(3, iterator.next());
        assertFalse(iterator.hasNext());
        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    public void testIteratorRemove() {
        queue.add(1);
        queue.add(2);
        queue.add(3);

        Iterator<Integer> iterator = queue.iterator();

        assertEquals(1, iterator.next());
        iterator.remove();
        assertEquals(2, iterator.next());
        iterator.remove();
        assertEquals(3, iterator.next());
        iterator.remove();

        assertTrue(queue.isEmpty());
    }

    @Test
    public void testIteratorRemoveFirstElement() {
        queue.add(1);
        queue.add(2);
        queue.add(3);

        Iterator<Integer> iterator = queue.iterator();

        assertEquals(1, iterator.next());
        iterator.remove();
        assertEquals(2, iterator.next());
        assertEquals(3, iterator.next());

        assertEquals(2, queue.size());
        assertEquals(2, queue.get(0));
        assertEquals(3, queue.get(1));
    }

    @Test
    public void testIteratorRemoveThrowsIllegalStateException() {
        queue.add(1);

        Iterator<Integer> iterator = queue.iterator();

        assertThrows(IllegalStateException.class, iterator::remove);
    }
}
