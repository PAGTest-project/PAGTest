
package org.ice4j.message;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.ice4j.*;

public class MessageFactory_createAllocationResponseTest {

    @Test
    public void testCreateAllocationResponse() {
        Request request = new Request();
        request.setTransactionID(new byte[16]); // Set a valid transaction ID
        TransportAddress mappedAddress = new TransportAddress("127.0.0.1", 12345, Transport.UDP);
        TransportAddress relayedAddress = new TransportAddress("127.0.0.1", 54321, Transport.UDP);
        int lifetime = 3600;

        Response response = MessageFactory.createAllocationResponse(request, mappedAddress, relayedAddress, lifetime);

        assertNotNull(response);
        assertEquals(Message.ALLOCATE_RESPONSE, response.getMessageType());
    }
}
