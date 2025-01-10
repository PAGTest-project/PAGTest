
package org.ice4j.message;

import static org.junit.jupiter.api.Assertions.*;
import org.ice4j.*;
import org.ice4j.attribute.*;
import org.junit.jupiter.api.*;

public class MessageFactory_createBindingErrorResponseTest {

    @Test
    public void testCreateBindingErrorResponse() {
        char errorCode = 400;
        String reasonPhrase = "Bad Request";

        Response expectedReturn = new Response();
        expectedReturn.setMessageType(Message.BINDING_ERROR_RESPONSE);

        ErrorCodeAttribute errorCodeAttribute = AttributeFactory
            .createErrorCodeAttribute(errorCode, reasonPhrase);
        expectedReturn.putAttribute(errorCodeAttribute);

        Response actualReturn = MessageFactory
            .createBindingErrorResponse(errorCode, reasonPhrase);
        assertEquals(expectedReturn, actualReturn);
    }
}
