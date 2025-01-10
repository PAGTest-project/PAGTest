
package org.ice4j.message;

import static org.junit.jupiter.api.Assertions.*;
import org.ice4j.*;
import org.ice4j.attribute.*;
import org.junit.jupiter.api.*;

public class MessageFactory_create3489BindingResponseTest {

    @Test
    public void testCreate3489BindingResponseWithAllAddresses() {
        TransportAddress mappedAddress = new TransportAddress("192.168.1.1", 1234, Transport.UDP);
        TransportAddress sourceAddress = new TransportAddress("192.168.1.2", 5678, Transport.UDP);
        TransportAddress changedAddress = new TransportAddress("192.168.1.3", 9012, Transport.UDP);

        Response expectedResponse = new Response();
        expectedResponse.setMessageType(Message.BINDING_SUCCESS_RESPONSE);
        expectedResponse.putAttribute(AttributeFactory.createMappedAddressAttribute(mappedAddress));
        expectedResponse.putAttribute(AttributeFactory.createSourceAddressAttribute(sourceAddress));
        expectedResponse.putAttribute(AttributeFactory.createChangedAddressAttribute(changedAddress));

        Response actualResponse = MessageFactory.create3489BindingResponse(mappedAddress, sourceAddress, changedAddress);
        assertEquals(expectedResponse.getMessageType(), actualResponse.getMessageType());
        assertEquals(expectedResponse.getAttribute(Attribute.MAPPED_ADDRESS), actualResponse.getAttribute(Attribute.MAPPED_ADDRESS));
        assertEquals(expectedResponse.getAttribute(Attribute.SOURCE_ADDRESS), actualResponse.getAttribute(Attribute.SOURCE_ADDRESS));
        assertEquals(expectedResponse.getAttribute(Attribute.CHANGED_ADDRESS), actualResponse.getAttribute(Attribute.CHANGED_ADDRESS));
    }

    @Test
    public void testCreate3489BindingResponseWithNullSourceAddress() {
        TransportAddress mappedAddress = new TransportAddress("192.168.1.1", 1234, Transport.UDP);
        TransportAddress changedAddress = new TransportAddress("192.168.1.3", 9012, Transport.UDP);

        Response expectedResponse = new Response();
        expectedResponse.setMessageType(Message.BINDING_SUCCESS_RESPONSE);
        expectedResponse.putAttribute(AttributeFactory.createMappedAddressAttribute(mappedAddress));
        expectedResponse.putAttribute(AttributeFactory.createChangedAddressAttribute(changedAddress));

        Response actualResponse = MessageFactory.create3489BindingResponse(mappedAddress, null, changedAddress);
        assertEquals(expectedResponse.getMessageType(), actualResponse.getMessageType());
        assertEquals(expectedResponse.getAttribute(Attribute.MAPPED_ADDRESS), actualResponse.getAttribute(Attribute.MAPPED_ADDRESS));
        assertNull(actualResponse.getAttribute(Attribute.SOURCE_ADDRESS));
        assertEquals(expectedResponse.getAttribute(Attribute.CHANGED_ADDRESS), actualResponse.getAttribute(Attribute.CHANGED_ADDRESS));
    }

    @Test
    public void testCreate3489BindingResponseWithNullChangedAddress() {
        TransportAddress mappedAddress = new TransportAddress("192.168.1.1", 1234, Transport.UDP);
        TransportAddress sourceAddress = new TransportAddress("192.168.1.2", 5678, Transport.UDP);

        Response expectedResponse = new Response();
        expectedResponse.setMessageType(Message.BINDING_SUCCESS_RESPONSE);
        expectedResponse.putAttribute(AttributeFactory.createMappedAddressAttribute(mappedAddress));
        expectedResponse.putAttribute(AttributeFactory.createSourceAddressAttribute(sourceAddress));

        Response actualResponse = MessageFactory.create3489BindingResponse(mappedAddress, sourceAddress, null);
        assertEquals(expectedResponse.getMessageType(), actualResponse.getMessageType());
        assertEquals(expectedResponse.getAttribute(Attribute.MAPPED_ADDRESS), actualResponse.getAttribute(Attribute.MAPPED_ADDRESS));
        assertEquals(expectedResponse.getAttribute(Attribute.SOURCE_ADDRESS), actualResponse.getAttribute(Attribute.SOURCE_ADDRESS));
        assertNull(actualResponse.getAttribute(Attribute.CHANGED_ADDRESS));
    }

    @Test
    public void testCreate3489BindingResponseWithNullSourceAndChangedAddress() {
        TransportAddress mappedAddress = new TransportAddress("192.168.1.1", 1234, Transport.UDP);

        Response expectedResponse = new Response();
        expectedResponse.setMessageType(Message.BINDING_SUCCESS_RESPONSE);
        expectedResponse.putAttribute(AttributeFactory.createMappedAddressAttribute(mappedAddress));

        Response actualResponse = MessageFactory.create3489BindingResponse(mappedAddress, null, null);
        assertEquals(expectedResponse.getMessageType(), actualResponse.getMessageType());
        assertEquals(expectedResponse.getAttribute(Attribute.MAPPED_ADDRESS), actualResponse.getAttribute(Attribute.MAPPED_ADDRESS));
        assertNull(actualResponse.getAttribute(Attribute.SOURCE_ADDRESS));
        assertNull(actualResponse.getAttribute(Attribute.CHANGED_ADDRESS));
    }
}
