
package org.apache.commons.collections4.queue;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Queue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SynchronizedQueue_offerTest {

    private SynchronizedQueue<String> synchronizedQueue;
    private Queue<String> mockQueue;

    @BeforeEach
    public void setUp() {
        mockQueue = mock(Queue.class);
        synchronizedQueue = new SynchronizedQueue<>(mockQueue);
    }

    @Test
    public void testOffer() {
        String element = "testElement";
        when(mockQueue.offer(element)).thenReturn(true);

        boolean result = synchronizedQueue.offer(element);

        assertTrue(result);
    }
}
