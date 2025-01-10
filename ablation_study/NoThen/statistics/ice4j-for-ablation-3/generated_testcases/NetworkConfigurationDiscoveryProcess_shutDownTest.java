
package org.ice4j.stunclient;

import static org.junit.jupiter.api.Assertions.*;
import org.ice4j.*;
import org.ice4j.message.*;
import org.ice4j.stack.*;
import org.junit.jupiter.api.*;

public class NetworkConfigurationDiscoveryProcess_shutDownTest {
    private NetworkConfigurationDiscoveryProcess stunAddressDiscoverer;
    private ResponseSequenceServer responseServer;
    private TransportAddress discovererAddress;
    private TransportAddress responseServerAddress;

    @BeforeEach
    public void setUp() throws Exception {
        StunStack stunStack = new StunStack();

        responseServerAddress = new TransportAddress("127.0.0.1", 3478, Transport.UDP);
        discovererAddress = new TransportAddress("127.0.0.1", 5678, Transport.UDP);

        responseServer = new ResponseSequenceServer(stunStack, responseServerAddress);
        stunAddressDiscoverer = new NetworkConfigurationDiscoveryProcess(
                stunStack,
                discovererAddress, responseServerAddress);

        stunAddressDiscoverer.start();
        responseServer.start();

        System.setProperty(StackProperties.MAX_CTRAN_RETRANS_TIMER, "100");
        System.setProperty(StackProperties.MAX_CTRAN_RETRANSMISSIONS, "2");
    }

    @Test
    public void testShutDown() throws Exception {
        // Given
        Response testIResponse1 = MessageFactory.create3489BindingResponse(
                discovererAddress, responseServerAddress, responseServerAddress);
        responseServer.addMessage(testIResponse1);

        // When
        StunDiscoveryReport report = stunAddressDiscoverer.determineAddress();
        assertNotNull(report);

        // Then
        stunAddressDiscoverer.shutDown();
        assertNull(stunAddressDiscoverer.getLocalAddress());
        assertNull(stunAddressDiscoverer.getRequestSender());
        assertFalse(stunAddressDiscoverer.isStarted());
    }
}
