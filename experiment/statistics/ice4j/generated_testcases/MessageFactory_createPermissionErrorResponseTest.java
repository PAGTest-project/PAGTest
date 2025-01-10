
package org.ice4j.message;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.ice4j.attribute.ErrorCodeAttribute;
import org.ice4j.attribute.AttributeFactory;

public class MessageFactory_createPermissionErrorResponseTest {

    @Test
    public void testCreatePermissionErrorResponse() {
        // Given
        char errorCode = ErrorCodeAttribute.UNKNOWN_ATTRIBUTE;
        String reasonPhrase = "Unknown attribute";

        // When
        Response response = MessageFactory.createPermissionErrorResponse(errorCode, reasonPhrase);

        // Then
        assertEquals(Message.CREATEPERMISSION_ERROR_RESPONSE, response.getMessageType());
        ErrorCodeAttribute errorCodeAttribute = (ErrorCodeAttribute) response.getAttribute(ErrorCodeAttribute.class);
        assertNotNull(errorCodeAttribute);
        assertEquals(errorCode, errorCodeAttribute.getErrorCode());
        assertEquals(reasonPhrase, errorCodeAttribute.getReasonPhrase());
    }
}
