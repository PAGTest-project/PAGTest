
package org.ice4j.pseudotcp.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ByteFifoBuffer_resetWritePositionTest {

    private ByteFifoBuffer fifoBuffer;

    @BeforeEach
    public void setUp() {
        fifoBuffer = new ByteFifoBuffer(1024);
    }

    @Test
    public void testResetWritePosition() {
        // Given
        byte[] data = new byte[256];
        fifoBuffer.write(data, 256);
        assertEquals(256, fifoBuffer.getBuffered());
        assertEquals(768, fifoBuffer.getWriteRemaining());

        // When
        fifoBuffer.resetWritePosition();

        // Then
        assertEquals(0, fifoBuffer.getBuffered());
        assertEquals(1024, fifoBuffer.getWriteRemaining());
    }
}
