
package org.ice4j.pseudotcp.util;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class ByteFifoBuffer_resetWritePositionTest {

    private ByteFifoBuffer byteFifoBuffer;

    @BeforeEach
    public void setUp() {
        byteFifoBuffer = new ByteFifoBuffer(1000);
    }

    @Test
    public void testResetWritePosition() {
        int w_len = 100;
        byte[] w_data = getWData(w_len);

        byteFifoBuffer.write(w_data, w_len);
        byteFifoBuffer.consumeWriteBuffer(50);

        byteFifoBuffer.resetWritePosition();

        assertEquals(0, byteFifoBuffer.getBuffered());
        assertEquals(1000, byteFifoBuffer.getWriteRemaining());
    }

    private byte[] getWData(int length) {
        byte[] data = new byte[length];
        for (int i = 0; i < length; i++) {
            data[i] = (byte) (i % 256);
        }
        return data;
    }
}
