
package org.ice4j.message;

import static org.junit.jupiter.api.Assertions.*;
import org.ice4j.*;
import org.ice4j.attribute.*;
import org.junit.jupiter.api.*;

public class MessageFactory_createConnectErrorResponseTest {

    @Test
    public void testCreateConnectErrorResponseWithValidErrorCode() {
        char errorCode = 400;
        Response expectedResponse = new Response();
        expectedResponse.setMessageType(Message.CONNECT_ERROR_RESPONSE);

        ErrorCodeAttribute errorCodeAttribute = AttributeFactory.createErrorCodeAttribute(errorCode, null);
        expectedResponse.putAttribute(errorCodeAttribute);

        Response actualResponse = MessageFactory.createConnectErrorResponse(errorCode);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void testCreateConnectErrorResponseWithInvalidErrorCode() {
        char invalidErrorCode = 1000; // Invalid error code
        assertThrows(IllegalArgumentException.class, () -> {
            MessageFactory.createConnectErrorResponse(invalidErrorCode, "Invalid error code");
        });
    }
}
