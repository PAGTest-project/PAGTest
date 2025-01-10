
package org.ice4j.message;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.ice4j.attribute.Attribute;

public class MessageFactory_createRefreshRequestTest {

    @Test
    public void testCreateRefreshRequest() {
        // Given
        int lifetime = 3600;

        // When
        Request refreshRequest = MessageFactory.createRefreshRequest(lifetime);

        // Then
        assertEquals(Message.REFRESH_REQUEST, refreshRequest.getMessageType());
        assertNotNull(refreshRequest.getAttribute(Attribute.LIFETIME));
    }

    @Test
    public void testCreateRefreshRequestWithInvalidMessageType() {
        // Given
        int lifetime = 3600;

        // When
        Request refreshRequest = MessageFactory.createRefreshRequest(lifetime);

        // Then
        assertNotEquals(Message.BINDING_REQUEST, refreshRequest.getMessageType());
    }
}
