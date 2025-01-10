
package org.ice4j.pseudotcp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.net.SocketException;
import java.net.InetSocketAddress;

public class PseudoTcpSocket_setConversationIDTest {
    private PseudoTcpSocket socket;
    private PseudoTcpSocketImpl socketImpl;

    @BeforeEach
    public void setUp() throws SocketException {
        socketImpl = new PseudoTcpSocketImpl(12345);
        socket = new PseudoTcpSocket(socketImpl);
    }

    @Test
    public void testSetConversationIDSuccess() {
        assertDoesNotThrow(() -> socket.setConversationID(54321));
        assertEquals(54321, socket.getConversationID());
    }

    @Test
    public void testSetConversationIDOnConnectedSocket() throws IOException {
        socket.connect(new InetSocketAddress("localhost", 8080), 5000);
        assertThrows(IllegalStateException.class, () -> socket.setConversationID(54321));
    }

    @Test
    public void testSetConversationIDOnClosedSocket() throws IOException {
        socket.close();
        assertThrows(IllegalStateException.class, () -> socket.setConversationID(54321));
    }
}
