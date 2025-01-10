
package org.ice4j.message;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.ice4j.attribute.AttributeFactory;
import org.ice4j.attribute.ConnectionIdAttribute;

public class MessageFactory_createConnectResponseTest {

    @Test
    public void testCreateConnectResponse() {
        // Given
        int connectionIdValue = 12345;

        // When
        Response connectSuccessResponse = MessageFactory.createConnectResponse(connectionIdValue);

        // Then
        assertEquals(Message.CONNECT_RESPONSE, connectSuccessResponse.getMessageType());
        ConnectionIdAttribute connectionIdAttribute = (ConnectionIdAttribute) connectSuccessResponse.getAttribute(ConnectionIdAttribute.class);
        assertNotNull(connectionIdAttribute);
        assertEquals(connectionIdValue, connectionIdAttribute.getConnectionIdValue());
    }
}
