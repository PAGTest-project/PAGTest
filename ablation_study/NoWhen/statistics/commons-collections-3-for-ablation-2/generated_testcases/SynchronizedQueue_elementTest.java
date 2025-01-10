
package org.apache.commons.collections4.queue;

import org.junit.jupiter.api.Test;
import java.util.LinkedList;
import java.util.Queue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SynchronizedQueue_elementTest {

    @Test
    public void testElement() {
        Queue<String> queue = new LinkedList<>();
        queue.add("element1");
        SynchronizedQueue<String> synchronizedQueue = SynchronizedQueue.synchronizedQueue(queue);

        String result = synchronizedQueue.element();

        assertEquals("element1", result);
    }
}
