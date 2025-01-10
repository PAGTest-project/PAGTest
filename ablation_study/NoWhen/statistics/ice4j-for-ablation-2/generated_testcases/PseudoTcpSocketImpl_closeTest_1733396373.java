
package org.ice4j.pseudotcp;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.net.DatagramSocket;

import static org.mockito.Mockito.*;

public class PseudoTcpSocketImpl_closeTest {

    @Test
    public void testClose_Successful() throws IOException, InterruptedException {
        // Given
        PseudoTCPBase pseudoTcp = mock(PseudoTCPBase.class);
        DatagramSocket socket = mock(DatagramSocket.class);
        PseudoTcpSocketImpl pseudoTcpSocket = new PseudoTcpSocketImpl(12345, socket) {
            @Override
            public void onTcpClosed(PseudoTCPBase tcp, IOException e) {
                // Mock implementation
            }

            @Override
            public void joinAllThreads() throws InterruptedException {
                // Mock implementation
            }
        };
        pseudoTcpSocket.pseudoTcp = pseudoTcp;

        // When
        pseudoTcpSocket.close();

        // Then
        verify(pseudoTcp).close(true);
        verify(socket).close();
        verify(pseudoTcpSocket).joinAllThreads();
    }

    @Test
    public void testClose_Interrupted() throws IOException, InterruptedException {
        // Given
        PseudoTCPBase pseudoTcp = mock(PseudoTCPBase.class);
        DatagramSocket socket = mock(DatagramSocket.class);
        PseudoTcpSocketImpl pseudoTcpSocket = new PseudoTcpSocketImpl(12345, socket) {
            @Override
            public void onTcpClosed(PseudoTCPBase tcp, IOException e) {
                // Mock implementation
            }

            @Override
            public void joinAllThreads() throws InterruptedException {
                throw new InterruptedException("Mock interruption");
            }
        };
        pseudoTcpSocket.pseudoTcp = pseudoTcp;

        // When
        try {
            pseudoTcpSocket.close();
        } catch (IOException e) {
            // Then
            verify(pseudoTcp).close(true);
            verify(socket, never()).close();
            verify(pseudoTcpSocket).joinAllThreads();
            assert(e.getMessage().equals("Closing socket interrupted"));
        }
    }
}
