
package org.apache.commons.collections4.queue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CircularFifoQueue_clearTest {

    private CircularFifoQueue<String> queue;

    @BeforeEach
    public void setUp() {
        queue = new CircularFifoQueue<>(5);
    }

    @Test
    public void testClearWithElements() {
        // Given
        queue.add("1");
        queue.add("2");
        queue.add("3");
        assertFalse(queue.isEmpty());

        // When
        queue.clear();

        // Then
        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());
    }

    @Test
    public void testClearEmptyQueue() {
        // Given
        assertTrue(queue.isEmpty());

        // When
        queue.clear();

        // Then
        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());
    }

    @Test
    public void testClearFullQueue() {
        // Given
        queue.add("1");
        queue.add("2");
        queue.add("3");
        queue.add("4");
        queue.add("5");
        assertTrue(queue.isAtFullCapacity());

        // When
        queue.clear();

        // Then
        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());
    }
}
