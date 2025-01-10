
package org.ice4j.pseudotcp;

import static org.junit.jupiter.api.Assertions.*;
import java.io.*;
import java.net.*;
import org.junit.jupiter.api.*;

public class PseudoTcpSocket_connectTest {

    private PseudoTcpSocket socket;
    private PseudoTcpSocketImpl socketImpl;

    @BeforeEach
    public void setUp() throws SocketException {
        socketImpl = new PseudoTcpSocketImpl(0, new DatagramSocket());
        socket = new PseudoTcpSocket(socketImpl);
    }

    @Test
    public void testConnectSuccess() throws IOException {
        InetSocketAddress address = new InetSocketAddress("localhost", 12345);
        socket.connect(address);
        assertTrue(socket.isConnected());
    }

    @Test
    public void testConnectAlreadyConnected() throws IOException {
        InetSocketAddress address = new InetSocketAddress("localhost", 12345);
        socket.connect(address);
        assertThrows(SocketException.class, () -> socket.connect(address));
    }

    @Test
    public void testConnectSocketClosed() throws IOException {
        socket.close();
        InetSocketAddress address = new InetSocketAddress("localhost", 12345);
        assertThrows(SocketException.class, () -> socket.connect(address));
    }

    @Test
    public void testConnectInvalidAddress() {
        SocketAddress address = new InetSocketAddress("invalidhost", 12345);
        assertThrows(UnknownHostException.class, () -> socket.connect(address));
    }

    @Test
    public void testConnectInvalidPort() {
        SocketAddress address = new InetSocketAddress("localhost", 65536);
        assertThrows(IllegalArgumentException.class, () -> socket.connect(address));
    }

    @Test
    public void testConnectNullAddress() {
        assertThrows(IllegalArgumentException.class, () -> socket.connect(null));
    }

    @Test
    public void testConnectNonInetSocketAddress() {
        SocketAddress address = new SocketAddress() {};
        assertThrows(IllegalArgumentException.class, () -> socket.connect(address));
    }
}
