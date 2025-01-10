
package org.ice4j.pseudotcp;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.UnknownHostException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PseudoTcpSocketImpl_bindTest {

    @Test
    void testBind() throws IOException {
        // Given
        PseudoTcpSocketImpl pseudoTcpSocket = new PseudoTcpSocketImpl(12345);
        InetAddress host = InetAddress.getByName("localhost");
        int port = 12345;

        // When
        pseudoTcpSocket.bind(host, port);

        // Then
        assertNotNull(pseudoTcpSocket.socket);
        assertEquals(new InetSocketAddress(host.getHostAddress(), port), pseudoTcpSocket.socket.getLocalSocketAddress());
    }

    @Test
    void testBindWithExistingSocket() throws IOException {
        // Given
        PseudoTcpSocketImpl pseudoTcpSocket = new PseudoTcpSocketImpl(12345);
        pseudoTcpSocket.socket = mock(DatagramSocket.class);
        InetAddress host = InetAddress.getByName("localhost");
        int port = 12345;

        // When
        pseudoTcpSocket.bind(host, port);

        // Then
        verify(pseudoTcpSocket.socket).close();
        assertNotNull(pseudoTcpSocket.socket);
        assertEquals(new InetSocketAddress(host.getHostAddress(), port), pseudoTcpSocket.socket.getLocalSocketAddress());
    }

    @Test
    void testBindWithIOException() throws IOException {
        // Given
        PseudoTcpSocketImpl pseudoTcpSocket = new PseudoTcpSocketImpl(12345);
        InetAddress host = InetAddress.getByName("localhost");
        int port = 12345;

        // Mocking DatagramSocket to throw IOException
        DatagramSocket mockSocket = mock(DatagramSocket.class);
        doThrow(new IOException("Mocked IOException")).when(mockSocket).bind(any(SocketAddress.class));

        // When
        pseudoTcpSocket.socket = mockSocket;
        assertThrows(IOException.class, () -> pseudoTcpSocket.bind(host, port));
    }
}
