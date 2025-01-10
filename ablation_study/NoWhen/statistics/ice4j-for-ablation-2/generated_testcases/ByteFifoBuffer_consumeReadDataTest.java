
package org.ice4j.pseudotcp.util;

import java.nio.BufferUnderflowException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ByteFifoBuffer_consumeReadDataTest {
    private ByteFifoBuffer buffer;

    @BeforeEach
    public void setUp() {
        buffer = new ByteFifoBuffer(1024);
    }

    @Test
    public void testConsumeReadDataSuccess() {
        byte[] data = new byte[512];
        buffer.write(data, 512);
        buffer.consumeReadData(256);
        assertEquals(256, buffer.getBuffered());
        assertEquals(256, buffer.getReadPos());
    }

    @Test
    public void testConsumeReadDataBufferUnderflow() {
        byte[] data = new byte[512];
        buffer.write(data, 512);
        assertThrows(BufferUnderflowException.class, () -> buffer.consumeReadData(1024));
    }

    @Test
    public void testConsumeReadDataIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> buffer.consumeReadData(-1));
    }

    @Test
    public void testConsumeReadDataWrapAround() {
        byte[] data = new byte[512];
        buffer.write(data, 512);
        buffer.consumeReadData(512);
        assertEquals(0, buffer.getBuffered());
        assertEquals(0, buffer.getReadPos());
    }

    @Test
    public void testConsumeReadDataMultipleConsumptions() {
        byte[] data = new byte[512];
        buffer.write(data, 512);
        buffer.consumeReadData(256);
        buffer.consumeReadData(256);
        assertEquals(0, buffer.getBuffered());
        assertEquals(0, buffer.getReadPos());
    }
}
