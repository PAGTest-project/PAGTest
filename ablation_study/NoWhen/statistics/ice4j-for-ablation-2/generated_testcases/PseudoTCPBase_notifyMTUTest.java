
package org.ice4j.pseudotcp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PseudoTCPBase_notifyMTUTest {

    @Test
    void testNotifyMTU_EstablishedState() {
        // Given
        PseudoTcpNotify notify = mock(PseudoTcpNotify.class);
        PseudoTCPBase pseudoTCPBase = new PseudoTCPBase(notify, 12345L);
        pseudoTCPBase.m_state = PseudoTcpState.TCP_ESTABLISHED;

        // When
        pseudoTCPBase.notifyMTU(1500);

        // Then
        assertEquals(1500, pseudoTCPBase.getMTU());
        verify(notify, never()).tcpWritePacket(any(), any(), anyInt());
    }

    @Test
    void testNotifyMTU_NonEstablishedState() {
        // Given
        PseudoTcpNotify notify = mock(PseudoTcpNotify.class);
        PseudoTCPBase pseudoTCPBase = new PseudoTCPBase(notify, 12345L);
        pseudoTCPBase.m_state = PseudoTcpState.TCP_LISTEN;

        // When
        pseudoTCPBase.notifyMTU(1500);

        // Then
        assertEquals(1500, pseudoTCPBase.getMTU());
        verify(notify, never()).tcpWritePacket(any(), any(), anyInt());
    }
}
