
package org.ice4j.pseudotcp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.logging.Level;
import java.util.logging.Logger;

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
        verify(logger).log(Level.FINER, "stream writeable");
        verify(logger).log(Level.FINER, "write notified - now !");
    }
}
