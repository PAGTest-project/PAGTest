
package org.ice4j.pseudotcp;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PseudoTcpSocketImpl_bindTest {

    @Test
    void testBindWithExistingSocket() throws IOException {
        // Given
        PseudoTcpSocketImpl pseudoTcpSocket = new PseudoTcpSocketImpl(12345);
        DatagramSocket mockSocket = mock(DatagramSocket.class);
        pseudoTcpSocket.socket = mockSocket;
        InetAddress host = InetAddress.getByName("127.0.0.1");
        int port = 12345;

        // When
        pseudoTcpSocket.bind(host, port);

        // Then
        verify(mockSocket).close();
        assertNotNull(pseudoTcpSocket.socket);
        assertEquals(new InetSocketAddress(host.getHostAddress(), port), ((InetSocketAddress) pseudoTcpSocket.socket.getLocalSocketAddress()));
    }

    @Test
    void testBindWithoutExistingSocket() throws IOException {
        // Given
        PseudoTcpSocketImpl pseudoTcpSocket = new PseudoTcpSocketImpl(12345);
        InetAddress host = InetAddress.getByName("127.0.0.1");
        int port = 12345;

        // When
        pseudoTcpSocket.bind(host, port);

        // Then
        assertNotNull(pseudoTcpSocket.socket);
        assertEquals(new InetSocketAddress(host.getHostAddress(), port), ((InetSocketAddress) pseudoTcpSocket.socket.getLocalSocketAddress()));
    }
}
