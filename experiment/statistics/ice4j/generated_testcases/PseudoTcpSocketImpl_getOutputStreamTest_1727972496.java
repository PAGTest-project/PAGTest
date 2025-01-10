
package org.ice4j.pseudotcp;

import static org.junit.jupiter.api.Assertions.*;
import java.io.*;
import org.junit.jupiter.api.*;

public class PseudoTcpSocketImpl_getOutputStreamTest {

    private PseudoTcpSocketImpl pseudoTcpSocketImpl;

    @BeforeEach
    public void setUp() throws SocketException {
        pseudoTcpSocketImpl = new PseudoTcpSocketImpl(1234567890L);
    }

    @Test
    public void testGetOutputStream_FirstCall() throws IOException {
        OutputStream outputStream = pseudoTcpSocketImpl.getOutputStream();
        assertNotNull(outputStream);
        assertTrue(outputStream instanceof PseudoTcpOutputStream);
    }

    @Test
    public void testGetOutputStream_SubsequentCall() throws IOException {
        OutputStream outputStream1 = pseudoTcpSocketImpl.getOutputStream();
        OutputStream outputStream2 = pseudoTcpSocketImpl.getOutputStream();
        assertSame(outputStream1, outputStream2);
    }
}
