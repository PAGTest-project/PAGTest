
package org.ice4j.message;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageFactory_createRefreshErrorResponseTest {

    @Test
    public void testCreateRefreshErrorResponse_WithErrorCode() {
        Response response = MessageFactory.createRefreshErrorResponse('4');
        assertTrue(response.isErrorResponse());
    }

    @Test
    public void testCreateRefreshErrorResponse_WithErrorCodeAndReasonPhrase() {
        Response response = MessageFactory.createRefreshErrorResponse('4', "Reason");
        assertTrue(response.isErrorResponse());
    }
}
