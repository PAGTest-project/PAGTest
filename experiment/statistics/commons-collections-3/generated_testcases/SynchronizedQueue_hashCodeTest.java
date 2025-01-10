
package org.apache.commons.collections4.queue;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.LinkedList;
import java.util.Queue;

public class SynchronizedQueue_hashCodeTest {

    @Test
    public void testHashCode() {
        Queue<String> queue = new LinkedList<>();
        queue.offer("element1");
        queue.offer("element2");

        SynchronizedQueue<String> synchronizedQueue = SynchronizedQueue.synchronizedQueue(queue);

        int expectedHashCode = queue.hashCode();
        int actualHashCode = synchronizedQueue.hashCode();

        assertEquals(expectedHashCode, actualHashCode);
    }
}
