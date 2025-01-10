
package org.ice4j.message;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.ice4j.attribute.Attribute;
import org.ice4j.attribute.AttributeFactory;
import org.ice4j.message.Message.MessageType;

public class MessageFactory_createGoogleAllocateRequestTest {

    @Test
    public void testCreateGoogleAllocateRequest() {
        // Given
        String username = "testUser";

        // When
        Request allocateRequest = MessageFactory.createGoogleAllocateRequest(username);

        // Then
        assertEquals(MessageType.ALLOCATE_REQUEST, allocateRequest.getMessageType());

        Attribute magicCookieAttr = allocateRequest.getAttribute(Attribute.Type.MAGIC_COOKIE);
        assertNotNull(magicCookieAttr);

        Attribute usernameAttr = allocateRequest.getAttribute(Attribute.Type.USERNAME);
        assertNotNull(usernameAttr);
        assertEquals(username, usernameAttr.getValue());
    }
}
