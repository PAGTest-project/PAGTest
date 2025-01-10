
package org.ice4j.message;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageFactory_createChannelBindResponseTest {

    @Test
    public void testCreateChannelBindResponse() {
        Response response = MessageFactory.createChannelBindResponse();
        assertEquals(Message.CHANNELBIND_RESPONSE, response.getMessageType());
    }
}
