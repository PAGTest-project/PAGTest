
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
        sock = discoverer.sock;
    }

    @Test
    public void testShutDown() {
        discoverer.shutDown();

        assertNull(discoverer.sock, "Socket should be null after shutdown");
        assertNull(discoverer.localAddress, "Local address should be null after shutdown");
        assertNull(discoverer.requestSender, "Request sender should be null after shutdown");
        assertFalse(discoverer.started, "Started flag should be false after shutdown");
    }

    @Test
    public void testShutDownWithNullSocket() {
        discoverer.sock = null;
        discoverer.shutDown();

        assertNull(discoverer.sock, "Socket should remain null after shutdown");
        assertNull(discoverer.localAddress, "Local address should be null after shutdown");
        assertNull(discoverer.requestSender, "Request sender should be null after shutdown");
        assertFalse(discoverer.started, "Started flag should be false after shutdown");
    }

    @Test
    public void testShutDownWithNullLocalAddress() {
        discoverer.localAddress = null;
        discoverer.shutDown();

        assertNull(discoverer.sock, "Socket should be null after shutdown");
        assertNull(discoverer.localAddress, "Local address should remain null after shutdown");
        assertNull(discoverer.requestSender, "Request sender should be null after shutdown");
        assertFalse(discoverer.started, "Started flag should be false after shutdown");
    }
}
