
package org.ice4j.pseudotcp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.net.DatagramSocket;
import java.net.SocketException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PseudoTcpSocketImpl_getPTCPOptionTest {

    private PseudoTcpSocketImpl pseudoTcpSocket;
    private PseudoTCPBase mockPseudoTcp;

    @BeforeEach
    public void setUp() throws SocketException {
        mockPseudoTcp = mock(PseudoTCPBase.class);
        pseudoTcpSocket = new PseudoTcpSocketImpl(12345, new DatagramSocket()) {
            @Override
            public PseudoTCPBase getPseudoTcp() {
                return mockPseudoTcp;
            }
        };
    }

    @Test
    public void testGetPTCPOption_ReadTimeout() {
        pseudoTcpSocket.setPTCPOption(Option.OPT_READ_TIMEOUT, 1000);
        assertEquals(1000, pseudoTcpSocket.getPTCPOption(Option.OPT_READ_TIMEOUT));
    }

    @Test
    public void testGetPTCPOption_WriteTimeout() {
        pseudoTcpSocket.setPTCPOption(Option.OPT_WRITE_TIMEOUT, 2000);
        assertEquals(2000, pseudoTcpSocket.getPTCPOption(Option.OPT_WRITE_TIMEOUT));
    }

    @Test
    public void testGetPTCPOption_OtherOption() {
        when(mockPseudoTcp.getOption(Option.OPT_OTHER)).thenReturn(3000L);
        assertEquals(3000, pseudoTcpSocket.getPTCPOption(Option.OPT_OTHER));
    }
}
