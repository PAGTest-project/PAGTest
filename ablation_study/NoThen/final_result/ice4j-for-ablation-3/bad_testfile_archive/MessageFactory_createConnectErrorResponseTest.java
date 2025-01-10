
package org.ice4j.message;

import static org.junit.jupiter.api.Assertions.*;
import org.ice4j.*;
import org.ice4j.attribute.*;
import org.junit.jupiter.api.*;

public class MessageFactory_createConnectErrorResponseTest {

    @Test
    public void testCreateConnectErrorResponseWithErrorCode() {
        char errorCode = 400;

        Response expectedReturn = new Response();
        expectedReturn.setMessageType(Message.CONNECT_ERROR_RESPONSE);

        Attribute errorCodeAtt = AttributeFactory
            .createErrorCodeAttribute(errorCode, null);
        expectedReturn.putAttribute(errorCodeAtt);

        Response actualReturn = MessageFactory
            .createConnectErrorResponse(errorCode);
        assertEquals(expectedReturn, actualReturn);
    }

    @Test
    public void testCreateConnectErrorResponseWithInvalidErrorCode() {
        char errorCode = 1000; // Invalid error code

        assertThrows(IllegalArgumentException.class, () -> {
            MessageFactory.createConnectErrorResponse(errorCode, "Invalid Error Code");
        });
    }
}
