
package org.ice4j.message;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.ice4j.attribute.AttributeFactory;
import org.ice4j.attribute.ConnectionIdAttribute;
import org.ice4j.message.Message.MessageType;

public class MessageFactory_createConnectionBindRequestTest {

    @Test
    public void testCreateConnectionBindRequest() {
        int connectionIdValue = 12345;
        Request connectionBindRequest = MessageFactory.createConnectionBindRequest(connectionIdValue);

        assertEquals(MessageType.CONNECTION_BIND_REQUEST, connectionBindRequest.getMessageType());

        ConnectionIdAttribute connectionIdAttribute = (ConnectionIdAttribute) connectionBindRequest.getAttribute(ConnectionIdAttribute.class);
        assertNotNull(connectionIdAttribute);
        assertEquals(connectionIdValue, connectionIdAttribute.getConnectionId());
    }
}
