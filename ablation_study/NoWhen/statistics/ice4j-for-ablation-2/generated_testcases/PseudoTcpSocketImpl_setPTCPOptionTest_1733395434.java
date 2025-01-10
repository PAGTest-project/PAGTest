
package org.ice4j.pseudotcp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PseudoTcpSocketImpl_setPTCPOptionTest {

    @Test
    void testSetPTCPOptionWriteTimeout() {
        PseudoTcpSocketImpl socket = new PseudoTcpSocketImpl(12345);
        socket.setPTCPOption(Option.OPT_WRITE_TIMEOUT, 1000);
        assertEquals(1000, socket.getPTCPOption(Option.OPT_WRITE_TIMEOUT));
    }

    @Test
    void testSetPTCPOptionReadTimeout() {
        PseudoTcpSocketImpl socket = new PseudoTcpSocketImpl(12345);
        socket.setPTCPOption(Option.OPT_READ_TIMEOUT, 2000);
        assertEquals(2000, socket.getPTCPOption(Option.OPT_READ_TIMEOUT));
    }

    @Test
    void testSetPTCPOptionOtherOption() {
        PseudoTcpSocketImpl socket = new PseudoTcpSocketImpl(12345);
        PseudoTCPBase mockPseudoTcp = mock(PseudoTCPBase.class);
        socket.pseudoTcp = mockPseudoTcp;

        socket.setPTCPOption(Option.OPT_NODELAY, 1);
        verify(mockPseudoTcp).setOption(Option.OPT_NODELAY, 1);
    }
}
