
package org.ice4j.message;

import org.junit.jupiter.api.Test;
import org.ice4j.TransportAddress;
import org.ice4j.attribute.Attribute;
import org.ice4j.attribute.AttributeFactory;
import org.ice4j.attribute.ChangedAddressAttribute;
import org.ice4j.attribute.MappedAddressAttribute;
import org.ice4j.attribute.SourceAddressAttribute;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MessageFactory_create3489BindingResponseTest {

    @Test
    public void testCreate3489BindingResponse_AllAddresses() {
        // Given
        TransportAddress mappedAddress = new TransportAddress("192.168.1.1", 12345);
        TransportAddress sourceAddress = new TransportAddress("192.168.1.2", 12346);
        TransportAddress changedAddress = new TransportAddress("192.168.1.3", 12347);

        // When
        Response response = MessageFactory.create3489BindingResponse(mappedAddress, sourceAddress, changedAddress);

        // Then
        assertNotNull(response);
        assertEquals(Message.BINDING_SUCCESS_RESPONSE, response.getMessageType());

        MappedAddressAttribute mappedAddressAttribute = (MappedAddressAttribute) response.getAttribute(Attribute.Type.MAPPED_ADDRESS);
        assertNotNull(mappedAddressAttribute);
        assertEquals(mappedAddress, mappedAddressAttribute.getAddress());

        SourceAddressAttribute sourceAddressAttribute = (SourceAddressAttribute) response.getAttribute(Attribute.Type.SOURCE_ADDRESS);
        assertNotNull(sourceAddressAttribute);
        assertEquals(sourceAddress, sourceAddressAttribute.getAddress());

        ChangedAddressAttribute changedAddressAttribute = (ChangedAddressAttribute) response.getAttribute(Attribute.Type.CHANGED_ADDRESS);
        assertNotNull(changedAddressAttribute);
        assertEquals(changedAddress, changedAddressAttribute.getAddress());
    }

    @Test
    public void testCreate3489BindingResponse_NoSourceAndChangedAddresses() {
        // Given
        TransportAddress mappedAddress = new TransportAddress("192.168.1.1", 12345);
        TransportAddress sourceAddress = null;
        TransportAddress changedAddress = null;

        // When
        Response response = MessageFactory.create3489BindingResponse(mappedAddress, sourceAddress, changedAddress);

        // Then
        assertNotNull(response);
        assertEquals(Message.BINDING_SUCCESS_RESPONSE, response.getMessageType());

        MappedAddressAttribute mappedAddressAttribute = (MappedAddressAttribute) response.getAttribute(Attribute.Type.MAPPED_ADDRESS);
        assertNotNull(mappedAddressAttribute);
        assertEquals(mappedAddress, mappedAddressAttribute.getAddress());

        SourceAddressAttribute sourceAddressAttribute = (SourceAddressAttribute) response.getAttribute(Attribute.Type.SOURCE_ADDRESS);
        assertEquals(null, sourceAddressAttribute);

        ChangedAddressAttribute changedAddressAttribute = (ChangedAddressAttribute) response.getAttribute(Attribute.Type.CHANGED_ADDRESS);
        assertEquals(null, changedAddressAttribute);
    }
}
