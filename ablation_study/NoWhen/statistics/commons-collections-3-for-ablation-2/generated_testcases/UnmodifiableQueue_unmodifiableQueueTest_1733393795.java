
package org.apache.commons.collections4.queue;

import org.apache.commons.collections4.Unmodifiable;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UnmodifiableQueue_unmodifiableQueueTest {

    @Test
    public void testUnmodifiableQueueWithUnmodifiableQueue() {
        // Given
        Queue<String> originalQueue = new ArrayDeque<>();
        Queue<String> unmodifiableQueue = new UnmodifiableQueue<>(originalQueue);

        // When
        Queue<String> result = UnmodifiableQueue.unmodifiableQueue(unmodifiableQueue);

        // Then
        assertSame(unmodifiableQueue, result);
        assertTrue(result instanceof Unmodifiable);
    }

    @Test
    public void testUnmodifiableQueueWithModifiableQueue() {
        // Given
        Queue<String> originalQueue = new ArrayDeque<>();

        // When
        Queue<String> result = UnmodifiableQueue.unmodifiableQueue(originalQueue);

        // Then
        assertTrue(result instanceof UnmodifiableQueue);
        assertTrue(result instanceof Unmodifiable);
    }
}
