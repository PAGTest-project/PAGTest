
package org.ice4j.message;

import static org.junit.jupiter.api.Assertions.*;
import org.ice4j.*;
import org.ice4j.attribute.*;
import org.junit.jupiter.api.*;

public class MessageFactory_createBindingErrorResponseTest {

    @Test
    public void testCreateBindingErrorResponseWithErrorCodeAndReasonPhrase() {
        char errorCode = 400;
        String reasonPhrase = "Bad Request";

        Response expectedReturn = new Response();
        expectedReturn.setMessageType(Message.BINDING_ERROR_RESPONSE);

        Attribute errorCodeAtt = AttributeFactory.createErrorCodeAttribute(errorCode, reasonPhrase);
        expectedReturn.putAttribute(errorCodeAtt);

        Response actualReturn = MessageFactory.createBindingErrorResponse(errorCode, reasonPhrase);
        assertEquals(expectedReturn, actualReturn);
    }

    @Test
    public void testCreateBindingErrorResponseWithErrorCodeOnly() {
        char errorCode = 404;
        String reasonPhrase = null;

        Response expectedReturn = new Response();
        expectedReturn.setMessageType(Message.BINDING_ERROR_RESPONSE);

        Attribute errorCodeAtt = AttributeFactory.createErrorCodeAttribute(errorCode, "");
        expectedReturn.putAttribute(errorCodeAtt);

        Response actualReturn = MessageFactory.createBindingErrorResponse(errorCode, reasonPhrase);
        assertEquals(expectedReturn, actualReturn);
    }

    @Test
    public void testCreateBindingErrorResponseWithReasonPhraseOnly() {
        char errorCode = 0;
        String reasonPhrase = "Not Found";

        Response expectedReturn = new Response();
        expectedReturn.setMessageType(Message.BINDING_ERROR_RESPONSE);

        Attribute errorCodeAtt = AttributeFactory.createErrorCodeAttribute(errorCode, reasonPhrase);
        expectedReturn.putAttribute(errorCodeAtt);

        Response actualReturn = MessageFactory.createBindingErrorResponse(errorCode, reasonPhrase);
        assertEquals(expectedReturn, actualReturn);
    }

    @Test
    public void testCreateBindingErrorResponseWithNoErrorCodeAndNoReasonPhrase() {
        char errorCode = 0;
        String reasonPhrase = null;

        Response expectedReturn = new Response();
        expectedReturn.setMessageType(Message.BINDING_ERROR_RESPONSE);

        Attribute errorCodeAtt = AttributeFactory.createErrorCodeAttribute(errorCode, "");
        expectedReturn.putAttribute(errorCodeAtt);

        Response actualReturn = MessageFactory.createBindingErrorResponse(errorCode, reasonPhrase);
        assertEquals(expectedReturn, actualReturn);
    }
}
