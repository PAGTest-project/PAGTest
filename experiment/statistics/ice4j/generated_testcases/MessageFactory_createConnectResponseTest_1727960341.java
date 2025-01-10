
package org.ice4j.message;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.ice4j.attribute.AttributeFactory;
import org.ice4j.attribute.ConnectionIdAttribute;

public class MessageFactory_createConnectResponseTest {

    @Test
    public void testCreateConnectResponse() {
        int connectionIdValue = 12345;
        Response connectSuccessResponse = MessageFactory.createConnectResponse(connectionIdValue);

        assertEquals(Message.CONNECT_RESPONSE, connectSuccessResponse.getMessageType());

        ConnectionIdAttribute connectionIdAttribute = (ConnectionIdAttribute) connectSuccessResponse.getAttribute(ConnectionIdAttribute.class);
        assertNotNull(connectionIdAttribute);
        assertEquals(connectionIdValue, connectionIdAttribute.getConnectionId());
    }
}
