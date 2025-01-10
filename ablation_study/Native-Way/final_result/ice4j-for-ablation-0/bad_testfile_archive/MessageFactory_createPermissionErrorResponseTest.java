
package org.ice4j.message;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.ice4j.attribute.ErrorCodeAttribute;

public class MessageFactory_createPermissionErrorResponseTest {

    @Test
    public void testCreatePermissionErrorResponse() {
        char errorCode = ErrorCodeAttribute.UNKNOWN_ATTRIBUTE;
        String reasonPhrase = "Unknown attribute";

        Response response = MessageFactory.createPermissionErrorResponse(errorCode, reasonPhrase);

        assertEquals(Message.CREATEPERMISSION_ERROR_RESPONSE, response.getMessageType());
        ErrorCodeAttribute errorCodeAttribute = (ErrorCodeAttribute) response.getAttribute(ErrorCodeAttribute.class);
        assertNotNull(errorCodeAttribute);
        assertEquals(errorCode, errorCodeAttribute.getErrorCode());
        assertEquals(reasonPhrase, errorCodeAttribute.getReasonPhrase());
    }
}
