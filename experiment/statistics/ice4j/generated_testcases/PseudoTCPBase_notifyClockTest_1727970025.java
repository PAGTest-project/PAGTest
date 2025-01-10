
package org.ice4j.pseudotcp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

class PseudoTCPBase_notifyClockTest {

    @Test
    void testNotifyClock_TCPClosedState() {
        PseudoTCPBase pseudoTCPBase = new PseudoTCPBase(null, 0);
        pseudoTCPBase.m_state = PseudoTcpState.TCP_CLOSED;

        pseudoTCPBase.notifyClock(System.currentTimeMillis());

        // No assertions needed as the method should return immediately
    }

    @Test
    void testNotifyClock_RetransmitSegment() {
        PseudoTCPBase pseudoTCPBase = new PseudoTCPBase(null, 0);
        pseudoTCPBase.m_state = PseudoTcpState.TCP_ESTABLISHED;
        pseudoTCPBase.m_rto_base = 1000;
        pseudoTCPBase.m_rx_rto = 2000;
        pseudoTCPBase.m_slist.add(new SSegment(0, 100, false));

        long now = System.currentTimeMillis();
        pseudoTCPBase.notifyClock(now);

        // Verify retransmission logic was triggered
        assertEquals(now, pseudoTCPBase.m_rto_base);
    }

    @Test
    void testNotifyClock_ProbeClosedWindows() {
        PseudoTCPBase pseudoTCPBase = new PseudoTCPBase(null, 0);
        pseudoTCPBase.m_state = PseudoTcpState.TCP_ESTABLISHED;
        pseudoTCPBase.m_lastsend = 1000;
        pseudoTCPBase.m_rx_rto = 2000;
        pseudoTCPBase.m_lastrecv = now() - 20000;

        long now = System.currentTimeMillis();
        pseudoTCPBase.notifyClock(now);

        // Verify closedown was triggered due to idle timeout
        assertEquals(PseudoTcpState.TCP_CLOSED, pseudoTCPBase.m_state);
    }

    @Test
    void testNotifyClock_SendDelayedAcks() {
        PseudoTCPBase pseudoTCPBase = new PseudoTCPBase(null, 0);
        pseudoTCPBase.m_state = PseudoTcpState.TCP_ESTABLISHED;
        pseudoTCPBase.m_t_ack = 1000;
        pseudoTCPBase.m_ack_delay = 500;

        long now = System.currentTimeMillis();
        pseudoTCPBase.notifyClock(now);

        // Verify delayed ack was sent
        assertEquals(0, pseudoTCPBase.m_t_ack);
    }

    @Test
    void testNotifyClock_KeepAliveTimeout() {
        PseudoTCPBase pseudoTCPBase = new PseudoTCPBase(null, 0);
        pseudoTCPBase.m_state = PseudoTcpState.TCP_ESTABLISHED;
        pseudoTCPBase.m_lastrecv = now() - 95000;
        pseudoTCPBase.m_bOutgoing = true;

        long now = System.currentTimeMillis();
        pseudoTCPBase.notifyClock(now);

        // Verify closedown was triggered due to keepalive timeout
        assertEquals(PseudoTcpState.TCP_CLOSED, pseudoTCPBase.m_state);
    }

    private long now() {
        return System.currentTimeMillis();
    }
}
