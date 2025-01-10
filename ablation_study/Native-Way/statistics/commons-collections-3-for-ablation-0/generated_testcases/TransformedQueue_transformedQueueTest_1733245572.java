
package org.apache.commons.collections4.queue;

import org.apache.commons.collections4.Transformer;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;

public class TransformedQueue_transformedQueueTest {

    @Test
    public void testTransformedQueueWithNonEmptyQueue() {
        Queue<String> queue = new LinkedList<>();
        queue.add("a");
        queue.add("b");
        Transformer<String, String> transformer = s -> s.toUpperCase();

        TransformedQueue<String> transformedQueue = TransformedQueue.transformedQueue(queue, transformer);

        assertTrue(queue.isEmpty());
        assertEquals(2, transformedQueue.size());
        assertEquals("A", transformedQueue.poll());
        assertEquals("B", transformedQueue.poll());
    }

    @Test
    public void testTransformedQueueWithEmptyQueue() {
        Queue<String> queue = new LinkedList<>();
        Transformer<String, String> transformer = s -> s.toUpperCase();

        TransformedQueue<String> transformedQueue = TransformedQueue.transformedQueue(queue, transformer);

        assertTrue(queue.isEmpty());
        assertTrue(transformedQueue.isEmpty());
    }

    @Test
    public void testTransformedQueueWithNullQueue() {
        Transformer<String, String> transformer = s -> s.toUpperCase();

        assertThrows(NullPointerException.class, () -> {
            TransformedQueue.transformedQueue(null, transformer);
        });
    }

    @Test
    public void testTransformedQueueWithNullTransformer() {
        Queue<String> queue = new LinkedList<>();
        queue.add("a");

        assertThrows(NullPointerException.class, () -> {
            TransformedQueue.transformedQueue(queue, null);
        });
    }
}
