
package org.apache.commons.collections4.queue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Queue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class SynchronizedQueue_peekTest {

    @Mock
    private Queue<String> mockQueue;

    private SynchronizedQueue<String> synchronizedQueue;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        synchronizedQueue = new SynchronizedQueue<>(mockQueue);
    }

    @Test
    public void testPeek() {
        // Given
        String expectedElement = "testElement";
        when(mockQueue.peek()).thenReturn(expectedElement);

        // When
        String actualElement = synchronizedQueue.peek();

        // Then
        assertEquals(expectedElement, actualElement);
        verify(mockQueue, times(1)).peek();
    }
}
