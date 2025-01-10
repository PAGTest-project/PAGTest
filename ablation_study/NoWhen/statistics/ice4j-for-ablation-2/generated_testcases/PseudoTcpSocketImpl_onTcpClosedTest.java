
package org.ice4j.pseudotcp;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.logging.Level;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class PseudoTcpSocketImpl_onTcpClosedTest {

    @Test
    public void testOnTcpClosedWithException() {
        // Given
        PseudoTcpSocketImpl socketImpl = new PseudoTcpSocketImpl(12345);
        IOException mockException = mock(IOException.class);
        PseudoTCPBase mockTcp = mock(PseudoTCPBase.class);
        socketImpl.pseudoTcp = mockTcp;
        socketImpl.logger = mock(java.util.logging.Logger.class);

        // When
        socketImpl.onTcpClosed(mockTcp, mockException);

        // Then
        verify(socketImpl.logger).log(eq(Level.SEVERE), contains("PseudoTcp closed: "));
        assertFalse(socketImpl.runReceive);
        assertFalse(socketImpl.runClock);
        assertEquals(mockException, socketImpl.exception);
        verify(mockTcp).close(true);
    }

    @Test
    public void testOnTcpClosedWithoutException() {
        // Given
        PseudoTcpSocketImpl socketImpl = new PseudoTcpSocketImpl(12345);
        PseudoTCPBase mockTcp = mock(PseudoTCPBase.class);
        socketImpl.pseudoTcp = mockTcp;
        socketImpl.logger = mock(java.util.logging.Logger.class);

        // When
        socketImpl.onTcpClosed(mockTcp, null);

        // Then
        verify(socketImpl.logger).log(eq(Level.FINE), eq("PseudoTcp closed"));
        assertFalse(socketImpl.runReceive);
        assertFalse(socketImpl.runClock);
        assertNull(socketImpl.exception);
        verify(mockTcp).close(true);
    }
}
