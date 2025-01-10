
package org.ice4j.message;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.ice4j.message.MessageFactory;
import org.ice4j.message.Request;
import org.ice4j.message.Message;

public class MessageFactory_createBindingRequestTest {

    @BeforeEach
    public void setUp() {
        // No setup required for this test case
    }

    @Test
    public void testCreateBindingRequest() {
        Request bindingRequest = MessageFactory.createBindingRequest();
        assertEquals(Message.BINDING_REQUEST, bindingRequest.getMessageType());
    }
}
