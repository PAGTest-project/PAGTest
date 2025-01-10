
package org.ice4j.message;

import org.ice4j.attribute.ErrorCodeAttribute;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageFactory_createAllocationErrorResponseTest {

    @Test
    public void testCreateAllocationErrorResponse() {
        // Given
        char errorCode = 400;
        String reasonPhrase = "Bad Request";

        // When
        Response response = MessageFactory.createAllocationErrorResponse(errorCode, reasonPhrase);

        // Then
        assertEquals(Message.ALLOCATE_ERROR_RESPONSE, response.getMessageType());
        ErrorCodeAttribute errorCodeAttribute = (ErrorCodeAttribute) response.getAttribute(ErrorCodeAttribute.class);
        assertNotNull(errorCodeAttribute);
        assertEquals((int) errorCode, errorCodeAttribute.getErrorCode());
        assertEquals(reasonPhrase, errorCodeAttribute.getReasonPhrase());
    }
}
