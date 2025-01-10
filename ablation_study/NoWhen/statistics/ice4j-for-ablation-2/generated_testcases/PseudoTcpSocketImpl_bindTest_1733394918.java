
package org.ice4j.pseudotcp;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PseudoTcpSocketImpl_bindTest {

    @Test
    void testBindWithExistingSocket() throws IOException {
        PseudoTcpSocketImpl pseudoTcpSocket = new PseudoTcpSocketImpl(12345);
        DatagramSocket mockSocket = mock(DatagramSocket.class);
        pseudoTcpSocket.socket = mockSocket;

        InetAddress host = InetAddress.getByName("127.0.0.1");
        int port = 12345;

        pseudoTcpSocket.bind(host, port);

        verify(mockSocket).close();
        assertNotNull(pseudoTcpSocket.socket);
    }

    @Test
    void testBindWithoutExistingSocket() throws IOException {
        PseudoTcpSocketImpl pseudoTcpSocket = new PseudoTcpSocketImpl(12345);

        InetAddress host = InetAddress.getByName("127.0.0.1");
        int port = 12345;

        pseudoTcpSocket.bind(host, port);

        assertNotNull(pseudoTcpSocket.socket);
    }

    @Test
    void testBindWithSocketException() throws IOException {
        PseudoTcpSocketImpl pseudoTcpSocket = new PseudoTcpSocketImpl(12345);
        DatagramSocket mockSocket = mock(DatagramSocket.class);
        pseudoTcpSocket.socket = mockSocket;

        doThrow(new SocketException("Mocked exception")).when(mockSocket).close();

        InetAddress host = InetAddress.getByName("127.0.0.1");
        int port = 12345;

        assertThrows(SocketException.class, () -> pseudoTcpSocket.bind(host, port));
    }
}
