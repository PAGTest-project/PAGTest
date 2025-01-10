
package org.ice4j.message;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.ice4j.message.MessageFactory;
import org.ice4j.message.Response;

public class MessageFactory_createConnectionBindResponseTest {

    @Test
    public void testCreateConnectionBindResponse() {
        Response response = MessageFactory.createConnectionBindResponse();
        assertEquals(Message.CONNECTION_BIND_SUCCESS_RESPONSE, response.getMessageType());
    }
}
