
package org.ice4j.message;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageFactory_createConnectionBindErrorResponseTest {

    @Test
    public void testCreateConnectionBindErrorResponse() {
        // Given
        char errorCode = ErrorCodeAttribute.UNKNOWN_ATTRIBUTE;

        // When
        Response response = MessageFactory.createConnectionBindErrorResponse(errorCode);

        // Then
        assertNotNull(response);
        assertTrue(response.isErrorResponse());
    }
}
