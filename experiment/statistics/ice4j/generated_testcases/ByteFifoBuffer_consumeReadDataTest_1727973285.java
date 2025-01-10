
package org.ice4j.pseudotcp.util;

import java.nio.BufferUnderflowException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ByteFifoBuffer_consumeReadDataTest {

    private ByteFifoBuffer fifoBuffer;

    @BeforeEach
    public void setUp() {
        fifoBuffer = new ByteFifoBuffer(16);
    }

    @Test
    public void testConsumeReadDataSuccess() {
        // Given
        byte[] data = new byte[8];
        fifoBuffer.write(data, 8);

        // When
        fifoBuffer.consumeReadData(8);

        // Then
        assertEquals(0, fifoBuffer.getBuffered());
        assertEquals(8, fifoBuffer.read_pos);
    }

    @Test
    public void testConsumeReadDataBufferUnderflow() {
        // Given
        byte[] data = new byte[8];
        fifoBuffer.write(data, 8);

        // When & Then
        assertThrows(BufferUnderflowException.class, () -> fifoBuffer.consumeReadData(10));
    }

    @Test
    public void testConsumeReadDataIllegalArgumentException() {
        // Given
        byte[] data = new byte[8];
        fifoBuffer.write(data, 8);

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> fifoBuffer.consumeReadData(-1));
    }
}
