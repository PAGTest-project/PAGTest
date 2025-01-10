
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
        PseudoTCPBase pseudoTCPBase = new PseudoTCPBase(null, 12345L);
        pseudoTCPBase.m_state = PseudoTcpState.TCP_CLOSED;

        pseudoTCPBase.notifyClock(System.currentTimeMillis());

        // No assertions needed as the method should return immediately
    }

    @Test
    void testNotifyClock_RetransmitSegment() {
        PseudoTCPBase pseudoTCPBase = new PseudoTCPBase(null, 12345L);
        pseudoTCPBase.m_state = PseudoTcpState.TCP_ESTABLISHED;
        pseudoTCPBase.m_rto_base = 1000L;
        pseudoTCPBase.m_rx_rto = 2000L;
        pseudoTCPBase.m_slist.add(new SSegment(1L, 100, false));

        long now = System.currentTimeMillis();
        pseudoTCPBase.notifyClock(now);

        // Verify retransmission logic
        assertEquals(now, pseudoTCPBase.m_rto_base);
        assertEquals(4000L, pseudoTCPBase.m_rx_rto);
    }

    @Test
    void testNotifyClock_ProbeClosedWindows() {
        PseudoTCPBase pseudoTCPBase = new PseudoTCPBase(null, 12345L);
        pseudoTCPBase.m_state = PseudoTcpState.TCP_ESTABLISHED;
        pseudoTCPBase.setM_snd_wnd(0);
        pseudoTCPBase.m_lastsend = 1000L;
        pseudoTCPBase.m_rx_rto = 2000L;

        long now = System.currentTimeMillis();
        pseudoTCPBase.notifyClock(now);

        // Verify window probing logic
        assertEquals(now, pseudoTCPBase.m_lastsend);
        assertEquals(4000L, pseudoTCPBase.m_rx_rto);
    }

    @Test
    void testNotifyClock_SendDelayedAcks() {
        PseudoTCPBase pseudoTCPBase = new PseudoTCPBase(null, 12345L);
        pseudoTCPBase.m_state = PseudoTcpState.TCP_ESTABLISHED;
        pseudoTCPBase.m_t_ack = 1000L;
        pseudoTCPBase.m_ack_delay = 500L;

        long now = System.currentTimeMillis();
        pseudoTCPBase.notifyClock(now);

        // Verify delayed ACK logic
        assertEquals(now, pseudoTCPBase.m_t_ack);
    }

    @Test
    void testNotifyClock_IdleTimeout() {
        PseudoTCPBase pseudoTCPBase = new PseudoTCPBase(null, 12345L);
        pseudoTCPBase.m_state = PseudoTcpState.TCP_ESTABLISHED;
        pseudoTCPBase.m_lastrecv = 1000L;
        pseudoTCPBase.m_lasttraffic = 1000L;
        pseudoTCPBase.m_bOutgoing = true;

        long now = System.currentTimeMillis();
        pseudoTCPBase.notifyClock(now);

        // Verify idle timeout logic
        assertEquals(PseudoTcpState.TCP_CLOSED, pseudoTCPBase.m_state);
    }
}
