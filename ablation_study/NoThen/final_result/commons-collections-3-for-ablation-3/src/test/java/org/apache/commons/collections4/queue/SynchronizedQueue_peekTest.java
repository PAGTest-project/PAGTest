
package org.apache.commons.collections4.queue;

import org.junit.jupiter.api.Test;
import java.util.LinkedList;
import java.util.Queue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SynchronizedQueue_peekTest {

    @Test
    public void testPeek() {
        Queue<String> queue = new LinkedList<>();
        queue.offer("element");
        SynchronizedQueue<String> synchronizedQueue = SynchronizedQueue.synchronizedQueue(queue);

        String peekedElement = synchronizedQueue.peek();

        assertEquals("element", peekedElement);
    }
}
