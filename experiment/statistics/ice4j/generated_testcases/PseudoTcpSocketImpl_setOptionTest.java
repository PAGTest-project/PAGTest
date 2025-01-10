
package org.ice4j.pseudotcp;

import org.junit.jupiter.api.Test;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PseudoTcpSocketImpl_setOptionTest {

    @Test
    public void testSetOption() throws SocketException {
        // Given
        PseudoTcpSocketImpl socketImpl = new PseudoTcpSocketImpl(12345);
        int optID = SocketOptions.TCP_NODELAY;
        Object value = true;

        // When
        socketImpl.setOption(optID, value);

        // Then
        assertEquals(value, socketImpl.getOption(optID));
    }
}
