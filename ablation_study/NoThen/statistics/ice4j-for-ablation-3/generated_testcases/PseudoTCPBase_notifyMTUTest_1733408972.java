
package org.ice4j.pseudotcp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.ice4j.pseudotcp.PseudoTCPBase.PseudoTcpState;

public class PseudoTCPBase_notifyMTUTest {

    @Test
    public void testNotifyMTU_EstablishedState() {
        // Given
        PseudoTCPBase pseudoTCPBase = new PseudoTCPBase(null, 12345L);
        pseudoTCPBase.m_state = PseudoTcpState.TCP_ESTABLISHED;

        // When
        pseudoTCPBase.notifyMTU(1500);

        // Then
        assertEquals(1500, pseudoTCPBase.getMTU());
    }

    @Test
    public void testNotifyMTU_NonEstablishedState() {
        // Given
        PseudoTCPBase pseudoTCPBase = new PseudoTCPBase(null, 12345L);
        pseudoTCPBase.m_state = PseudoTcpState.TCP_LISTEN;

        // When
        pseudoTCPBase.notifyMTU(1500);

        // Then
        assertEquals(1500, pseudoTCPBase.getMTU());
    }
}
