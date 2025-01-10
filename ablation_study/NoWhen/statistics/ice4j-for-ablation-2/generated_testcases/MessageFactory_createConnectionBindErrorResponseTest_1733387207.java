
package org.ice4j.message;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageFactory_createConnectionBindErrorResponseTest {

    @Test
    public void testCreateConnectionBindErrorResponse_ValidErrorCode() {
        char errorCode = '4';
        Response response = MessageFactory.createConnectionBindErrorResponse(errorCode);
        assertNotNull(response);
        assertTrue(response.isErrorResponse());
    }

    @Test
    public void testCreateConnectionBindErrorResponse_InvalidErrorCode() {
        char invalidErrorCode = 'a';
        assertThrows(IllegalArgumentException.class, () -> {
            MessageFactory.createConnectionBindErrorResponse(invalidErrorCode, "Invalid error code");
        });
    }
}
