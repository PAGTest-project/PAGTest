
package org.ice4j.pseudotcp;

import static org.junit.jupiter.api.Assertions.*;
import java.io.*;
import java.net.*;
import org.junit.jupiter.api.*;

public class PseudoTcpSocket_isClosedTest {

    private PseudoTcpSocket socket;
    private PseudoTcpSocketImpl socketImpl;

    @BeforeEach
    public void setUp() throws SocketException {
        socketImpl = new PseudoTcpSocketImpl(0L, new DatagramSocket());
        socket = new PseudoTcpSocket(socketImpl);
    }

    @Test
    public void testIsClosedWhenSocketIsClosed() {
        socketImpl.m_state = PseudoTcpState.TCP_CLOSED;
        assertTrue(socket.isClosed());
    }

    @Test
    public void testIsClosedWhenSocketIsNotClosed() {
        socketImpl.m_state = PseudoTcpState.TCP_ESTABLISHED;
        assertFalse(socket.isClosed());
    }

    @Test
    public void testIsClosedAfterClosingSocket() throws IOException {
        socketImpl.m_state = PseudoTcpState.TCP_ESTABLISHED;
        assertFalse(socket.isClosed());
        socket.close();
        assertTrue(socket.isClosed());
    }

    @Test
    public void testIsClosedAfterConnectingAndClosingSocket() throws IOException {
        InetSocketAddress address = new InetSocketAddress("localhost", 12345);
        socket.connect(address, 5000);
        assertFalse(socket.isClosed());
        socket.close();
        assertTrue(socket.isClosed());
    }
}
