
package org.ice4j.message;

import static org.junit.jupiter.api.Assertions.*;
import org.ice4j.*;
import org.ice4j.attribute.*;
import org.junit.jupiter.api.*;

public class MessageFactory_createBindingRequestTest {

    @Test
    public void testCreateBindingRequest() {
        Request bindingRequest = MessageFactory.createBindingRequest();
        assertEquals(Message.BINDING_REQUEST, bindingRequest.getMessageType());
    }

    @Test
    public void testCreateBindingRequestWithPriority() throws StunException {
        long priority = 123456789L;
        Request bindingRequest = MessageFactory.createBindingRequest(priority);
        assertEquals(Message.BINDING_REQUEST, bindingRequest.getMessageType());
        PriorityAttribute priorityAttribute = (PriorityAttribute) bindingRequest.getAttribute(Attribute.PRIORITY);
        assertNotNull(priorityAttribute);
        assertEquals(priority, priorityAttribute.getPriority());
    }

    @Test
    public void testCreateBindingRequestWithPriorityAndControlling() throws StunException {
        long priority = 123456789L;
        boolean controlling = true;
        long tieBreaker = 987654321L;
        Request bindingRequest = MessageFactory.createBindingRequest(priority, controlling, tieBreaker);
        assertEquals(Message.BINDING_REQUEST, bindingRequest.getMessageType());
        PriorityAttribute priorityAttribute = (PriorityAttribute) bindingRequest.getAttribute(Attribute.PRIORITY);
        IceControllingAttribute iceControllingAttribute = (IceControllingAttribute) bindingRequest.getAttribute(Attribute.ICE_CONTROLLING);
        assertNotNull(priorityAttribute);
        assertNotNull(iceControllingAttribute);
        assertEquals(priority, priorityAttribute.getPriority());
        assertEquals(tieBreaker, iceControllingAttribute.getTieBreaker());
    }

    @Test
    public void testCreateBindingRequestWithPriorityAndControlled() throws StunException {
        long priority = 123456789L;
        boolean controlling = false;
        long tieBreaker = 987654321L;
        Request bindingRequest = MessageFactory.createBindingRequest(priority, controlling, tieBreaker);
        assertEquals(Message.BINDING_REQUEST, bindingRequest.getMessageType());
        PriorityAttribute priorityAttribute = (PriorityAttribute) bindingRequest.getAttribute(Attribute.PRIORITY);
        IceControlledAttribute iceControlledAttribute = (IceControlledAttribute) bindingRequest.getAttribute(Attribute.ICE_CONTROLLED);
        assertNotNull(priorityAttribute);
        assertNotNull(iceControlledAttribute);
        assertEquals(priority, priorityAttribute.getPriority());
        assertEquals(tieBreaker, iceControlledAttribute.getTieBreaker());
    }
}
