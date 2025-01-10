
package org.ice4j.message;

import org.ice4j.Transport;
import org.ice4j.TransportAddress;
import org.ice4j.attribute.Attribute;
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
        TransportAddress peerAddress = new TransportAddress("127.0.0.1", 3478, Transport.UDP);
        byte[] transactionId = new byte[16]; // Assuming a 16-byte transaction ID

        // When
        Indication connectionAttemptIndication = MessageFactory.createConnectionAttemptIndication(connectionIdValue, peerAddress, transactionId);

        // Then
        assertEquals(Message.CONNECTION_ATTEMPT_INDICATION, connectionAttemptIndication.getMessageType());

        ConnectionIdAttribute connectionIdAttribute = (ConnectionIdAttribute) connectionAttemptIndication.getAttribute(Attribute.Type.CONNECTION_ID);
        assertNotNull(connectionIdAttribute);
        assertEquals(connectionIdValue, connectionIdAttribute.getConnectionIdValue());

        XorPeerAddressAttribute xorPeerAddressAttribute = (XorPeerAddressAttribute) connectionAttemptIndication.getAttribute(Attribute.Type.XOR_PEER_ADDRESS);
        assertNotNull(xorPeerAddressAttribute);
        assertEquals(peerAddress, xorPeerAddressAttribute.getAddress());
    }
}
