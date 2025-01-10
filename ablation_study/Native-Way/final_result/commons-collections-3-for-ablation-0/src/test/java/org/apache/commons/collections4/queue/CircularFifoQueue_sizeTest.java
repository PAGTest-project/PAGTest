
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
        queue = new CircularFifoQueue<>(5);
    }

    @Test
    public void testSizeEmptyQueue() {
        assertEquals(0, queue.size());
    }

    @Test
    public void testSizeNonEmptyQueue() {
        queue.add(1);
        queue.add(2);
        assertEquals(2, queue.size());
    }

    @Test
    public void testSizeFullQueue() {
        for (int i = 0; i < 5; i++) {
            queue.add(i);
        }
        assertEquals(5, queue.size());
    }

    @Test
    public void testSizeAfterRemove() {
        for (int i = 0; i < 5; i++) {
            queue.add(i);
        }
        queue.remove();
        assertEquals(4, queue.size());
    }

    @Test
    public void testSizeAfterClear() {
        for (int i = 0; i < 5; i++) {
            queue.add(i);
        }
        queue.clear();
        assertEquals(0, queue.size());
    }

    @Test
    public void testSizeAfterWrapAround() {
        for (int i = 0; i < 5; i++) {
            queue.add(i);
        }
        queue.remove();
        queue.add(5);
        assertEquals(5, queue.size());
    }

    @Test
    public void testSizeAfterPartialWrapAround() {
        for (int i = 0; i < 3; i++) {
            queue.add(i);
        }
        queue.remove();
        queue.add(3);
        assertEquals(3, queue.size());
    }

    @Test
    public void testSizeAfterFullWrapAround() {
        for (int i = 0; i < 5; i++) {
            queue.add(i);
        }
        for (int i = 0; i < 5; i++) {
            queue.remove();
        }
        for (int i = 0; i < 5; i++) {
            queue.add(i);
        }
        assertEquals(5, queue.size());
    }
}
