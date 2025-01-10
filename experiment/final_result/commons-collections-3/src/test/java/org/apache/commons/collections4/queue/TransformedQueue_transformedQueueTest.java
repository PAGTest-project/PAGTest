
package org.apache.commons.collections4.queue;

import org.apache.commons.collections4.Transformer;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TransformedQueue_transformedQueueTest {

    @Test
    public void testTransformedQueueWithNonEmptyQueue() {
        // Given
        Queue<String> queue = new LinkedList<>();
        queue.add("a");
        queue.add("b");
        Transformer<String, String> transformer = s -> s.toUpperCase();

        // When
        TransformedQueue<String> transformedQueue = TransformedQueue.transformedQueue(queue, transformer);

        // Then
        assertEquals(2, queue.size()); // Change this line to check the size of the original queue
        assertEquals(2, transformedQueue.size());
        assertEquals("A", transformedQueue.poll());
        assertEquals("B", transformedQueue.poll());
    }

    @Test
    public void testTransformedQueueWithEmptyQueue() {
        // Given
        Queue<String> queue = new LinkedList<>();
        Transformer<String, String> transformer = s -> s.toUpperCase();

        // When
        TransformedQueue<String> transformedQueue = TransformedQueue.transformedQueue(queue, transformer);

        // Then
        assertEquals(0, queue.size());
        assertEquals(0, transformedQueue.size());
    }

    @Test
    public void testTransformedQueueWithNullQueue() {
        // Given
        Queue<String> queue = null;
        Transformer<String, String> transformer = s -> s.toUpperCase();

        // When & Then
        assertThrows(NullPointerException.class, () -> TransformedQueue.transformedQueue(queue, transformer));
    }

    @Test
    public void testTransformedQueueWithNullTransformer() {
        // Given
        Queue<String> queue = new LinkedList<>();
        Transformer<String, String> transformer = null;

        // When & Then
        assertThrows(NullPointerException.class, () -> TransformedQueue.transformedQueue(queue, transformer));
    }
}
