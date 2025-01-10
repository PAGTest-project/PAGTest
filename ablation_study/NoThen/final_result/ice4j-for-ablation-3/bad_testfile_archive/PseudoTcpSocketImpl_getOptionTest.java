
package org.ice4j.pseudotcp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PseudoTcpSocketImpl_getOptionTest {

    private PseudoTcpSocketImpl socket;
    private Map<Integer, Object> options;

    @BeforeEach
    void setUp() throws SocketException {
        socket = new PseudoTcpSocketImpl(12345, new DatagramSocket());
        options = new HashMap<>();
        socket.setOptions(options); // Use a setter method to access private field
    }

    @Test
    void testGetOption_TCP_NODELAY_True() throws SocketException {
        options.put(Option.OPT_NODELAY.ordinal(), true);
        assertTrue((Boolean) socket.getOption(1)); // Use the actual value instead of SocketOptions.TCP_NODELAY
    }

    @Test
    void testGetOption_TCP_NODELAY_False() throws SocketException {
        options.put(Option.OPT_NODELAY.ordinal(), false);
        assertFalse((Boolean) socket.getOption(1)); // Use the actual value instead of SocketOptions.TCP_NODELAY
    }

    @Test
    void testGetOption_UnknownOptID_ReturnsNull() throws SocketException {
        assertNull(socket.getOption(999));
    }

    @Test
    void testGetOption_KnownOptID_ReturnsValue() throws SocketException {
        options.put(1, "value");
        assertEquals("value", socket.getOption(1));
    }
}
