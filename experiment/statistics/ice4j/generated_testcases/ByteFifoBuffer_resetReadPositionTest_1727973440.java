
package org.ice4j.pseudotcp.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ByteFifoBuffer_resetReadPositionTest {

    private ByteFifoBuffer fifo;

    @BeforeEach
    public void setUp() {
        fifo = new ByteFifoBuffer(16);
    }

    @Test
    public void testResetReadPosition() {
        // Given
        fifo.write(new byte[]{1, 2, 3, 4}, 4);
        fifo.read(new byte[4], 4);

        // When
        fifo.resetReadPosition();

        // Then
        assertEquals(0, fifo.getBuffered());
        assertEquals(0, fifo.getReadPosition());
    }
}
