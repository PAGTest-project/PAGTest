
package org.ice4j.pseudotcp;

import org.junit.jupiter.api.Test;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PseudoTcpSocketImpl_getOptionTest {

    @Test
    void testGetOption() throws SocketException {
        PseudoTcpSocketImpl socket = new PseudoTcpSocketImpl(12345);
        Map<Integer, Object> options = new HashMap<>();
        options.put(java.net.SocketOptions.TCP_NODELAY, true);
        options.put(2, "option2");
        socket.setOptions(options);

        // Test for TCP_NODELAY
        assertTrue((boolean) socket.getOption(java.net.SocketOptions.TCP_NODELAY));

        // Test for unknown optID
        assertNull(socket.getOption(3));
    }
}
