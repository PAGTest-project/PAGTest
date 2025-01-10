
package org.ice4j.pseudotcp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PseudoTcpSocketImpl_getPTCPOptionTest {

    private PseudoTcpSocketImpl pseudoTcpSocketImpl;
    private PseudoTCPBase pseudoTcp;

    @BeforeEach
    void setUp() {
        pseudoTcp = mock(PseudoTCPBase.class);
        pseudoTcpSocketImpl = new PseudoTcpSocketImpl(12345, mock(DatagramSocket.class));
        pseudoTcpSocketImpl.pseudoTcp = pseudoTcp;
    }

    @Test
    void testGetPTCPOption_ReadTimeout() {
        pseudoTcpSocketImpl.readTimeout = 1000;
        long result = pseudoTcpSocketImpl.getPTCPOption(Option.OPT_READ_TIMEOUT);
        assertEquals(1000, result);
    }

    @Test
    void testGetPTCPOption_WriteTimeout() {
        pseudoTcpSocketImpl.writeTimeout = 2000;
        long result = pseudoTcpSocketImpl.getPTCPOption(Option.OPT_WRITE_TIMEOUT);
        assertEquals(2000, result);
    }

    @Test
    void testGetPTCPOption_OtherOption() {
        when(pseudoTcp.getOption(Option.OPT_NODELAY)).thenReturn(3000L);
        long result = pseudoTcpSocketImpl.getPTCPOption(Option.OPT_NODELAY);
        assertEquals(3000, result);
    }
}
