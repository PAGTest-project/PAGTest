
package org.ice4j.pseudotcp;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.net.DatagramSocket;

public class PseudoTcpSocketImpl_closeTest {

    @Test
    public void testCloseSuccess() throws IOException {
        // Given
        PseudoTCPBase pseudoTcp = mock(PseudoTCPBase.class);
        DatagramSocket socket = mock(DatagramSocket.class);
        PseudoTcpSocketImpl pseudoTcpSocket = new PseudoTcpSocketImpl(12345, socket) {
            @Override
            PseudoTCPBase getPseudoTcp() {
                return pseudoTcp;
            }
        };

        // When
        pseudoTcpSocket.close();

        // Then
        verify(pseudoTcp).close(true);
        verify(socket).close();
    }

    @Test
    public void testCloseInterrupted() throws IOException {
        // Given
        PseudoTCPBase pseudoTcp = mock(PseudoTCPBase.class);
        DatagramSocket socket = mock(DatagramSocket.class);
        PseudoTcpSocketImpl pseudoTcpSocket = new PseudoTcpSocketImpl(12345, socket) {
            @Override
            PseudoTCPBase getPseudoTcp() {
                return pseudoTcp;
            }
        };

        doThrow(new InterruptedException()).when(pseudoTcp).close(true);

        // When
        IOException exception = assertThrows(IOException.class, pseudoTcpSocket::close);

        // Then
        assertEquals("Closing socket interrupted", exception.getMessage());
        verify(socket).close();
    }
}
