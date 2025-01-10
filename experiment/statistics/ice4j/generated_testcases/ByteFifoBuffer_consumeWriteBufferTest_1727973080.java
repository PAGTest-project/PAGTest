
package org.ice4j.pseudotcp.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.BufferOverflowException;

import static org.junit.jupiter.api.Assertions.*;

public class ByteFifoBuffer_consumeWriteBufferTest {

    private ByteFifoBuffer fifoBuffer;

    @BeforeEach
    public void setUp() {
        fifoBuffer = new ByteFifoBuffer(16);
    }

    @Test
    public void testConsumeWriteBufferSuccess() {
        fifoBuffer.write(new byte[8], 8);
        assertDoesNotThrow(() -> fifoBuffer.consumeWriteBuffer(8));
        assertEquals(8, fifoBuffer.getBuffered());
    }

    @Test
    public void testConsumeWriteBufferOverflow() {
        fifoBuffer.write(new byte[8], 8);
        assertThrows(BufferOverflowException.class, () -> fifoBuffer.consumeWriteBuffer(9));
    }

    @Test
    public void testConsumeWriteBufferNegativeCount() {
        assertThrows(IllegalArgumentException.class, () -> fifoBuffer.consumeWriteBuffer(-1));
    }

    @Test
    public void testConsumeWriteBufferZeroCount() {
        assertDoesNotThrow(() -> fifoBuffer.consumeWriteBuffer(0));
        assertEquals(0, fifoBuffer.getBuffered());
    }
}
