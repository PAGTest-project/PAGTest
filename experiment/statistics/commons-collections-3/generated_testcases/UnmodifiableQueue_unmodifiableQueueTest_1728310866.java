
package org.apache.commons.collections4.queue;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayDeque;
import java.util.Queue;

import org.apache.commons.collections4.Unmodifiable;
import org.junit.jupiter.api.Test;

public class UnmodifiableQueue_unmodifiableQueueTest {

    @Test
    public void testUnmodifiableQueueWithUnmodifiableQueue() {
        Queue<String> originalQueue = new ArrayDeque<>();
        originalQueue.add("element");
        Queue<String> unmodifiableQueue = new UnmodifiableQueue<>(originalQueue);

        Queue<String> result = UnmodifiableQueue.unmodifiableQueue(unmodifiableQueue);

        assertSame(unmodifiableQueue, result);
        assertTrue(result instanceof Unmodifiable);
    }

    @Test
    public void testUnmodifiableQueueWithModifiableQueue() {
        Queue<String> originalQueue = new ArrayDeque<>();
        originalQueue.add("element");

        Queue<String> result = UnmodifiableQueue.unmodifiableQueue(originalQueue);

        assertTrue(result instanceof UnmodifiableQueue);
    }
}
