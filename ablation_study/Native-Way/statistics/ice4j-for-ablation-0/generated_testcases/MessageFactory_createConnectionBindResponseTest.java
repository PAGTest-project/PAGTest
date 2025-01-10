
package org.ice4j.message;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class MessageFactory_createConnectionBindResponseTest {

    @Test
    public void testCreateConnectionBindResponse() {
        Response response = MessageFactory.createConnectionBindResponse();
        assertEquals(Message.CONNECTION_BIND_SUCCESS_RESPONSE, response.getMessageType());
    }
}
