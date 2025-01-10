
package org.ice4j.message;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.ice4j.attribute.ErrorCodeAttribute;

public class MessageFactory_createRefreshErrorResponseTest {

    @Test
    public void testCreateRefreshErrorResponse_Success() {
        // Given
        char errorCode = ErrorCodeAttribute.BAD_REQUEST;
        String reasonPhrase = "Bad Request";

        // When
        Response response = MessageFactory.createRefreshErrorResponse(errorCode, reasonPhrase);

        // Then
        assertNotNull(response);
        assertEquals(Message.REFRESH_ERROR_RESPONSE, response.getMessageType());
        ErrorCodeAttribute errorCodeAttribute = (ErrorCodeAttribute) response.getAttribute(ErrorCodeAttribute.class);
        assertNotNull(errorCodeAttribute);
        assertEquals(errorCode, errorCodeAttribute.getErrorCode());
        assertEquals(reasonPhrase, errorCodeAttribute.getReasonPhrase());
    }

    @Test
    public void testCreateRefreshErrorResponse_IllegalArgumentException() {
        // Given
        char errorCode = (char) 1000; // Invalid error code to trigger IllegalArgumentException
        String reasonPhrase = "Invalid Error Code";

        // When
        Response response = MessageFactory.createRefreshErrorResponse(errorCode, reasonPhrase);

        // Then
        assertNotNull(response);
        assertEquals(Message.REFRESH_ERROR_RESPONSE, response.getMessageType());
        assertNull(response.getAttribute(ErrorCodeAttribute.class));
    }
}
