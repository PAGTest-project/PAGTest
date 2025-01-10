
package org.apache.commons.collections4.queue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CircularFifoQueue_sizeTest {

    private CircularFifoQueue<Integer> queue;

    @BeforeEach
    public void setUp() {
        queue = new CircularFifoQueue<>(3);
    }

    @Test
    public void testSizeWhenQueueIsEmpty() {
        assertEquals(0, queue.size());
    }

    @Test
    public void testSizeWhenQueueIsFull() {
        queue.add(1);
        queue.add(2);
        queue.add(3);
        assertEquals(3, queue.size());
    }

    @Test
    public void testSizeWhenQueueHasElementsButNotFull() {
        queue.add(1);
        queue.add(2);
        assertEquals(2, queue.size());
    }

    @Test
    public void testSizeAfterAddingAndRemovingElements() {
        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.remove();
        assertEquals(2, queue.size());
    }

    @Test
    public void testSizeAfterClearingQueue() {
        queue.add(1);
        queue.add(2);
        queue.clear();
        assertEquals(0, queue.size());
    }

    @Test
    public void testSizeWhenEndIsLessThanStart() {
        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.remove();
        queue.add(4);
        assertEquals(3, queue.size());
    }

    @Test
    public void testSizeWhenEndEqualsStartAndQueueIsFull() {
        queue.add(1);
        queue.add(2);
        queue.add(3);
        assertEquals(3, queue.size());
    }

    @Test
    public void testSizeWhenEndEqualsStartAndQueueIsEmpty() {
        queue.add(1);
        queue.remove();
        assertEquals(0, queue.size());
    }
}
