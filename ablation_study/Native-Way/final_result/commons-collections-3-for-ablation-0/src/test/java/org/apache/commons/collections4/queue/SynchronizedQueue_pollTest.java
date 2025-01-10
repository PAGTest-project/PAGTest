
package org.apache.commons.collections4.queue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Queue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SynchronizedQueue_pollTest {

    private SynchronizedQueue<String> synchronizedQueue;
    private Queue<String> mockQueue;

    @BeforeEach
    public void setUp() {
        mockQueue = mock(Queue.class);
        synchronizedQueue = new SynchronizedQueue<>(mockQueue);
    }

    @Test
    public void testPoll_WhenQueueIsNotEmpty() {
        when(mockQueue.poll()).thenReturn("element");
        assertEquals("element", synchronizedQueue.poll());
    }

    @Test
    public void testPoll_WhenQueueIsEmpty() {
        when(mockQueue.poll()).thenReturn(null);
        assertNull(synchronizedQueue.poll());
    }
}
