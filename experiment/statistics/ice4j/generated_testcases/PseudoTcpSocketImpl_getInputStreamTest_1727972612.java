
package org.ice4j.pseudotcp;

import static org.junit.jupiter.api.Assertions.*;
import java.io.*;
import java.net.*;
import org.junit.jupiter.api.*;

public class PseudoTcpSocketImpl_getInputStreamTest {

    private PseudoTcpSocketImpl pseudoTcpSocket;

    @BeforeEach
    public void setUp() throws SocketException {
        pseudoTcpSocket = new PseudoTcpSocketImpl(12345);
    }

    @Test
    public void testGetInputStream_InitialState() throws IOException {
        InputStream inputStream = pseudoTcpSocket.getInputStream();
        assertNotNull(inputStream);
        assertTrue(inputStream instanceof PseudoTcpInputStream);
    }

    @Test
    public void testGetInputStream_SubsequentCalls() throws IOException {
        InputStream firstCall = pseudoTcpSocket.getInputStream();
        InputStream secondCall = pseudoTcpSocket.getInputStream();
        assertSame(firstCall, secondCall);
    }
}
