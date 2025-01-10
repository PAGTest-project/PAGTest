
package org.ice4j.message;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.ice4j.attribute.AttributeFactory;
import org.ice4j.attribute.ConnectionIdAttribute;
import org.ice4j.message.MessageFactory;
import org.ice4j.message.Response;

public class MessageFactory_createConnectResponseTest {

    @Test
    public void testCreateConnectResponse() {
        int connectionIdValue = 12345;
        Response response = MessageFactory.createConnectResponse(connectionIdValue);

        assertEquals(Message.CONNECT_RESPONSE, response.getMessageType());

        ConnectionIdAttribute connectionIdAttribute = (ConnectionIdAttribute) response.getAttribute(Attribute.CONNECTION_ID);
        assertNotNull(connectionIdAttribute);
        assertEquals(connectionIdValue, connectionIdAttribute.getConnectionId());
    }
}
