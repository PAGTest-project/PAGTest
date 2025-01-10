
package org.ice4j.pseudotcp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PseudoTcpSocketImpl_setMTUTest {

    @Test
    void testSetMTU() {
        // Given
        PseudoTCPBase mockPseudoTcp = mock(PseudoTCPBase.class);
        PseudoTcpSocketImpl pseudoTcpSocketImpl = new PseudoTcpSocketImpl(12345L);
        pseudoTcpSocketImpl.pseudoTcp = mockPseudoTcp;

        // When
        pseudoTcpSocketImpl.setMTU(1450);

        // Then
        verify(mockPseudoTcp).notifyMTU(1450);
    }
}
