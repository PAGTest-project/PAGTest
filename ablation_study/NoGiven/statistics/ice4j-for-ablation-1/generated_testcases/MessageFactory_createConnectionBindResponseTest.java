
package org.ice4j.message;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageFactory_createConnectionBindResponseTest {

    @Test
    public void testCreateConnectionBindResponse() {
        Response response = MessageFactory.createConnectionBindResponse();
        assertEquals(Message.CONNECTION_BIND_SUCCESS_RESPONSE, response.getMessageType());
    }
}
