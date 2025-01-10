
package org.ice4j.message;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageFactory_createSharedSecretRequestTest {

    @Test
    public void testCreateSharedSecretRequest() {
        UnsupportedOperationException exception = assertThrows(
            UnsupportedOperationException.class,
            () -> MessageFactory.createSharedSecretRequest()
        );
        assertEquals("Shared Secret Support is not currently implemented", exception.getMessage());
    }
}
