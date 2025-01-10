
package org.ice4j.message;

import static org.junit.jupiter.api.Assertions.*;
import org.ice4j.*;
import org.ice4j.attribute.*;
import org.junit.jupiter.api.*;

public class MessageFactory_createBindingResponseTest {

    @Test
    public void testCreateBindingResponse() throws Exception {
        Request request = MessageFactory.createBindingRequest();
        TransportAddress mappedAddress = new TransportAddress("127.0.0.1", 12345, Transport.UDP);

        Response expectedResponse = new Response();
        expectedResponse.setMessageType(Message.BINDING_SUCCESS_RESPONSE);
        XorMappedAddressAttribute xorMappedAddressAttribute = AttributeFactory.createXorMappedAddressAttribute(mappedAddress, request.getTransactionID());
        expectedResponse.putAttribute(xorMappedAddressAttribute);

        Response actualResponse = MessageFactory.createBindingResponse(request, mappedAddress);

        assertEquals(expectedResponse.getMessageType(), actualResponse.getMessageType());
        assertEquals(expectedResponse.getAttribute(Attribute.Type.XOR_MAPPED_ADDRESS), actualResponse.getAttribute(Attribute.Type.XOR_MAPPED_ADDRESS));
    }

    @Test
    public void testCreateBindingResponseWithInvalidRequest() {
        Request request = new Request();
        TransportAddress mappedAddress = new TransportAddress("127.0.0.1", 12345, Transport.UDP);

        assertThrows(IllegalArgumentException.class, () -> {
            MessageFactory.createBindingResponse(request, mappedAddress);
        });
    }
}
