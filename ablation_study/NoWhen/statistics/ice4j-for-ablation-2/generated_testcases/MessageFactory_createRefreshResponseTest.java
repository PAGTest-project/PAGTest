
package org.ice4j.message;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.ice4j.attribute.LifetimeAttribute;
import org.ice4j.attribute.AttributeFactory;

public class MessageFactory_createRefreshResponseTest {

    @Test
    public void testCreateRefreshResponse_Success() {
        // Given
        int lifetime = 3600;

        // When
        Response response = MessageFactory.createRefreshResponse(lifetime);

        // Then
        assertEquals(Message.REFRESH_RESPONSE, response.getMessageType());
        LifetimeAttribute lifetimeAttribute = (LifetimeAttribute) response.getAttribute(LifetimeAttribute.LIFETIME);
        assertNotNull(lifetimeAttribute);
        assertEquals(lifetime, lifetimeAttribute.getLifetime());
    }

    @Test
    public void testCreateRefreshResponse_IllegalArgumentException() {
        // Given
        int invalidLifetime = -1; // An invalid lifetime to trigger IllegalArgumentException

        // When
        Response response = MessageFactory.createRefreshResponse(invalidLifetime);

        // Then
        assertEquals(Message.REFRESH_RESPONSE, response.getMessageType());
        assertNull(response.getAttribute(LifetimeAttribute.LIFETIME));
    }
}
