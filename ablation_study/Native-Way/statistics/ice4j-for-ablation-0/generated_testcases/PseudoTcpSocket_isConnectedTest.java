
package org.ice4j.pseudotcp;

import static org.junit.jupiter.api.Assertions.*;
import java.io.*;
import java.net.*;
import org.junit.jupiter.api.*;

public class PseudoTcpSocket_isConnectedTest {

    private PseudoTcpSocket socket;
    private PseudoTcpSocketImpl socketImpl;

    @BeforeEach
    public void setUp() throws SocketException {
        socketImpl = new PseudoTcpSocketImpl(0, new DatagramSocket());
        socket = new PseudoTcpSocket(socketImpl);
    }

    @Test
    public void testIsConnectedWhenConnected() throws IOException {
        // Simulate a connected state
        socketImpl.setState(PseudoTcpState.TCP_ESTABLISHED);
        assertTrue(socket.isConnected());
    }

    @Test
    public void testIsConnectedWhenNotConnected() throws IOException {
        // Simulate a non-connected state
        socketImpl.setState(PseudoTcpState.TCP_CLOSED);
        assertFalse(socket.isConnected());
    }

    @Test
    public void testIsConnectedAfterConnect() throws IOException {
        // Simulate connecting the socket
        socket.connect(new InetSocketAddress("localhost", 8080), 5000);
        assertTrue(socket.isConnected());
    }

    @Test
    public void testIsConnectedAfterClose() throws IOException {
        // Simulate connecting and then closing the socket
        socket.connect(new InetSocketAddress("localhost", 8080), 5000);
        socket.close();
        assertFalse(socket.isConnected());
    }
}
