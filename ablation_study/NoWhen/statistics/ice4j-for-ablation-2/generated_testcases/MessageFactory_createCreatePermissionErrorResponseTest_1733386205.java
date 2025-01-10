
package org.ice4j.message;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.ice4j.Response;

public class MessageFactory_createCreatePermissionErrorResponseTest {

    @Test
    public void testCreateCreatePermissionErrorResponse() {
        // Given
        char errorCode = '4';

        // When
        Response response = MessageFactory.createCreatePermissionErrorResponse(errorCode);

        // Then
        assertNotNull(response);
        assertTrue(response.isErrorResponse());
    }
}
