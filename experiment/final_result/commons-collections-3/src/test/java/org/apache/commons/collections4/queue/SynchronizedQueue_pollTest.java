
package org.apache.commons.collections4.queue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

import java.util.Queue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class SynchronizedQueue_pollTest {

    @Mock
    private Queue<String> mockQueue;

    private SynchronizedQueue<String> synchronizedQueue;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        synchronizedQueue = new SynchronizedQueue<>(mockQueue, new Object());
    }

    @Test
    public void testPoll_WhenQueueHasElements_ShouldReturnElement() {
        when(mockQueue.poll()).thenReturn("element");

        String result = synchronizedQueue.poll();

        assertEquals("element", result);
        verify(mockQueue, times(1)).poll();
    }

    @Test
    public void testPoll_WhenQueueIsEmpty_ShouldReturnNull() {
        when(mockQueue.poll()).thenReturn(null);

        String result = synchronizedQueue.poll();

        assertNull(result);
        verify(mockQueue, times(1)).poll();
    }
}
