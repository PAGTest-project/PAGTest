
package org.ice4j.message;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.ice4j.attribute.ErrorCodeAttribute;
import org.ice4j.attribute.AttributeFactory;

public class MessageFactory_createRefreshErrorResponseTest {

    @Test
    public void testCreateRefreshErrorResponse_Success() {
        // Given
        char errorCode = ErrorCodeAttribute.UNKNOWN_ATTRIBUTE;
        String reasonPhrase = "Unknown attribute";

        // When
        Response result = MessageFactory.createRefreshErrorResponse(errorCode, reasonPhrase);

        // Then
        assertNotNull(result);
        assertEquals(Message.REFRESH_ERROR_RESPONSE, result.getMessageType());
        ErrorCodeAttribute errorCodeAttribute = (ErrorCodeAttribute) result.getAttribute(ErrorCodeAttribute.class);
        assertNotNull(errorCodeAttribute);
        assertEquals(errorCode, errorCodeAttribute.getErrorCode());
        assertEquals(reasonPhrase, errorCodeAttribute.getReasonPhrase());
    }

    @Test
    public void testCreateRefreshErrorResponse_IllegalArgumentException() {
        // Given
        char errorCode = 'A'; // Invalid error code to trigger IllegalArgumentException
        String reasonPhrase = "Invalid error code";

        // When
        Response result = MessageFactory.createRefreshErrorResponse(errorCode, reasonPhrase);

        // Then
        assertNotNull(result);
        assertEquals(Message.REFRESH_ERROR_RESPONSE, result.getMessageType());
        assertNull(result.getAttribute(ErrorCodeAttribute.class));
    }
}
