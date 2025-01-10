
package org.ice4j.message;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.ice4j.message.MessageFactory;
import org.ice4j.message.Response;

public class MessageFactory_createConnectionBindErrorResponseTest {

    @Test
    public void testCreateConnectionBindErrorResponse_WithErrorCode() {
        // Given
        char errorCode = '4';

        // When
        Response response = MessageFactory.createConnectionBindErrorResponse(errorCode);

        // Then
        assertNotNull(response);
        assertTrue(response.isErrorResponse());
    }

    @Test
    public void testCreateConnectionBindErrorResponse_WithNullReasonPhrase() {
        // Given
        char errorCode = '5';

        // When
        Response response = MessageFactory.createConnectionBindErrorResponse(errorCode);

        // Then
        assertNotNull(response);
        assertTrue(response.isErrorResponse());
    }
}
