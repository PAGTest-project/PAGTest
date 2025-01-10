
package org.ice4j.pseudotcp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.ice4j.pseudotcp.PseudoTcpState;

public class PseudoTCPBase_notifyMTUTest {

    @Test
    public void testNotifyMTU_EstablishedState() {
        PseudoTCPBase pseudoTCPBase = new PseudoTCPBase(null, 12345);
        pseudoTCPBase.m_state = PseudoTcpState.TCP_ESTABLISHED;
        pseudoTCPBase.notifyMTU(1500);
        assertEquals(1500, pseudoTCPBase.getMTU());
    }

    @Test
    public void testNotifyMTU_NonEstablishedState() {
        PseudoTCPBase pseudoTCPBase = new PseudoTCPBase(null, 12345);
        pseudoTCPBase.m_state = PseudoTcpState.TCP_LISTEN;
        pseudoTCPBase.notifyMTU(1500);
        assertEquals(1500, pseudoTCPBase.getMTU());
    }
}
