
package org.ice4j.pseudotcp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.ice4j.pseudotcp.PseudoTCPBase;
import org.ice4j.pseudotcp.PseudoTcpState;

public class PseudoTCPBase_notifyMTUTest {

    @Test
    public void testNotifyMTU_EstablishedState() {
        PseudoTCPBase pseudoTCPBase = new PseudoTCPBase(null, 0);
        pseudoTCPBase.m_state = PseudoTcpState.TCP_ESTABLISHED;
        pseudoTCPBase.notifyMTU(1500);
        assertEquals(1500, pseudoTCPBase.m_mtu_advise);
    }

    @Test
    public void testNotifyMTU_NonEstablishedState() {
        PseudoTCPBase pseudoTCPBase = new PseudoTCPBase(null, 0);
        pseudoTCPBase.m_state = PseudoTcpState.TCP_LISTEN;
        pseudoTCPBase.notifyMTU(1500);
        assertEquals(1500, pseudoTCPBase.m_mtu_advise);
    }
}
