
package org.apache.commons.collections4.queue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Queue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SynchronizedQueue_elementTest {

    private Queue<String> mockQueue;
    private SynchronizedQueue<String> synchronizedQueue;

    @BeforeEach
    public void setUp() {
        mockQueue = mock(Queue.class);
        synchronizedQueue = new SynchronizedQueue<>(mockQueue);
    }

    @Test
    public void testElement() {
        // Given
        String expectedElement = "testElement";
        when(mockQueue.element()).thenReturn(expectedElement);

        // When
        String actualElement = synchronizedQueue.element();

        // Then
        assertEquals(expectedElement, actualElement);
    }
}
