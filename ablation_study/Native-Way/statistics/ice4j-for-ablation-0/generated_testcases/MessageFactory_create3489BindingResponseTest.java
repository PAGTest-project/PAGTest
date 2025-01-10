
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

        Response response = MessageFactory.create3489BindingResponse(mappedAddress, sourceAddress, changedAddress);

        assertEquals(Message.BINDING_SUCCESS_RESPONSE, response.getMessageType());
        assertNotNull(response.getAttribute(Attribute.MAPPED_ADDRESS));
        assertNotNull(response.getAttribute(Attribute.SOURCE_ADDRESS));
        assertNotNull(response.getAttribute(Attribute.CHANGED_ADDRESS));
    }

    @Test
    public void testCreate3489BindingResponseWithNullSourceAddress() {
        TransportAddress mappedAddress = new TransportAddress("192.168.1.1", 1234, Transport.UDP);
        TransportAddress sourceAddress = null;
        TransportAddress changedAddress = new TransportAddress("192.168.1.3", 9012, Transport.UDP);

        Response response = MessageFactory.create3489BindingResponse(mappedAddress, sourceAddress, changedAddress);

        assertEquals(Message.BINDING_SUCCESS_RESPONSE, response.getMessageType());
        assertNotNull(response.getAttribute(Attribute.MAPPED_ADDRESS));
        assertNull(response.getAttribute(Attribute.SOURCE_ADDRESS));
        assertNotNull(response.getAttribute(Attribute.CHANGED_ADDRESS));
    }

    @Test
    public void testCreate3489BindingResponseWithNullChangedAddress() {
        TransportAddress mappedAddress = new TransportAddress("192.168.1.1", 1234, Transport.UDP);
        TransportAddress sourceAddress = new TransportAddress("192.168.1.2", 5678, Transport.UDP);
        TransportAddress changedAddress = null;

        Response response = MessageFactory.create3489BindingResponse(mappedAddress, sourceAddress, changedAddress);

        assertEquals(Message.BINDING_SUCCESS_RESPONSE, response.getMessageType());
        assertNotNull(response.getAttribute(Attribute.MAPPED_ADDRESS));
        assertNotNull(response.getAttribute(Attribute.SOURCE_ADDRESS));
        assertNull(response.getAttribute(Attribute.CHANGED_ADDRESS));
    }

    @Test
    public void testCreate3489BindingResponseWithNullSourceAndChangedAddress() {
        TransportAddress mappedAddress = new TransportAddress("192.168.1.1", 1234, Transport.UDP);
        TransportAddress sourceAddress = null;
        TransportAddress changedAddress = null;

        Response response = MessageFactory.create3489BindingResponse(mappedAddress, sourceAddress, changedAddress);

        assertEquals(Message.BINDING_SUCCESS_RESPONSE, response.getMessageType());
        assertNotNull(response.getAttribute(Attribute.MAPPED_ADDRESS));
        assertNull(response.getAttribute(Attribute.SOURCE_ADDRESS));
        assertNull(response.getAttribute(Attribute.CHANGED_ADDRESS));
    }
}
