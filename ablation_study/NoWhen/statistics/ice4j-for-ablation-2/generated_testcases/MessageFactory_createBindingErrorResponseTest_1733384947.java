
package org.ice4j.message;

import static org.junit.jupiter.api.Assertions.*;
import org.ice4j.*;
import org.ice4j.attribute.*;
import org.junit.jupiter.api.*;

public class MessageFactory_createBindingErrorResponseTest {

    @Test
    public void testCreateBindingErrorResponse_WithReasonPhrase() {
        char errorCode = 400;
        String reasonPhrase = "Bad Request";

        Response expectedResponse = new Response();
        expectedResponse.setMessageType(Message.BINDING_ERROR_RESPONSE);

        ErrorCodeAttribute errorCodeAttribute = AttributeFactory.createErrorCodeAttribute(errorCode, reasonPhrase);
        expectedResponse.putAttribute(errorCodeAttribute);

        Response actualResponse = MessageFactory.createBindingErrorResponse(errorCode, reasonPhrase);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void testCreateBindingErrorResponse_WithoutReasonPhrase() {
        char errorCode = 404;

        Response expectedResponse = new Response();
        expectedResponse.setMessageType(Message.BINDING_ERROR_RESPONSE);

        ErrorCodeAttribute errorCodeAttribute = AttributeFactory.createErrorCodeAttribute(errorCode);
        expectedResponse.putAttribute(errorCodeAttribute);

        Response actualResponse = MessageFactory.createBindingErrorResponse(errorCode);

        assertEquals(expectedResponse, actualResponse);
    }
}
