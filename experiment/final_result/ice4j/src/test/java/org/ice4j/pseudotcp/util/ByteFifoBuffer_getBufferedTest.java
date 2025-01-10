
package org.ice4j.pseudotcp.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ByteFifoBuffer_getBufferedTest {

    private ByteFifoBuffer fifoBuffer;

    @BeforeEach
    public void setUp() {
        fifoBuffer = new ByteFifoBuffer(1024);
    }

    @Test
    public void testGetBufferedInitialState() {
        assertEquals(0, fifoBuffer.getBuffered());
    }

    @Test
    public void testGetBufferedAfterWrite() {
        byte[] data = new byte[256];
        fifoBuffer.write(data, 256);
        assertEquals(256, fifoBuffer.getBuffered());
    }

    @Test
    public void testGetBufferedAfterRead() {
        byte[] data = new byte[256];
        byte[] readBuffer = new byte[256];
        fifoBuffer.write(data, 256);
        fifoBuffer.read(readBuffer, 256);
        assertEquals(0, fifoBuffer.getBuffered());
    }

    @Test
    public void testGetBufferedAfterPartialRead() {
        byte[] data = new byte[256];
        byte[] readBuffer = new byte[128];
        fifoBuffer.write(data, 256);
        fifoBuffer.read(readBuffer, 128);
        assertEquals(128, fifoBuffer.getBuffered());
    }

    @Test
    public void testGetBufferedAfterMultipleWritesAndReads() {
        byte[] data1 = new byte[128];
        byte[] data2 = new byte[128];
        byte[] readBuffer = new byte[256];
        fifoBuffer.write(data1, 128);
        fifoBuffer.write(data2, 128);
        fifoBuffer.read(readBuffer, 256);
        assertEquals(0, fifoBuffer.getBuffered());
    }
}
