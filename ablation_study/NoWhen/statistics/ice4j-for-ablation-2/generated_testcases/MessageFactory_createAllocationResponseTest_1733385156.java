
package org.ice4j.message;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.ice4j.*;

public class MessageFactory_createAllocationResponseTest {

    @Test
    public void testCreateAllocationResponse() {
        // Given
        Request request = new Request();
        TransportAddress mappedAddress = new TransportAddress("127.0.0.1", 12345, Transport.UDP);
        TransportAddress relayedAddress = new TransportAddress("127.0.0.1", 54321, Transport.UDP);
        int lifetime = 3600;

        // When
        Response response = MessageFactory.createAllocationResponse(request, mappedAddress, relayedAddress, lifetime);

        // Then
        assertNotNull(response);
        assertEquals(Message.ALLOCATE_RESPONSE, response.getMessageType());
        assertTrue(response.getAttributes().containsKey(Attribute.Type.XOR_MAPPED_ADDRESS));
        assertTrue(response.getAttributes().containsKey(Attribute.Type.XOR_RELAYED_ADDRESS));
        assertTrue(response.getAttributes().containsKey(Attribute.Type.LIFETIME));
    }
}
