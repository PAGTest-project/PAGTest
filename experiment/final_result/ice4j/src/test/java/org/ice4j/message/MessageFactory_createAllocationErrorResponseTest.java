
package org.ice4j.message;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageFactory_createAllocationErrorResponseTest {

    @Test
    public void testCreateAllocationErrorResponse_WithErrorCode() {
        Response response = MessageFactory.createAllocationErrorResponse('4');
        assertNotNull(response);
        assertTrue(response.isErrorResponse());
    }
}
