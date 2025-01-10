
package org.ice4j.pseudotcp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assertions;
import java.io.IOException;
import org.ice4j.pseudotcp.PseudoTcpState;

public class PseudoTCPBase_connectTest {

    private PseudoTCPBase pseudoTCPBase;

    @BeforeEach
    public void setUp() {
        pseudoTCPBase = new PseudoTCPBase(new PseudoTcpNotify() {
            @Override
            public void tcpWritePacket(PseudoTCPBase tcp, byte[] packet, int len) {
                // Mock implementation
            }
        }, 12345L);
    }

    @Test
    public void testConnect_ValidState() throws IOException {
        pseudoTCPBase.m_state = PseudoTcpState.TCP_LISTEN;
        pseudoTCPBase.connect();
        Assertions.assertEquals(PseudoTcpState.TCP_SYN_SENT, pseudoTCPBase.m_state);
    }

    @Test
    public void testConnect_InvalidState() {
        pseudoTCPBase.m_state = PseudoTcpState.TCP_ESTABLISHED;
        Assertions.assertThrows(IOException.class, () -> {
            pseudoTCPBase.connect();
        });
    }
}
