
package org.ice4j.stunclient;

import static org.junit.jupiter.api.Assertions.*;
import org.ice4j.*;
import org.ice4j.stack.*;
import org.junit.jupiter.api.*;
import java.io.IOException;

public class NetworkConfigurationDiscoveryProcess_startTest {

    private NetworkConfigurationDiscoveryProcess discoverer;
    private StunStack stunStack;
    private TransportAddress localAddress;
    private TransportAddress serverAddress;

    @BeforeEach
    public void setUp() throws Exception {
        stunStack = new StunStack();
        localAddress = new TransportAddress("127.0.0.1", 5678, Transport.UDP);
        serverAddress = new TransportAddress("127.0.0.1", 3479, Transport.UDP);
        discoverer = new NetworkConfigurationDiscoveryProcess(stunStack, localAddress, serverAddress);
    }

    @Test
    public void testStartSuccess() throws IOException, StunException {
        discoverer.start();
        assertTrue(discoverer.isStarted());
    }

    @Test
    public void testStartFailure() {
        assertThrows(IOException.class, () -> {
            discoverer.start();
        });
    }

    @Test
    public void testStartThenShutdown() throws IOException, StunException {
        discoverer.start();
        assertTrue(discoverer.isStarted());
        discoverer.shutDown();
        assertFalse(discoverer.isStarted());
    }

    private static class NetworkConfigurationDiscoveryProcess {
        private boolean started;
        private final StunStack stunStack;
        private final TransportAddress localAddress;
        private final TransportAddress serverAddress;

        public NetworkConfigurationDiscoveryProcess(StunStack stunStack, TransportAddress localAddress, TransportAddress serverAddress) {
            this.stunStack = stunStack;
            this.localAddress = localAddress;
            this.serverAddress = serverAddress;
        }

        public void start() throws IOException, StunException {
            // Simulate starting process
            started = true;
        }

        public boolean isStarted() {
            return started;
        }

        public void shutDown() {
            started = false;
        }
    }
}
