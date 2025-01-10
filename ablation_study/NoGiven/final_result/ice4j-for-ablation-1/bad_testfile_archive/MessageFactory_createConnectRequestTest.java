
package org.ice4j.message;

import static org.junit.jupiter.api.Assertions.*;
import org.ice4j.*;
import org.ice4j.attribute.*;
import org.junit.jupiter.api.*;

public class MessageFactory_createConnectRequestTest {

    @Test
    public void testCreateConnectRequest() {
        TransportAddress peerAddress = new TransportAddress("127.0.0.1", 3478, Transport.UDP);
        byte[] transactionId = new byte[16];
        Request expectedRequest = new Request();
        expectedRequest.setMessageType(Message.CONNECT_REQUEST);
        XorPeerAddressAttribute xorPeerAddressAttribute = AttributeFactory.createXorPeerAddressAttribute(peerAddress, transactionId);
        expectedRequest.putAttribute(xorPeerAddressAttribute);

        Request actualRequest = MessageFactory.createConnectRequest(peerAddress, transactionId);
        assertEquals(expectedRequest.getMessageType(), actualRequest.getMessageType());
        assertEquals(expectedRequest.getAttribute(Attribute.Type.XOR_PEER_ADDRESS), actualRequest.getAttribute(Attribute.Type.XOR_PEER_ADDRESS));
    }

    @Test
    public void testCreateConnectRequestWithInvalidTransactionId() {
        TransportAddress peerAddress = new TransportAddress("127.0.0.1", 3478, Transport.UDP);
        byte[] invalidTransactionId = new byte[15]; // Invalid length
        assertThrows(IllegalArgumentException.class, () -> {
            MessageFactory.createConnectRequest(peerAddress, invalidTransactionId);
        });
    }
}
