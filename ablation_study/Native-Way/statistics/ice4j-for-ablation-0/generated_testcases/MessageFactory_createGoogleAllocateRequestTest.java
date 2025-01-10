
package org.ice4j.message;

import static org.junit.jupiter.api.Assertions.*;
import org.ice4j.*;
import org.ice4j.attribute.*;
import org.junit.jupiter.api.*;

public class MessageFactory_createGoogleAllocateRequestTest {

    @Test
    public void testCreateGoogleAllocateRequest() {
        String username = "testUser";
        Request expectedRequest = new Request();
        expectedRequest.setMessageType(Message.ALLOCATE_REQUEST);
        expectedRequest.putAttribute(AttributeFactory.createMagicCookieAttribute());
        expectedRequest.putAttribute(AttributeFactory.createUsernameAttribute(username));

        Request actualRequest = MessageFactory.createGoogleAllocateRequest(username);
        assertEquals(expectedRequest.getMessageType(), actualRequest.getMessageType());
        assertEquals(expectedRequest.getAttributes().size(), actualRequest.getAttributes().size());
        assertTrue(actualRequest.getAttributes().contains(AttributeFactory.createMagicCookieAttribute()));
        assertTrue(actualRequest.getAttributes().contains(AttributeFactory.createUsernameAttribute(username)));
    }
}
