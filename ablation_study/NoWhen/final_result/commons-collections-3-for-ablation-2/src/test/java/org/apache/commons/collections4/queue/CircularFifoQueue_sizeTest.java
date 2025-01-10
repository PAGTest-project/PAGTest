
package org.apache.commons.collections4.queue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    public void testSizeWhenQueueIsNotFull() {
        queue.add("1");
        queue.add("2");
        queue.add("3");
        assertEquals(3, queue.size());
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
    public void testSizeWhenQueueIsWrapped() {
        queue.add("1");
        queue.add("2");
        queue.add("3");
        queue.add("4");
        queue.add("5");
        queue.add("6");
        assertEquals(5, queue.size());
    }

    @Test
    public void testSizeAfterRemove() {
        queue.add("1");
        queue.add("2");
        queue.add("3");
        queue.remove();
        assertEquals(2, queue.size());
    }

    @Test
    public void testSizeAfterClear() {
        queue.add("1");
        queue.add("2");
        queue.add("3");
        queue.clear();
        assertEquals(0, queue.size());
    }

    @Test
    public void testSizeWhenQueueIsFullAndWrapped() {
        queue.add("1");
        queue.add("2");
        queue.add("3");
        queue.add("4");
        queue.add("5");
        queue.add("6");
        queue.remove();
        queue.add("7");
        assertEquals(5, queue.size());
    }

    @Test
    public void testSizeWhenQueueIsFullAndRemovedFromMiddle() {
        queue.add("1");
        queue.add("2");
        queue.add("3");
        queue.add("4");
        queue.add("5");
        queue.remove("3");
        assertEquals(4, queue.size());
    }
}
