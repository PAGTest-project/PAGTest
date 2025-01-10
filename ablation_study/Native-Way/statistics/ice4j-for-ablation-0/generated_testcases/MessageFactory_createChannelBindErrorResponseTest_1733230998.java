
package org.ice4j.message;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.ice4j.attribute.ErrorCodeAttribute;

public class MessageFactory_createChannelBindErrorResponseTest {

    @Test
    public void testCreateChannelBindErrorResponse() {
        char errorCode = ErrorCodeAttribute.BAD_REQUEST;
        String reasonPhrase = "Bad Request";

        Response response = MessageFactory.createChannelBindErrorResponse(errorCode, reasonPhrase);

        assertEquals(Message.CHANNELBIND_ERROR_RESPONSE, response.getMessageType());
        ErrorCodeAttribute errorCodeAttribute = (ErrorCodeAttribute) response.getAttribute(ErrorCodeAttribute.class);
        assertNotNull(errorCodeAttribute);
        assertEquals(errorCode, errorCodeAttribute.getErrorCode());
        assertEquals(reasonPhrase, errorCodeAttribute.getReasonPhrase());
    }
}
