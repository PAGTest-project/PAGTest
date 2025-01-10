
package org.ice4j.message;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageFactory_createConnectionBindErrorResponseTest {

    @Test
    public void testCreateConnectionBindErrorResponse_WithErrorCode() {
        char errorCode = '4';
        Response response = MessageFactory.createConnectionBindErrorResponse(errorCode);
        assertNotNull(response);
        assertTrue(response.isErrorResponse());
    }

    @Test
    public void testCreateConnectionBindErrorResponse_WithNullReasonPhrase() {
        char errorCode = '5';
        Response response = MessageFactory.createConnectionBindErrorResponse(errorCode);
        assertNotNull(response);
        assertTrue(response.isErrorResponse());
    }
}
