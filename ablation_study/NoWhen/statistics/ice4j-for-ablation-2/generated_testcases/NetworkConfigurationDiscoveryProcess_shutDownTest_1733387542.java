
package org.ice4j.stunclient;

import static org.junit.jupiter.api.Assertions.*;
import org.ice4j.*;
import org.ice4j.message.*;
import org.ice4j.stack.*;
import org.junit.jupiter.api.*;

public class NetworkConfigurationDiscoveryProcess_shutDownTest {

    private NetworkConfigurationDiscoveryProcess discoverer;
    private TransportAddress localAddress;
    private TransportAddress serverAddress;
    private StunStack stunStack;
    private IceSocketWrapper sock;

    @BeforeEach
    public void setUp() throws Exception {
        stunStack = new StunStack();
        localAddress = new TransportAddress("127.0.0.1", 5678, Transport.UDP);
        serverAddress = new TransportAddress("127.0.0.1", 3479, Transport.UDP);
        discoverer = new NetworkConfigurationDiscoveryProcess(stunStack, localAddress, serverAddress);
        discoverer.start();
        sock = discoverer.getSock();
    }

    @Test
    public void testShutDown() {
        discoverer.shutDown();

        assertNull(discoverer.getSock(), "Socket should be null after shutdown");
        assertNull(discoverer.getLocalAddress(), "Local address should be null after shutdown");
        assertNull(discoverer.getRequestSender(), "Request sender should be null after shutdown");
        assertFalse(discoverer.isStarted(), "Started flag should be false after shutdown");
    }

    @Test
    public void testShutDownWithNullSocket() {
        discoverer.setSock(null);
        discoverer.shutDown();

        assertNull(discoverer.getSock(), "Socket should remain null after shutdown");
        assertNull(discoverer.getLocalAddress(), "Local address should be null after shutdown");
        assertNull(discoverer.getRequestSender(), "Request sender should be null after shutdown");
        assertFalse(discoverer.isStarted(), "Started flag should be false after shutdown");
    }

    @Test
    public void testShutDownWithNullLocalAddress() {
        discoverer.setLocalAddress(null);
        discoverer.shutDown();

        assertNull(discoverer.getSock(), "Socket should be null after shutdown");
        assertNull(discoverer.getLocalAddress(), "Local address should remain null after shutdown");
        assertNull(discoverer.getRequestSender(), "Request sender should be null after shutdown");
        assertFalse(discoverer.isStarted(), "Started flag should be false after shutdown");
    }
}
