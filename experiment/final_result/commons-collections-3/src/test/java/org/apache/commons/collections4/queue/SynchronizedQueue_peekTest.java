
package org.apache.commons.collections4.queue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Queue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class SynchronizedQueue_peekTest {

    private SynchronizedQueue<String> synchronizedQueue;
    private Queue<String> mockQueue;

    @BeforeEach
    public void setUp() {
        mockQueue = Mockito.mock(Queue.class);
        synchronizedQueue = new SynchronizedQueue<>(mockQueue);
    }

    @Test
    public void testPeek() {
        // Given
        when(mockQueue.peek()).thenReturn("element");

        // When
        String result = synchronizedQueue.peek();

        // Then
        assertEquals("element", result);
        verify(mockQueue, times(1)).peek();
    }
}
