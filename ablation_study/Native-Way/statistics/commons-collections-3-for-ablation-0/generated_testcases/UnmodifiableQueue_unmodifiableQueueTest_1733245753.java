
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
        Queue<String> originalQueue = new ArrayDeque<>();
        originalQueue.add("element1");
        originalQueue.add("element2");

        Queue<String> unmodifiableQueue = new UnmodifiableQueue<>(originalQueue);

        Queue<String> result = UnmodifiableQueue.unmodifiableQueue(unmodifiableQueue);

        assertSame(unmodifiableQueue, result);
        assertTrue(result instanceof Unmodifiable);
    }

    @Test
    public void testUnmodifiableQueueWithModifiableQueue() {
        Queue<String> originalQueue = new ArrayDeque<>();
        originalQueue.add("element1");
        originalQueue.add("element2");

        Queue<String> result = UnmodifiableQueue.unmodifiableQueue(originalQueue);

        assertTrue(result instanceof UnmodifiableQueue);
        assertTrue(result instanceof Unmodifiable);
    }
}
