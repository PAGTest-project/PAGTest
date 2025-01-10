
package org.ice4j.message;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

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
