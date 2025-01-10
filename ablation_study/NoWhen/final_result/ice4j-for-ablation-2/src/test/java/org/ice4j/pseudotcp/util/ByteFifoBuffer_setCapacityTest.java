
package org.ice4j.pseudotcp.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ByteFifoBuffer_setCapacityTest {
    private ByteFifoBuffer byteFifoBuffer;

    @BeforeEach
    public void setUp() {
        byteFifoBuffer = new ByteFifoBuffer(1000);
    }

    @Test
    public void testSetCapacitySuccess() {
        byte[] w_data = getWData(100);
        byteFifoBuffer.write(w_data, 100);

        assertTrue(byteFifoBuffer.setCapacity(1500));
        assertEquals(1500, byteFifoBuffer.length());
        assertEquals(100, byteFifoBuffer.getBuffered());
    }

    @Test
    public void testSetCapacityFailure() {
        byte[] w_data = getWData(100);
        byteFifoBuffer.write(w_data, 100);

        assertFalse(byteFifoBuffer.setCapacity(50));
        assertEquals(1000, byteFifoBuffer.length());
        assertEquals(100, byteFifoBuffer.getBuffered());
    }

    @Test
    public void testSetCapacityWithConsumeWriteBuffer() {
        byte[] w_data = getWData(100);
        byteFifoBuffer.write(w_data, 100);
        byteFifoBuffer.consumeWriteBuffer(50);

        assertTrue(byteFifoBuffer.setCapacity(1500));
        assertEquals(1500, byteFifoBuffer.length());
        assertEquals(150, byteFifoBuffer.getBuffered());
    }

    @Test
    public void testSetCapacityWithWriteRemaining() {
        byte[] w_data = getWData(100);
        byteFifoBuffer.write(w_data, 100);

        assertTrue(byteFifoBuffer.setCapacity(1500));
        assertEquals(1500, byteFifoBuffer.length());
        assertEquals(1400, byteFifoBuffer.getWriteRemaining());
    }

    private byte[] getWData(int length) {
        byte[] data = new byte[length];
        for (int i = 0; i < length; i++) {
            data[i] = (byte) (i % 256);
        }
        return data;
    }
}
