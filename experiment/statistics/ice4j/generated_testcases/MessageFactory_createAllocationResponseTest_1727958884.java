
package org.ice4j.message;

import static org.junit.jupiter.api.Assertions.*;
import org.ice4j.*;
import org.ice4j.attribute.*;
import org.junit.jupiter.api.*;

public class MessageFactory_createAllocationResponseTest {

    @Test
    public void testCreateAllocationResponse() {
        Request request = new Request();
        request.setMessageType(Message.ALLOCATE_REQUEST);

        TransportAddress mappedAddress = new TransportAddress("127.0.0.1", 12345, Transport.UDP);
        TransportAddress relayedAddress = new TransportAddress("127.0.0.1", 54321, Transport.UDP);
        int lifetime = 3600;

        Response expectedResponse = new Response();
        expectedResponse.setMessageType(Message.ALLOCATE_RESPONSE);

        XorMappedAddressAttribute xorMappedAddressAttribute = AttributeFactory.createXorMappedAddressAttribute(mappedAddress, request.getTransactionID());
        expectedResponse.putAttribute(xorMappedAddressAttribute);

        XorRelayedAddressAttribute xorRelayedAddressAttribute = AttributeFactory.createXorRelayedAddressAttribute(relayedAddress, request.getTransactionID());
        expectedResponse.putAttribute(xorRelayedAddressAttribute);

        LifetimeAttribute lifetimeAttribute = AttributeFactory.createLifetimeAttribute(lifetime);
        expectedResponse.putAttribute(lifetimeAttribute);

        Response actualResponse = MessageFactory.createAllocationResponse(request, mappedAddress, relayedAddress, null, lifetime);
        assertEquals(expectedResponse, actualResponse);
    }
}
