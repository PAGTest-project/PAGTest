
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
    public void testCreateCorrespondingErrorResponseWithUnknownAttributes() {
        char requestType = '0'; // Assuming '0' is a valid BINDING_REQUEST type
        char errorCode = '4';
        String reasonPhrase = "Unknown attribute";
        char[] unknownAttributes = {'A', 'B', 'C'};

        Response response = stunStack.createCorrespondingErrorResponse(requestType, errorCode, reasonPhrase, unknownAttributes);

        assertNotNull(response);
        assertEquals(errorCode, response.getErrorCode());
        assertEquals(reasonPhrase, response.getReasonPhrase());
        assertArrayEquals(unknownAttributes, response.getUnknownAttributes());
    }

    @Test
    public void testCreateCorrespondingErrorResponseWithoutUnknownAttributes() {
        char requestType = '0'; // Assuming '0' is a valid BINDING_REQUEST type
        char errorCode = '4';
        String reasonPhrase = "Unknown attribute";

        Response response = stunStack.createCorrespondingErrorResponse(requestType, errorCode, reasonPhrase);

        assertNotNull(response);
        assertEquals(errorCode, response.getErrorCode());
        assertEquals(reasonPhrase, response.getReasonPhrase());
        assertNull(response.getUnknownAttributes());
    }

    @Test
    public void testCreateCorrespondingErrorResponseWithInvalidRequestType() {
        char requestType = 'X';
        char errorCode = '4';
        String reasonPhrase = "Unknown attribute";

        Response response = stunStack.createCorrespondingErrorResponse(requestType, errorCode, reasonPhrase);

        assertNull(response);
    }
}
