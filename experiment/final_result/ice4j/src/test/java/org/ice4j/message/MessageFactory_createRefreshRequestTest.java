
package org.ice4j.message;

import static org.junit.jupiter.api.Assertions.*;
import org.ice4j.*;
import org.ice4j.attribute.*;
import org.junit.jupiter.api.*;

public class MessageFactory_createRefreshRequestTest {

    @Test
    public void testCreateRefreshRequest() {
        Request expectedRefreshRequest = new Request();
        expectedRefreshRequest.setMessageType(Message.REFRESH_REQUEST);

        Request actualRefreshRequest = MessageFactory.createRefreshRequest();
        assertEquals(expectedRefreshRequest.getMessageType(), actualRefreshRequest.getMessageType());
    }
}
