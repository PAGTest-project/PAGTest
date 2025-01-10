
package org.ice4j.stunclient;

import static org.junit.jupiter.api.Assertions.*;
import org.ice4j.*;
import org.ice4j.stack.*;
import org.junit.jupiter.api.*;
import java.io.IOException;
import org.ice4j.socket.IceUdpSocketWrapper;
import org.ice4j.socket.SafeCloseDatagramSocket;

public class NetworkConfigurationDiscoveryProcess_shutDownTest {

    private NetworkConfigurationDiscoveryProcess discoverer;
    private StunStack stunStack;
    private TransportAddress localAddress;
    private TransportAddress serverAddress;
    private IceSocketWrapper mockSocket;

    @BeforeEach
    public void setUp() throws Exception {
        stunStack = new StunStack();
        localAddress = new TransportAddress("127.0.0.1", 12345, Transport.UDP);
        serverAddress = new TransportAddress("127.0.0.1", 54321, Transport.UDP);
        mockSocket = new IceUdpSocketWrapper(new SafeCloseDatagramSocket(localAddress));

        discoverer = new NetworkConfigurationDiscoveryProcess(stunStack, localAddress, serverAddress);
        discoverer.start();
    }

    @Test
    public void testShutDown() throws IOException {
        // Given
        assertNotNull(discoverer.getSocket());
        assertNotNull(discoverer.getLocalAddress());
        assertNotNull(discoverer.getRequestSender());
        assertTrue(discoverer.isStarted());

        // When
        discoverer.shutDown();

        // Then
        assertNull(discoverer.getSocket());
        assertNull(discoverer.getLocalAddress());
        assertNull(discoverer.getRequestSender());
        assertFalse(discoverer.isStarted());
    }
}
