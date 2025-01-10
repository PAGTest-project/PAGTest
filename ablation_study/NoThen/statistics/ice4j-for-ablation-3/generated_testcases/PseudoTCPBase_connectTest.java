
package org.ice4j.pseudotcp;

import org.junit.jupiter.api.Test;
import org.ice4j.pseudotcp.util.PseudoTcpState;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class PseudoTCPBase_connectTest {

    @Test
    void testConnect_ValidState() throws IOException {
        PseudoTCPBase pseudoTCPBase = new PseudoTCPBase(null, 12345);
        pseudoTCPBase.m_state = PseudoTcpState.TCP_LISTEN;

        pseudoTCPBase.connect();

        assertEquals(PseudoTcpState.TCP_SYN_SENT, pseudoTCPBase.m_state);
    }

    @Test
    void testConnect_InvalidState() {
        PseudoTCPBase pseudoTCPBase = new PseudoTCPBase(null, 12345);
        pseudoTCPBase.m_state = PseudoTcpState.TCP_ESTABLISHED;

        assertThrows(IOException.class, pseudoTCPBase::connect);
    }
}
