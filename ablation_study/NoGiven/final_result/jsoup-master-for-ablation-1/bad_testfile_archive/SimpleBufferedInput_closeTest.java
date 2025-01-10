
package org.jsoup.internal;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

class SimpleBufferedInput_closeTest {

    @Test
    void testCloseWithAllocatedBuffer() throws IOException {
        SimpleBufferedInput sbi = new SimpleBufferedInput(new ByteArrayInputStream(new byte[10]));
        sbi.read(); // Ensure byteBuf is allocated
        sbi.close();
        assertNull(sbi.getBuf()); // Ensure byteBuf is null after close
    }

    @Test
    void testCloseWithNullBuffer() throws IOException {
        SimpleBufferedInput sbi = new SimpleBufferedInput(new ByteArrayInputStream(new byte[10]));
        sbi.close(); // Close without allocating byteBuf
        // No exception should be thrown
    }
}
