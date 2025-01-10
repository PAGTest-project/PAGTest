
package org.ice4j.stunclient;

import org.ice4j.*;
import org.ice4j.stack.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class NetworkConfigurationDiscoveryProcess_startTest {

    private NetworkConfigurationDiscoveryProcess discoverer;
    private StunStack stunStack;
    private TransportAddress localAddress;
    private TransportAddress serverAddress;

    @BeforeEach
    public void setUp() throws Exception {
        stunStack = new StunStack();
        localAddress = new TransportAddress("127.0.0.1", 5678, Transport.UDP);
        serverAddress = new TransportAddress("stun01bak.sipphone.com", 3479, Transport.UDP);
        discoverer = new NetworkConfigurationDiscoveryProcess(stunStack, localAddress, serverAddress);
    }

    @Test
    public void testStartSuccess() throws Exception {
        discoverer.start();
        assertTrue(discoverer.started);
    }

    @Test
    public void testStartFailure() {
        assertThrows(IOException.class, () -> {
            discoverer.start();
        });
    }

    @Test
    public void testStartAfterShutdown() throws Exception {
        discoverer.start();
        discoverer.shutDown();
        assertThrows(IOException.class, () -> {
            discoverer.start();
        });
    }
}
