
package org.ice4j.message;

import static org.junit.jupiter.api.Assertions.*;
import org.ice4j.*;
import org.ice4j.attribute.*;
import org.junit.jupiter.api.*;

public class MessageFactory_createConnectionBindRequestTest {

    @Test
    public void testCreateConnectionBindRequest() {
        int connectionIdValue = 12345;
        Request expectedRequest = new Request();
        expectedRequest.setMessageType(Message.CONNECTION_BIND_REQUEST);
        ConnectionIdAttribute connectionIdAttribute = AttributeFactory.createConnectionIdAttribute(connectionIdValue);
        expectedRequest.putAttribute(connectionIdAttribute);

        Request actualRequest = MessageFactory.createConnectionBindRequest(connectionIdValue);
        assertEquals(expectedRequest, actualRequest);
    }

    @Test
    public void testCreateConnectionBindRequestWithInvalidConnectionId() {
        int invalidConnectionIdValue = -1;
        assertThrows(IllegalArgumentException.class, () -> {
            AttributeFactory.createConnectionIdAttribute(invalidConnectionIdValue);
        });
    }
}
