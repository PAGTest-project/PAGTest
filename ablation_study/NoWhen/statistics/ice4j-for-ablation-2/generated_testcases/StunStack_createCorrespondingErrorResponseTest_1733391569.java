
package org.ice4j.stack;

import org.ice4j.message.MessageFactory;
import org.ice4j.message.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StunStack_createCorrespondingErrorResponseTest {

    private StunStack stunStack;

    @BeforeEach
    public void setUp() {
        stunStack = new StunStack();
    }

    @Test
    public void testCreateCorrespondingErrorResponseBindingRequestWithUnknownAttributes() {
        char requestType = Message.BINDING_REQUEST;
        char errorCode = ErrorCodeAttribute.UNKNOWN_ATTRIBUTE;
        String reasonPhrase = "unknown attribute";
        char[] unknownAttributes = {'A', 'B'};

        Response response = stunStack.createCorrespondingErrorResponse(
            requestType, errorCode, reasonPhrase, unknownAttributes);

        assertNotNull(response);
        assertEquals(errorCode, response.getErrorCode());
        assertEquals(reasonPhrase, response.getReasonPhrase());
        assertArrayEquals(unknownAttributes, response.getUnknownAttributes());
    }

    @Test
    public void testCreateCorrespondingErrorResponseBindingRequestWithoutUnknownAttributes() {
        char requestType = Message.BINDING_REQUEST;
        char errorCode = ErrorCodeAttribute.BAD_REQUEST;
        String reasonPhrase = "bad request";

        Response response = stunStack.createCorrespondingErrorResponse(
            requestType, errorCode, reasonPhrase);

        assertNotNull(response);
        assertEquals(errorCode, response.getErrorCode());
        assertEquals(reasonPhrase, response.getReasonPhrase());
    }

    @Test
    public void testCreateCorrespondingErrorResponseNonBindingRequest() {
        char requestType = 'X'; // Non-binding request type
        char errorCode = ErrorCodeAttribute.SERVER_ERROR;
        String reasonPhrase = "server error";

        Response response = stunStack.createCorrespondingErrorResponse(
            requestType, errorCode, reasonPhrase);

        assertNull(response);
    }
}
