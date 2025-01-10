
package org.ice4j.message;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageFactory_createBindingRequestTest {

    @Test
    public void testCreateBindingRequest() {
        Request bindingRequest = MessageFactory.createBindingRequest();
        assertEquals(Message.BINDING_REQUEST, bindingRequest.getMessageType());
    }
}
