
package org.ice4j.message;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.ice4j.attribute.LifetimeAttribute;

public class MessageFactory_createRefreshResponseTest {

    @Test
    public void testCreateRefreshResponse_Success() {
        int lifetime = 3600;
        Response response = MessageFactory.createRefreshResponse(lifetime);

        assertEquals(Message.REFRESH_RESPONSE, response.getMessageType());
        LifetimeAttribute lifetimeAttribute = (LifetimeAttribute) response.getAttribute(LifetimeAttribute.LIFETIME);
        assertNotNull(lifetimeAttribute);
        assertEquals(lifetime, lifetimeAttribute.getLifetime());
    }

    @Test
    public void testCreateRefreshResponse_IllegalArgumentException() {
        int invalidLifetime = -1; // Assuming -1 is an invalid lifetime
        Response response = MessageFactory.createRefreshResponse(invalidLifetime);

        assertEquals(Message.REFRESH_RESPONSE, response.getMessageType());
        LifetimeAttribute lifetimeAttribute = (LifetimeAttribute) response.getAttribute(LifetimeAttribute.LIFETIME);
        assertNull(lifetimeAttribute);
    }
}
