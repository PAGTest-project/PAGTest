
package org.ice4j.message;

import static org.junit.jupiter.api.Assertions.*;
import org.ice4j.*;
import org.ice4j.attribute.*;
import org.junit.jupiter.api.*;

public class MessageFactory_createRefreshResponseTest {

    @Test
    public void testCreateRefreshResponse_Success() {
        int lifetime = 3600;

        Response expectedResponse = new Response();
        expectedResponse.setMessageType(Message.REFRESH_RESPONSE);

        LifetimeAttribute lifetimeAttribute = AttributeFactory.createLifetimeAttribute(lifetime);
        expectedResponse.putAttribute(lifetimeAttribute);

        Response actualResponse = MessageFactory.createRefreshResponse(lifetime);
        assertEquals(expectedResponse.getMessageType(), actualResponse.getMessageType());
        assertEquals(expectedResponse.getAttribute(Attribute.LIFETIME), actualResponse.getAttribute(Attribute.LIFETIME));
    }

    @Test
    public void testCreateRefreshResponse_IllegalArgumentException() {
        int lifetime = -1; // Invalid lifetime to trigger IllegalArgumentException

        Response actualResponse = MessageFactory.createRefreshResponse(lifetime);
        assertNull(actualResponse.getAttribute(Attribute.LIFETIME));
    }
}
