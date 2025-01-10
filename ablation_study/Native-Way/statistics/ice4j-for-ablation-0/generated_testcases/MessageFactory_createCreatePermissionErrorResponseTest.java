
package org.ice4j.message;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageFactory_createCreatePermissionErrorResponseTest {

    @Test
    public void testCreateCreatePermissionErrorResponse() {
        Response response = MessageFactory.createCreatePermissionErrorResponse('4');
        assertNotNull(response);
        assertTrue(response.isErrorResponse());
    }
}
