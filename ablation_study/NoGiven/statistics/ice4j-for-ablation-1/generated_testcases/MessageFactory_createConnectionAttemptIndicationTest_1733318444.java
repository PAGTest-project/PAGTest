
package org.ice4j.message;

import org.ice4j.TransportAddress;
import org.ice4j.attribute.AttributeFactory;
import org.ice4j.attribute.ConnectionIdAttribute;
import org.ice4j.attribute.XorPeerAddressAttribute;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageFactory_createConnectionAttemptIndicationTest {

    @Test
    public void testCreateConnectionAttemptIndication() {
        // Given
        int connectionIdValue = 12345;
        TransportAddress peerAddress = new TransportAddress("127.0.0.1", 3478, TransportAddress.TransportProtocol.UDP);

        // When
        Indication connectionAttemptIndication = MessageFactory.createConnectionAttemptIndication(connectionIdValue, peerAddress);

        // Then
        assertEquals(Message.CONNECTION_ATTEMPT_INDICATION, connectionAttemptIndication.getMessageType());

        ConnectionIdAttribute connectionIdAttribute = (ConnectionIdAttribute) connectionAttemptIndication.getAttribute(ConnectionIdAttribute.class);
        assertNotNull(connectionIdAttribute);
        assertEquals(connectionIdValue, connectionIdAttribute.getConnectionId());

        XorPeerAddressAttribute xorPeerAddressAttribute = (XorPeerAddressAttribute) connectionAttemptIndication.getAttribute(XorPeerAddressAttribute.class);
        assertNotNull(xorPeerAddressAttribute);
        assertEquals(peerAddress, xorPeerAddressAttribute.getAddress());
    }
}
