
package org.ice4j.pseudotcp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class PseudoTCPBase_getNextClockTest {

    @Test
    public void testGetNextClock() {
        PseudoTCPBase pseudoTCPBase = new PseudoTCPBase(null, 12345L);
        long now = PseudoTCPBase.now();

        // Test case for SD_FORCEFUL shutdown
        pseudoTCPBase.m_shutdown = EnShutdown.SD_FORCEFUL;
        assertEquals(-1, pseudoTCPBase.getNextClock(now));

        // Test case for SD_GRACEFUL shutdown with TCP_ESTABLISHED state
        pseudoTCPBase.m_shutdown = EnShutdown.SD_GRACEFUL;
        pseudoTCPBase.m_state = PseudoTcpState.TCP_ESTABLISHED;
        pseudoTCPBase.m_sbuf.clear();
        pseudoTCPBase.m_t_ack = 0;
        assertEquals(-1, pseudoTCPBase.getNextClock(now));

        // Test case for TCP_CLOSED state
        pseudoTCPBase.m_state = PseudoTcpState.TCP_CLOSED;
        assertEquals(PseudoTCPBase.CLOSED_TIMEOUT, pseudoTCPBase.getNextClock(now));

        // Test case for default timeout calculation
        pseudoTCPBase.m_state = PseudoTcpState.TCP_ESTABLISHED;
        pseudoTCPBase.m_shutdown = EnShutdown.SD_NONE;
        pseudoTCPBase.m_t_ack = now - 1000;
        pseudoTCPBase.m_rto_base = now - 2000;
        pseudoTCPBase.m_lastsend = now - 3000;
        pseudoTCPBase.m_lasttraffic = now - 4000;
        long expectedTimeout = Math.min(Math.min(Math.min(PseudoTCPBase.DEFAULT_TIMEOUT, 1000), 2000), 3000);
        assertEquals(expectedTimeout, pseudoTCPBase.getNextClock(now));
    }
}
