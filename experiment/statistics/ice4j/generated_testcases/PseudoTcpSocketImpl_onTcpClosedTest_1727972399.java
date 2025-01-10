
package org.ice4j.pseudotcp;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.mockito.Mockito.*;

public class PseudoTcpSocketImpl_onTcpClosedTest {

    @Test
    public void testOnTcpClosedWithException() {
        // Given
        PseudoTcpSocketImpl socketImpl = new PseudoTcpSocketImpl(12345);
        IOException mockException = mock(IOException.class);
        Logger mockLogger = mock(Logger.class);
        socketImpl.logger = mockLogger;

        // When
        socketImpl.onTcpClosed(mock(PseudoTCPBase.class), mockException);

        // Then
        verify(mockLogger).log(Level.SEVERE, "PseudoTcp closed: " + mockException);
        assert(!socketImpl.isRunReceive());
        assert(!socketImpl.isRunClock());
        assert(socketImpl.getException() == mockException);
        // Assuming releaseAllLocks and cancelClockTask are tested elsewhere
    }

    @Test
    public void testOnTcpClosedWithoutException() {
        // Given
        PseudoTcpSocketImpl socketImpl = new PseudoTcpSocketImpl(12345);
        Logger mockLogger = mock(Logger.class);
        socketImpl.logger = mockLogger;

        // When
        socketImpl.onTcpClosed(mock(PseudoTCPBase.class), null);

        // Then
        verify(mockLogger).log(Level.FINE, "PseudoTcp closed");
        assert(!socketImpl.isRunReceive());
        assert(!socketImpl.isRunClock());
        assert(socketImpl.getException() == null);
        // Assuming releaseAllLocks and cancelClockTask are tested elsewhere
    }
}
