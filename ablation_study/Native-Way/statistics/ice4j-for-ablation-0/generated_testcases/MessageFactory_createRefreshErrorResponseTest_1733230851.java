
package org.ice4j.message;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.ice4j.attribute.ErrorCodeAttribute;

public class MessageFactory_createRefreshErrorResponseTest {

    @Test
    public void testCreateRefreshErrorResponse_Success() {
        Response response = MessageFactory.createRefreshErrorResponse((char) 4, "Reason");
        assertEquals(Message.REFRESH_ERROR_RESPONSE, response.getMessageType());
        ErrorCodeAttribute errorCodeAttribute = (ErrorCodeAttribute) response.getAttribute(ErrorCodeAttribute.class);
        assertNotNull(errorCodeAttribute);
        assertEquals(4, errorCodeAttribute.getErrorCode());
        assertEquals("Reason", errorCodeAttribute.getReasonPhrase());
    }

    @Test
    public void testCreateRefreshErrorResponse_IllegalArgumentException() {
        Response response = MessageFactory.createRefreshErrorResponse((char) 'a', "Invalid");
        assertEquals(Message.REFRESH_ERROR_RESPONSE, response.getMessageType());
        assertNull(response.getAttribute(ErrorCodeAttribute.class));
    }
}
