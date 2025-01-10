
package org.ice4j.pseudotcp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PseudoTCPBase_notifyClockTest {

    @Test
    public void testNotifyClock_TCP_CLOSED() {
        PseudoTCPBase pseudoTCPBase = new PseudoTCPBase(null, 12345L);
        pseudoTCPBase.m_state = PseudoTcpState.TCP_CLOSED;

        pseudoTCPBase.notifyClock(System.currentTimeMillis());

        // No assertions needed as the method should return immediately
    }

    @Test
    public void testNotifyClock_RetransmitSegment() {
        PseudoTCPBase pseudoTCPBase = new PseudoTCPBase(null, 12345L);
        pseudoTCPBase.m_state = PseudoTcpState.TCP_ESTABLISHED;
        pseudoTCPBase.m_rto_base = 1000L;
        pseudoTCPBase.m_rx_rto = 2000L;
        pseudoTCPBase.m_slist.add(new SSegment(1L, 100, false));
        Logger logger = mock(Logger.class);
        pseudoTCPBase.logger = logger;

        long now = System.currentTimeMillis();
        pseudoTCPBase.notifyClock(now);

        verify(logger).log(eq(Level.FINER), anyString());
        assertEquals(now, pseudoTCPBase.m_rto_base);
    }

    @Test
    public void testNotifyClock_ProbeClosedWindows() {
        PseudoTCPBase pseudoTCPBase = new PseudoTCPBase(null, 12345L);
        pseudoTCPBase.m_state = PseudoTcpState.TCP_ESTABLISHED;
        pseudoTCPBase.m_rto_base = 1000L;
        pseudoTCPBase.m_rx_rto = 2000L;
        pseudoTCPBase.m_lastsend = now() - 2000L;
        pseudoTCPBase.m_lastrecv = now() - 16000L;
        Logger logger = mock(Logger.class);
        pseudoTCPBase.logger = logger;

        long now = System.currentTimeMillis();
        pseudoTCPBase.notifyClock(now);

        verify(logger).log(eq(Level.FINE), anyString());
        assertEquals(PseudoTcpState.TCP_CLOSED, pseudoTCPBase.m_state);
    }

    @Test
    public void testNotifyClock_SendDelayedAcks() {
        PseudoTCPBase pseudoTCPBase = new PseudoTCPBase(null, 12345L);
        pseudoTCPBase.m_state = PseudoTcpState.TCP_ESTABLISHED;
        pseudoTCPBase.m_t_ack = now() - 100L;
        pseudoTCPBase.m_ack_delay = 100L;
        Logger logger = mock(Logger.class);
        pseudoTCPBase.logger = logger;

        long now = System.currentTimeMillis();
        pseudoTCPBase.notifyClock(now);

        verify(logger).log(eq(Level.FINE), anyString());
    }

    @Test
    public void testNotifyClock_IdleTimeout() {
        PseudoTCPBase pseudoTCPBase = new PseudoTCPBase(null, 12345L);
        pseudoTCPBase.m_state = PseudoTcpState.TCP_ESTABLISHED;
        pseudoTCPBase.m_lastrecv = now() - 90000L;
        Logger logger = mock(Logger.class);
        pseudoTCPBase.logger = logger;

        long now = System.currentTimeMillis();
        pseudoTCPBase.notifyClock(now);

        verify(logger).log(eq(Level.FINE), anyString());
        assertEquals(PseudoTcpState.TCP_CLOSED, pseudoTCPBase.m_state);
    }

    private long now() {
        return System.currentTimeMillis();
    }
}
