
package org.ice4j.message;

import static org.junit.jupiter.api.Assertions.*;
import org.ice4j.*;
import org.ice4j.attribute.*;
import org.junit.jupiter.api.*;

public class MessageFactory_createBindingResponseTest {

    @Test
    public void testCreateBindingResponse() throws Exception {
        Request request = new Request();
        request.setMessageType(Message.BINDING_REQUEST);

        TransportAddress mappedAddress = new TransportAddress(
            MsgFixture.ADDRESS_ATTRIBUTE_ADDRESS,
            MsgFixture.ADDRESS_ATTRIBUTE_PORT,
            Transport.UDP
        );

        Response expectedResponse = new Response();
        expectedResponse.setMessageType(Message.BINDING_SUCCESS_RESPONSE);

        XorMappedAddressAttribute xorMappedAddressAttribute = AttributeFactory
            .createXorMappedAddressAttribute(mappedAddress, request.getTransactionID());

        expectedResponse.putAttribute(xorMappedAddressAttribute);

        Response actualResponse = MessageFactory.createBindingResponse(request, mappedAddress);

        assertEquals(expectedResponse, actualResponse);
    }
}
