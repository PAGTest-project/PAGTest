
package org.apache.commons.collections4.queue;

import org.junit.jupiter.api.Test;
import java.util.Queue;
import java.util.LinkedList;
import static org.junit.jupiter.api.Assertions.*;

public class UnmodifiableQueue_unmodifiableQueueTest {

    @Test
    public void testUnmodifiableQueueWithModifiableQueue() {
        Queue<String> modifiableQueue = new LinkedList<>();
        modifiableQueue.add("element");

        Queue<String> unmodifiableQueue = UnmodifiableQueue.unmodifiableQueue(modifiableQueue);

        assertTrue(unmodifiableQueue instanceof UnmodifiableQueue);
        assertEquals(1, unmodifiableQueue.size());
    }

    @Test
    public void testUnmodifiableQueueWithUnmodifiableQueue() {
        Queue<String> modifiableQueue = new LinkedList<>();
        modifiableQueue.add("element");
        Queue<String> alreadyUnmodifiableQueue = UnmodifiableQueue.unmodifiableQueue(modifiableQueue);

        Queue<String> unmodifiableQueue = UnmodifiableQueue.unmodifiableQueue(alreadyUnmodifiableQueue);

        assertSame(alreadyUnmodifiableQueue, unmodifiableQueue);
        assertEquals(1, unmodifiableQueue.size());
    }
}
