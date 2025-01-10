
package org.apache.commons.collections4.queue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CircularFifoQueue_pollTest {

    private CircularFifoQueue<String> queue;

    @BeforeEach
    public void setUp() {
        queue = new CircularFifoQueue<>(3);
    }

    @Test
    public void testPollWhenQueueIsEmpty() {
        assertNull(queue.poll());
    }

    @Test
    public void testPollWhenQueueHasElements() {
        queue.add("element1");
        queue.add("element2");

        assertEquals("element1", queue.poll());
        assertEquals("element2", queue.poll());
        assertNull(queue.poll());
    }
}
