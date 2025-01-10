
package org.ice4j.message;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageFactory_createSharedSecretErrorResponseTest {

    @Test
    public void testCreateSharedSecretErrorResponse() {
        UnsupportedOperationException exception = assertThrows(
            UnsupportedOperationException.class,
            () -> MessageFactory.createSharedSecretErrorResponse()
        );
        assertEquals("Shared Secret Support is not currently implemented", exception.getMessage());
    }
}
