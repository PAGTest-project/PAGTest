
package org.ice4j.pseudotcp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.ice4j.pseudotcp.PseudoTCPBase;
import org.ice4j.pseudotcp.PseudoTcpState;
import org.ice4j.pseudotcp.PseudoTcpNotify;

class PseudoTCPBase_getStateTest {

    @Test
    void testGetState() {
        // Given
        PseudoTcpNotify notify = new PseudoTcpNotify() {
            // Mock implementation of PseudoTcpNotify
        };
        long conv = 123456L;
        PseudoTCPBase pseudoTCPBase = new PseudoTCPBase(notify, conv);

        // When
        PseudoTcpState state = pseudoTCPBase.getState();

        // Then
        assertEquals(PseudoTcpState.TCP_LISTEN, state);
    }
}
