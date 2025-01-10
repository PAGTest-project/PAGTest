
package org.ice4j.pseudotcp;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.logging.Level;
import java.util.logging.Logger;

import static org.mockito.Mockito.*;

public class PseudoTcpSocketImpl_onTcpWriteableTest {

    @Test
    public void testOnTcpWriteable() throws Exception {
        // Given
        PseudoTcpSocketImpl socketImpl = new PseudoTcpSocketImpl(12345);
        PseudoTCPBase tcp = mock(PseudoTCPBase.class);
        Logger logger = mock(Logger.class);
        socketImpl.logger = logger;

        // When
        socketImpl.onTcpWriteable(tcp);

        // Then
        verify(logger).log(eq(Level.FINER), eq("stream writeable"));
        verify(logger).log(eq(Level.FINER), eq("write notified - now !"));
        verify(socketImpl.write_notify, times(1)).notifyAll();
    }
}
