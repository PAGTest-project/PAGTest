
package org.ice4j.pseudotcp.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ByteFifoBuffer_lengthTest {

    private ByteFifoBuffer buffer;

    @BeforeEach
    public void setUp() {
        buffer = new ByteFifoBuffer(1024);
    }

    @Test
    public void testLength() {
        assertEquals(1024, buffer.length());
    }
}
