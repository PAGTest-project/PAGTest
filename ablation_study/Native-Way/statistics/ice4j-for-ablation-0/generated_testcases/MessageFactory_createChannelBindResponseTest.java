
package org.ice4j.message;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class MessageFactory_createChannelBindResponseTest {

    @Test
    public void testCreateChannelBindResponse() {
        Response response = MessageFactory.createChannelBindResponse();
        assertEquals(Message.CHANNELBIND_RESPONSE, response.getMessageType());
    }
}
