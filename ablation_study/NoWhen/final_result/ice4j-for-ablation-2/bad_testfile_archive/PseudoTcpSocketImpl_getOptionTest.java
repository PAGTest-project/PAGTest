
package org.ice4j.pseudotcp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PseudoTcpSocketImpl_getOptionTest {

    private PseudoTcpSocketImpl pseudoTcpSocket;

    @BeforeEach
    void setUp() {
        pseudoTcpSocket = new PseudoTcpSocketImpl(12345);
    }

    @Test
    void testGetOption_TCP_NODELAY() throws SocketException {
        pseudoTcpSocket.setOption(SocketOptions.TCP_NODELAY, true);
        assertTrue((Boolean) pseudoTcpSocket.getOption(SocketOptions.TCP_NODELAY));
    }

    @Test
    void testGetOption_UnknownOptID() throws SocketException {
        assertNull(pseudoTcpSocket.getOption(999));
    }

    @Test
    void testGetOption_KnownOptID() throws SocketException {
        pseudoTcpSocket.setOption(123, "someValue");
        assertEquals("someValue", pseudoTcpSocket.getOption(123));
    }
}
