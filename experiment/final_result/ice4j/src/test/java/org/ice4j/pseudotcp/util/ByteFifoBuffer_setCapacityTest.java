
package org.ice4j.pseudotcp.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ByteFifoBuffer_setCapacityTest {

    private ByteFifoBuffer fifo;

    @BeforeEach
    public void setUp() {
        fifo = new ByteFifoBuffer(16);
    }

    @Test
    public void testSetCapacitySuccess() {
        // Given
        byte[] data = new byte[8];
        fifo.write(data, 8);

        // When
        boolean result = fifo.setCapacity(16);

        // Then
        assertTrue(result);
        assertEquals(16, fifo.length());
    }

    @Test
    public void testSetCapacityFailure() {
        // Given
        byte[] data = new byte[16];
        fifo.write(data, 16);

        // When
        boolean result = fifo.setCapacity(8);

        // Then
        assertFalse(result);
        assertEquals(16, fifo.length());
    }
}
