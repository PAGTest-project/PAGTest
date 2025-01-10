
package org.ice4j.pseudotcp;

import org.junit.jupiter.api.Test;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PseudoTcpSocketImpl_setOptionTest {

    @Test
    public void testSetOption() throws SocketException {
        PseudoTcpSocketImpl socket = new PseudoTcpSocketImpl(12345);
        int optID = 1;
        Object value = "testValue";

        socket.setOption(optID, value);

        assertEquals(value, socket.getOptions().get(optID));
    }
}
