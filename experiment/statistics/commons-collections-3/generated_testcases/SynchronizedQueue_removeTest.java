
package org.apache.commons.collections4.queue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

import java.util.Queue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class SynchronizedQueue_removeTest {

    private Queue<String> mockQueue;
    private SynchronizedQueue<String> synchronizedQueue;

    @BeforeEach
    public void setUp() {
        mockQueue = Mockito.mock(Queue.class);
        synchronizedQueue = new SynchronizedQueue<>(mockQueue);
    }

    @Test
    public void testRemove() {
        // Given
        when(mockQueue.remove()).thenReturn("element");

        // When
        String result = synchronizedQueue.remove();

        // Then
        assertEquals("element", result);
        verify(mockQueue, times(1)).remove();
    }

    @Test
    public void testRemoveEmptyQueue() {
        // Given
        when(mockQueue.remove()).thenThrow(new java.util.NoSuchElementException());

        // When
        try {
            synchronizedQueue.remove();
        } catch (java.util.NoSuchElementException e) {
            // Then
            verify(mockQueue, times(1)).remove();
        }
    }
}
