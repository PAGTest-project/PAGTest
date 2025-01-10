
package org.ice4j.message;

import static org.junit.jupiter.api.Assertions.*;
import org.ice4j.*;
import org.ice4j.attribute.*;
import org.junit.jupiter.api.*;

public class MessageFactory_createBindingResponseTest {

    private TransportAddress mappedAddress;
    private Request request;

    @BeforeEach
    public void setUp() {
        mappedAddress = new TransportAddress("127.0.0.1", 3478, Transport.UDP);
        request = MessageFactory.createBindingRequest();
    }

    @Test
    public void testCreateBindingResponse() {
        Response expectedResponse = new Response();
        expectedResponse.setMessageType(Message.BINDING_SUCCESS_RESPONSE);
        XorMappedAddressAttribute xorMappedAddressAttribute = AttributeFactory
                .createXorMappedAddressAttribute(mappedAddress, request.getTransactionID());
        expectedResponse.putAttribute(xorMappedAddressAttribute);

        Response actualResponse = MessageFactory.createBindingResponse(request, mappedAddress);

        assertEquals(expectedResponse.getMessageType(), actualResponse.getMessageType());
        assertEquals(expectedResponse.getAttribute(Attribute.XOR_MAPPED_ADDRESS), actualResponse.getAttribute(Attribute.XOR_MAPPED_ADDRESS));
    }

    @Test
    public void testCreateBindingResponseWithNullRequest() {
        assertThrows(NullPointerException.class, () -> {
            MessageFactory.createBindingResponse(null, mappedAddress);
        });
    }

    @Test
    public void testCreateBindingResponseWithNullMappedAddress() {
        assertThrows(NullPointerException.class, () -> {
            MessageFactory.createBindingResponse(request, null);
        });
    }
}
