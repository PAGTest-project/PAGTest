
package org.ice4j.pseudotcp;

import static org.junit.jupiter.api.Assertions.*;
import java.io.*;
import java.net.*;
import org.junit.jupiter.api.*;

public class PseudoTcpSocket_isConnectingTest {

    private PseudoTcpSocket socket;

    @BeforeEach
    public void setUp() throws SocketException {
        socket = new PseudoTcpSocketFactory().createSocket();
    }

    @Test
    public void testIsConnectingWhenConnecting() throws IOException {
        socket.connect(new InetSocketAddress("localhost", 8080), 5000);
        assertTrue(socket.isConnecting());
    }

    @Test
    public void testIsConnectingWhenConnected() throws IOException {
        socket.connect(new InetSocketAddress("localhost", 8080), 5000);
        socket.accept(5000);
        assertFalse(socket.isConnecting());
    }

    @Test
    public void testIsConnectingWhenClosed() throws IOException {
        socket.close();
        assertFalse(socket.isConnecting());
    }

    @Test
    public void testIsConnectingWhenNotConnected() {
        assertFalse(socket.isConnecting());
    }

    @Test
    public void testIsConnectingAfterClose() throws IOException {
        socket.connect(new InetSocketAddress("localhost", 8080), 5000);
        socket.close();
        assertFalse(socket.isConnecting());
    }
}
