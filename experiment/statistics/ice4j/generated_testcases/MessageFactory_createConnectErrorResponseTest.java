
package org.ice4j.message;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageFactory_createConnectErrorResponseTest {

    @Test
    public void testCreateConnectErrorResponse_withErrorCode() {
        char errorCode = '4';
        Response response = MessageFactory.createConnectErrorResponse(errorCode);
        assertNotNull(response);
        assertTrue(response.isErrorResponse());
    }

    @Test
    public void testCreateConnectErrorResponse_withNullReasonPhrase() {
        char errorCode = '5';
        Response response = MessageFactory.createConnectErrorResponse(errorCode);
        assertNotNull(response);
        assertTrue(response.isErrorResponse());
    }
}
