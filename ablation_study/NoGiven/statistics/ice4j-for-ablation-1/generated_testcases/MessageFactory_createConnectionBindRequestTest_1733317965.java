
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
        Request expectedRequest = new Request();
        expectedRequest.setMessageType(MessageType.CONNECTION_BIND_REQUEST);
        ConnectionIdAttribute expectedAttribute = AttributeFactory.createConnectionIdAttribute(connectionIdValue);
        expectedRequest.putAttribute(expectedAttribute);

        Request actualRequest = MessageFactory.createConnectionBindRequest(connectionIdValue);

        assertEquals(expectedRequest.getMessageType(), actualRequest.getMessageType());
        assertEquals(expectedAttribute.getAttributeType(), actualRequest.getAttribute(expectedAttribute.getAttributeType()).getAttributeType());
        assertEquals(expectedAttribute.getConnectionId(), ((ConnectionIdAttribute) actualRequest.getAttribute(expectedAttribute.getAttributeType())).getConnectionId());
    }
}
