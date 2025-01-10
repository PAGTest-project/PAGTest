
package org.ice4j.pseudotcp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.io.OutputStream;

class PseudoTcpSocketImpl_getOutputStreamTest {

    @Test
    void testGetOutputStream_InitialCall() throws IOException {
        PseudoTcpSocketImpl socketImpl = new PseudoTcpSocketImpl(12345);
        OutputStream outputStream = socketImpl.getOutputStream();
        assertNotNull(outputStream);
        assertTrue(outputStream instanceof PseudoTcpOutputStream);
    }

    @Test
    void testGetOutputStream_SubsequentCall() throws IOException {
        PseudoTcpSocketImpl socketImpl = new PseudoTcpSocketImpl(12345);
        OutputStream firstCall = socketImpl.getOutputStream();
        OutputStream secondCall = socketImpl.getOutputStream();
        assertSame(firstCall, secondCall);
    }
}
