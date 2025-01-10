
package org.ice4j.stunclient;

import org.junit.jupiter.api.Test;
import org.ice4j.TransportAddress;
import org.ice4j.stack.StunStack;
import org.ice4j.socket.IceUdpSocketWrapper;
import org.ice4j.socket.SafeCloseDatagramSocket;
import org.ice4j.stack.BlockingRequestSender;
import java.io.IOException;
import org.ice4j.StunException;
import static org.mockito.Mockito.*;

public class NetworkConfigurationDiscoveryProcess_startTest {

    @Test
    public void testStart() throws IOException, StunException {
        // Given
        StunStack mockStunStack = mock(StunStack.class);
        TransportAddress mockLocalAddress = mock(TransportAddress.class);
        TransportAddress mockServerAddress = mock(TransportAddress.class);
        NetworkConfigurationDiscoveryProcess process = new NetworkConfigurationDiscoveryProcess(mockStunStack, mockLocalAddress, mockServerAddress);

        // When
        process.start();

        // Then
        verify(mockStunStack).addSocket(any(IceUdpSocketWrapper.class));
        assert process.getRequestSender() != null;
        assert process.isStarted();
    }
}
