
package org.ice4j.pseudotcp;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PseudoTCPBase_connectTest {

    @Test
    void testConnect_InvalidState() {
        PseudoTCPBase pseudoTCPBase = new PseudoTCPBase(null, 0);
        pseudoTCPBase.m_state = PseudoTcpState.TCP_ESTABLISHED;

        IOException exception = assertThrows(IOException.class, pseudoTCPBase::connect);
        assertEquals("Invalid socket state: TCP_ESTABLISHED", exception.getMessage());
    }

    @Test
    void testConnect_ValidState() throws IOException {
        PseudoTCPBase pseudoTCPBase = new PseudoTCPBase(null, 0);
        pseudoTCPBase.m_state = PseudoTcpState.TCP_LISTEN;

        Logger logger = mock(Logger.class);
        pseudoTCPBase.logger = logger;

        pseudoTCPBase.connect();

        assertEquals(PseudoTcpState.TCP_SYN_SENT, pseudoTCPBase.m_state);
        verify(logger).log(eq(Level.FINE), eq("State: TCP_SYN_SENT"), anyString());
        verify(pseudoTCPBase).queueConnectMessage();
        verify(pseudoTCPBase).attemptSend(SendFlags.sfNone);
    }
}
