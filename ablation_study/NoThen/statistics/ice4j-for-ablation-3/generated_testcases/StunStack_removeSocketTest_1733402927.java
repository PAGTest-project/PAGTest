
package org.ice4j.stack;

import org.ice4j.TransportAddress;
import org.ice4j.socket.IceSocketWrapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class StunStack_removeSocketTest {

    private StunStack stunStack;
    private NetAccessManager netAccessManager;
    private TransportAddress localAddr;
    private TransportAddress remoteAddr;

    @BeforeEach
    public void setUp() {
        stunStack = new StunStack();
        netAccessManager = mock(NetAccessManager.class);
        stunStack.netAccessManager = netAccessManager;
        localAddr = new TransportAddress("127.0.0.1", 12345, TransportAddress.Transport.UDP);
        remoteAddr = new TransportAddress("127.0.0.1", 54321, TransportAddress.Transport.UDP);
    }

    @Test
    public void testRemoveSocket() {
        // Given
        IceSocketWrapper socket = mock(IceSocketWrapper.class);
        stunStack.addSocket(socket);

        // When
        stunStack.removeSocket(localAddr, remoteAddr);

        // Then
        verify(netAccessManager).removeSocket(localAddr, remoteAddr);
    }
}
