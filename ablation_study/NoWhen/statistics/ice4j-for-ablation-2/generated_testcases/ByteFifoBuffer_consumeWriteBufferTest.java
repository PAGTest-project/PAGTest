
package org.ice4j.pseudotcp.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.nio.BufferOverflowException;
import static org.junit.jupiter.api.Assertions.*;

public class ByteFifoBuffer_consumeWriteBufferTest {
    private ByteFifoBuffer buffer;

    @BeforeEach
    public void setUp() {
        buffer = new ByteFifoBuffer(1024);
    }

    @Test
    public void testConsumeWriteBufferSuccess() {
        int count = 512;
        buffer.consumeWriteBuffer(count);
        assertEquals(512, buffer.getBuffered());
        assertEquals(512, buffer.getWriteRemaining());
    }

    @Test
    public void testConsumeWriteBufferOverflow() {
        int count = 1025;
        assertThrows(BufferOverflowException.class, () -> buffer.consumeWriteBuffer(count));
    }

    @Test
    public void testConsumeWriteBufferNegativeCount() {
        int count = -1;
        assertThrows(IllegalArgumentException.class, () -> buffer.consumeWriteBuffer(count));
    }

    @Test
    public void testConsumeWriteBufferWithWrite() {
        int writeCount = 512;
        byte[] data = new byte[writeCount];
        buffer.write(data, 0, writeCount);
        assertEquals(512, buffer.getBuffered());
        assertEquals(512, buffer.getWriteRemaining());

        int consumeCount = 256;
        buffer.consumeWriteBuffer(consumeCount);
        assertEquals(768, buffer.getBuffered());
        assertEquals(256, buffer.getWriteRemaining());
    }

    @Test
    public void testConsumeWriteBufferWithWriteAndOverflow() {
        int writeCount = 512;
        byte[] data = new byte[writeCount];
        buffer.write(data, 0, writeCount);
        assertEquals(512, buffer.getBuffered());
        assertEquals(512, buffer.getWriteRemaining());

        int consumeCount = 513;
        assertThrows(BufferOverflowException.class, () -> buffer.consumeWriteBuffer(consumeCount));
    }
}
