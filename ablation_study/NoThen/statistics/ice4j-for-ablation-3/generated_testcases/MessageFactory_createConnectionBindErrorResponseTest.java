
package org.ice4j.message;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageFactory_createConnectionBindErrorResponseTest {

    @Test
    public void testCreateConnectionBindErrorResponse() {
        // Given
        char errorCode = 0x0000; // Use a valid error code value

        // When
        Response response = MessageFactory.createConnectionBindErrorResponse(errorCode);

        // Then
        assertNotNull(response);
        assertTrue(response.isErrorResponse());
    }
}
