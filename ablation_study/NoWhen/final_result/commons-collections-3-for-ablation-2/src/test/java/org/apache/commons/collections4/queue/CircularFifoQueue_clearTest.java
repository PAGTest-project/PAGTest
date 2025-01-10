
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
        // Given
        queue.add("Element1");
        queue.add("Element2");
        queue.add("Element3");

        // When
        queue.clear();

        // Then
        assertTrue(queue.isEmpty(), "Queue should be empty after clear");
        assertEquals(0, queue.size(), "Queue size should be 0 after clear");
    }

    @Test
    public void testClearOnEmptyQueue() {
        // Given
        assertTrue(queue.isEmpty(), "Queue should be empty initially");

        // When
        queue.clear();

        // Then
        assertTrue(queue.isEmpty(), "Queue should still be empty after clear");
        assertEquals(0, queue.size(), "Queue size should be 0 after clear");
    }

    @Test
    public void testClearWithPartialFill() {
        // Given
        queue.add("Element1");
        queue.add("Element2");

        // When
        queue.clear();

        // Then
        assertTrue(queue.isEmpty(), "Queue should be empty after clear");
        assertEquals(0, queue.size(), "Queue size should be 0 after clear");
    }
}
