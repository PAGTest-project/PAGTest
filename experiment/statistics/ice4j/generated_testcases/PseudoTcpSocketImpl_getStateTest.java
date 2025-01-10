
package org.ice4j.pseudotcp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PseudoTcpSocketImpl_getStateTest {

    @Test
    void testGetState() {
        // Given
        PseudoTCPBase mockPseudoTcp = mock(PseudoTCPBase.class);
        PseudoTcpSocketImpl pseudoTcpSocketImpl = new PseudoTcpSocketImpl(12345L) {
            @Override
            public PseudoTCPBase getPseudoTcp() {
                return mockPseudoTcp;
            }
        };

        PseudoTcpState expectedState = PseudoTcpState.SYN_SENT;
        when(mockPseudoTcp.getState()).thenReturn(expectedState);

        // When
        PseudoTcpState actualState = pseudoTcpSocketImpl.getState();

        // Then
        assertEquals(expectedState, actualState);
    }
}
