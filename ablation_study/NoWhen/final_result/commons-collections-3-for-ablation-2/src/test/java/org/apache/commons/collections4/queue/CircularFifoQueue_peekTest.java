
package org.apache.commons.collections4.queue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CircularFifoQueue_peekTest {

    private CircularFifoQueue<String> queue;

    @BeforeEach
    public void setUp() {
        queue = new CircularFifoQueue<>(5);
    }

    @Test
    public void testPeekWhenQueueIsEmpty() {
        assertNull(queue.peek());
    }

    @Test
    public void testPeekWhenQueueHasOneElement() {
        queue.add("1");
        assertEquals("1", queue.peek());
    }

    @Test
    public void testPeekWhenQueueIsFull() {
        queue.add("1");
        queue.add("2");
        queue.add("3");
        queue.add("4");
        queue.add("5");
        assertEquals("1", queue.peek());
    }

    @Test
    public void testPeekAfterRemovingElements() {
        queue.add("1");
        queue.add("2");
        queue.add("3");
        queue.remove();
        assertEquals("2", queue.peek());
    }

    @Test
    public void testPeekAfterAddingMoreThanCapacity() {
        queue.add("1");
        queue.add("2");
        queue.add("3");
        queue.add("4");
        queue.add("5");
        queue.add("6");
        assertEquals("2", queue.peek());
    }

    @Test
    public void testPeekAfterClear() {
        queue.add("1");
        queue.add("2");
        queue.clear();
        assertNull(queue.peek());
    }

    @Test
    public void testPeekAfterRemovingAllElements() {
        queue.add("1");
        queue.add("2");
        queue.remove();
        queue.remove();
        assertNull(queue.peek());
    }

    @Test
    public void testPeekWithNoSuchElementException() {
        assertThrows(NoSuchElementException.class, () -> queue.element());
    }
}
