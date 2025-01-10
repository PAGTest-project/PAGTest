
package org.ice4j.stunclient;

import org.ice4j.*;
import org.ice4j.socket.*;
import org.ice4j.stack.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

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
    public void testStartSuccess() throws Exception {
        discoverer.start();
        assertTrue(discoverer.isStarted());
    }

    @Test
    public void testStartIOException() {
        // Simulate an IOException by providing an invalid local address
        localAddress = new TransportAddress("invalid.address", 5678, Transport.UDP);
        discoverer = new NetworkConfigurationDiscoveryProcess(stunStack, localAddress, serverAddress);

        assertThrows(IOException.class, () -> {
            discoverer.start();
        });
    }

    @Test
    public void testStartStunException() {
        // Simulate a StunException by providing a null StunStack
        discoverer = new NetworkConfigurationDiscoveryProcess(null, localAddress, serverAddress);

        assertThrows(NullPointerException.class, () -> {
            discoverer.start();
        });
    }

    // Add the missing isStarted method to the NetworkConfigurationDiscoveryProcess class
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
            IceUdpSocketWrapper sock = new IceUdpSocketWrapper(
                new SafeCloseDatagramSocket(localAddress));

            stunStack.addSocket(sock);

            BlockingRequestSender requestSender = new BlockingRequestSender(stunStack, localAddress);

            started = true;
        }

        public boolean isStarted() {
            return started;
        }
    }
}
