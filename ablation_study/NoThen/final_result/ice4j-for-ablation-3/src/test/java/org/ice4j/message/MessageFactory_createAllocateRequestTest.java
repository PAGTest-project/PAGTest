
package org.ice4j.message;

import static org.junit.jupiter.api.Assertions.*;
import org.ice4j.*;
import org.ice4j.attribute.*;
import org.junit.jupiter.api.*;

public class MessageFactory_createAllocateRequestTest {

    @Test
    public void testCreateAllocateRequest() {
        Request expectedAllocateRequest = new Request();
        expectedAllocateRequest.setMessageType(Message.ALLOCATE_REQUEST);

        Request actualReturn = MessageFactory.createAllocateRequest();
        assertEquals(expectedAllocateRequest, actualReturn);
    }
}
