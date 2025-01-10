
package org.ice4j.pseudotcp;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.logging.Level;
import java.util.logging.Logger;

import static org.mockito.Mockito.*;

public class PseudoTcpSocketImpl_onTcpReadableTest {

    @Test
    public void testOnTcpReadable() {
        // Given
        PseudoTcpSocketImpl socketImpl = new PseudoTcpSocketImpl(12345);
        PseudoTCPBase tcp = mock(PseudoTCPBase.class);
        Logger logger = mock(Logger.class);
        socketImpl.logger = logger;

        when(tcp.getAvailable()).thenReturn(100);
        when(logger.isLoggable(Level.FINER)).thenReturn(true);

        // When
        socketImpl.onTcpReadable(tcp);

        // Then
        verify(logger).log(Level.FINER, "TCP READABLE data available for reading: 100");
        verify(tcp, times(1)).getAvailable();
        verify(logger, times(1)).isLoggable(Level.FINER);
        verify(logger, times(1)).log(eq(Level.FINER), anyString());
    }
}
