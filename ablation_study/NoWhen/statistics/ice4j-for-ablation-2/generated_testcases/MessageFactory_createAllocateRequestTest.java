
package org.ice4j.message;

import static org.junit.jupiter.api.Assertions.*;
import org.ice4j.*;
import org.ice4j.attribute.*;
import org.junit.jupiter.api.*;

public class MessageFactory_createAllocateRequestTest {

    @Test
    public void testCreateAllocateRequest() {
        Request expectedRequest = new Request();
        expectedRequest.setMessageType(Message.ALLOCATE_REQUEST);

        Request actualRequest = MessageFactory.createAllocateRequest();

        assertEquals(expectedRequest.getMessageType(), actualRequest.getMessageType());
    }
}
