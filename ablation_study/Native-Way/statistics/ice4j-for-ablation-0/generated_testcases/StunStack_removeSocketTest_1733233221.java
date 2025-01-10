
package org.ice4j.stack;

import org.ice4j.Transport;
import org.ice4j.TransportAddress;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

public class StunStack_removeSocketTest {

    @Test
    public void testRemoveSocket() {
        // Given
        StunStack stunStack = new StunStack();
        NetAccessManager netAccessManager = mock(NetAccessManager.class);
        stunStack.netAccessManager = netAccessManager;
        TransportAddress localAddr = new TransportAddress("127.0.0.1", 3478, Transport.UDP);
        TransportAddress remoteAddr = new TransportAddress("127.0.0.1", 3479, Transport.UDP);

        // When
        stunStack.removeSocket(localAddr, remoteAddr);

        // Then
        verify(netAccessManager).removeSocket(localAddr, remoteAddr);
    }
}
