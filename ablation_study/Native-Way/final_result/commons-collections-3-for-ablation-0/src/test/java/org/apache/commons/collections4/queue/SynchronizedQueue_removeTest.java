
package org.apache.commons.collections4.queue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.util.NoSuchElementException;
import java.util.Queue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class SynchronizedQueue_removeTest {

    @Mock
    private Queue<String> mockQueue;

    private SynchronizedQueue<String> synchronizedQueue;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        synchronizedQueue = new SynchronizedQueue<>(mockQueue, new Object());
    }

    @Test
    public void testRemove_Success() {
        when(mockQueue.remove()).thenReturn("element");
        assertEquals("element", synchronizedQueue.remove());
    }

    @Test
    public void testRemove_ThrowsNoSuchElementException() {
        when(mockQueue.remove()).thenThrow(new NoSuchElementException());
        assertThrows(NoSuchElementException.class, () -> synchronizedQueue.remove());
    }
}
