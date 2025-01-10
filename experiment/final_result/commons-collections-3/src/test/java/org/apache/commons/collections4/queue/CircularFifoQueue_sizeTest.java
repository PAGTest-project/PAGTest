
package org.apache.commons.collections4.queue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CircularFifoQueue_sizeTest {

    private CircularFifoQueue<String> queue;

    @BeforeEach
    public void setUp() {
        queue = new CircularFifoQueue<>(5);
    }

    @Test
    public void testSizeWhenQueueIsEmpty() {
        assertEquals(0, queue.size());
    }

    @Test
    public void testSizeWhenQueueIsFull() {
        queue.add("1");
        queue.add("2");
        queue.add("3");
        queue.add("4");
        queue.add("5");
        assertEquals(5, queue.size());
    }

    @Test
    public void testSizeWhenQueueIsPartiallyFilled() {
        queue.add("1");
        queue.add("2");
        queue.add("3");
        assertEquals(3, queue.size());
    }

    @Test
    public void testSizeWhenQueueIsWrapped() {
        queue.add("1");
        queue.add("2");
        queue.add("3");
        queue.add("4");
        queue.add("5");
        queue.add("6"); // This will wrap around
        assertEquals(5, queue.size());
    }

    @Test
    public void testSizeAfterRemovingElements() {
        queue.add("1");
        queue.add("2");
        queue.add("3");
        queue.remove();
        assertEquals(2, queue.size());
    }

    @Test
    public void testSizeAfterClearingQueue() {
        queue.add("1");
        queue.add("2");
        queue.clear();
        assertEquals(0, queue.size());
    }
}
