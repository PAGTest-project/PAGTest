
package org.apache.commons.collections4.queue;

import org.junit.jupiter.api.Test;
import java.util.LinkedList;
import java.util.Queue;
import static org.junit.jupiter.api.Assertions.*;

public class SynchronizedQueue_removeTest {

    @Test
    public void testRemove() {
        // Given
        Queue<String> queue = new LinkedList<>();
        queue.offer("element1");
        SynchronizedQueue<String> synchronizedQueue = SynchronizedQueue.synchronizedQueue(queue);

        // When
        String removedElement = synchronizedQueue.remove();

        // Then
        assertEquals("element1", removedElement);
        assertTrue(synchronizedQueue.isEmpty());
    }
}
