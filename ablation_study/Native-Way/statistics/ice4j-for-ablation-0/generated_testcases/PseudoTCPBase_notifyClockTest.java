
package org.ice4j.pseudotcp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

class PseudoTCPBase_notifyClockTest {

    @Test
    void testNotifyClock_TCP_CLOSED() {
        PseudoTCPBase pseudoTCPBase = new PseudoTCPBase(null, 0);
        pseudoTCPBase.m_state = PseudoTcpState.TCP_CLOSED;

        pseudoTCPBase.notifyClock(1000);

        // No action should be taken when state is TCP_CLOSED
        assertEquals(PseudoTcpState.TCP_CLOSED, pseudoTCPBase.m_state);
    }

    @Test
    void testNotifyClock_RetransmitSegment() {
        PseudoTCPBase pseudoTCPBase = new PseudoTCPBase(null, 0);
        pseudoTCPBase.m_state = PseudoTcpState.TCP_ESTABLISHED;
        pseudoTCPBase.m_rto_base = 1000;
        pseudoTCPBase.m_rx_rto = 2000;
        pseudoTCPBase.m_slist.add(new SSegment(1000, 100, false));

        pseudoTCPBase.notifyClock(4000);

        // Verify retransmission logic
        assertEquals(4000, pseudoTCPBase.m_rto_base);
    }

    @Test
    void testNotifyClock_ProbeClosedWindows() {
        PseudoTCPBase pseudoTCPBase = new PseudoTCPBase(null, 0);
        pseudoTCPBase.m_state = PseudoTcpState.TCP_ESTABLISHED;
        pseudoTCPBase.m_lastsend = 1000;
        pseudoTCPBase.m_rx_rto = 2000;
        pseudoTCPBase.m_lastrecv = 0;
        pseudoTCPBase.m_snd_wnd = 0;

        pseudoTCPBase.notifyClock(4000);

        // Verify window probing logic
        assertEquals(4000, pseudoTCPBase.m_lastsend);
    }

    @Test
    void testNotifyClock_SendDelayedAcks() {
        PseudoTCPBase pseudoTCPBase = new PseudoTCPBase(null, 0);
        pseudoTCPBase.m_state = PseudoTcpState.TCP_ESTABLISHED;
        pseudoTCPBase.m_t_ack = 1000;
        pseudoTCPBase.m_ack_delay = 2000;

        pseudoTCPBase.notifyClock(4000);

        // Verify delayed ACK logic
        assertEquals(0, pseudoTCPBase.m_t_ack);
    }

    @Test
    void testNotifyClock_IdleTimeout() {
        PseudoTCPBase pseudoTCPBase = new PseudoTCPBase(null, 0);
        pseudoTCPBase.m_state = PseudoTcpState.TCP_ESTABLISHED;
        pseudoTCPBase.m_lastrecv = 1000;
        pseudoTCPBase.m_bOutgoing = true;

        pseudoTCPBase.notifyClock(91000);

        // Verify idle timeout logic
        assertEquals(PseudoTcpState.TCP_CLOSED, pseudoTCPBase.m_state);
    }
}
