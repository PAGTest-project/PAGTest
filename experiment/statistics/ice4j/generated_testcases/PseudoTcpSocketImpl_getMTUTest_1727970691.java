
package org.ice4j.pseudotcp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PseudoTcpSocketImpl_getMTUTest {

    private PseudoTcpSocketImpl pseudoTcpSocketImpl;
    private PseudoTCPBase mockPseudoTcp;

    @BeforeEach
    public void setUp() {
        mockPseudoTcp = mock(PseudoTCPBase.class);
        pseudoTcpSocketImpl = new PseudoTcpSocketImpl(12345L);
        pseudoTcpSocketImpl.pseudoTcp = mockPseudoTcp;
    }

    @Test
    public void testGetMTU() {
        int expectedMTU = 1450;
        when(mockPseudoTcp.getMTU()).thenReturn(expectedMTU);

        int actualMTU = pseudoTcpSocketImpl.getMTU();

        assertEquals(expectedMTU, actualMTU);
    }
}
