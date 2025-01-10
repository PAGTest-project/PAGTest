
package org.ice4j.message;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageFactory_createChannelBindErrorResponseTest {

    @Test
    public void testCreateChannelBindErrorResponse_WithErrorCode() {
        char errorCode = '4';
        Response response = MessageFactory.createChannelBindErrorResponse(errorCode);
        assertNotNull(response);
        assertTrue(response.isErrorResponse());
    }

    @Test
    public void testCreateChannelBindErrorResponse_WithErrorCodeAndReasonPhrase() {
        char errorCode = '5';
        String reasonPhrase = "Test Reason Phrase";
        Response response = MessageFactory.createChannelBindErrorResponse(errorCode, reasonPhrase);
        assertNotNull(response);
        assertTrue(response.isErrorResponse());
    }
}
