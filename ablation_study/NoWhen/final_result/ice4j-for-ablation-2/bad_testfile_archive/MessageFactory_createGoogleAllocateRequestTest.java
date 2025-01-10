
package org.ice4j.message;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.ice4j.attribute.Attribute;
import org.ice4j.attribute.AttributeFactory;
import org.ice4j.message.Message;

public class MessageFactory_createGoogleAllocateRequestTest {

    @Test
    public void testCreateGoogleAllocateRequest() {
        // Given
        String username = "testUser";

        // When
        Request result = MessageFactory.createGoogleAllocateRequest(username);

        // Then
        assertEquals(Message.ALLOCATE_REQUEST, result.getMessageType());

        Attribute magicCookieAttr = result.getAttribute(Attribute.MAGIC_COOKIE);
        assertNotNull(magicCookieAttr);

        Attribute usernameAttr = result.getAttribute(Attribute.USERNAME);
        assertNotNull(usernameAttr);
        assertEquals(username, usernameAttr.getAttributeValue());
    }
}
