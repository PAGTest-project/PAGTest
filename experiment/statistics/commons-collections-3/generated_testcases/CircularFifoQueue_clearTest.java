
package org.apache.commons.collections4.queue;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CircularFifoQueue_clearTest {

    private CircularFifoQueue<String> queue;

    @BeforeEach
    public void setUp() {
        queue = new CircularFifoQueue<>(3);
    }

    @Test
    public void testClear() {
        // Given: A non-empty queue
        queue.add("Element1");
        queue.add("Element2");
        queue.add("Element3");

        // When: clear() is called
        queue.clear();

        // Then: The queue should be empty
        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());
    }
}
