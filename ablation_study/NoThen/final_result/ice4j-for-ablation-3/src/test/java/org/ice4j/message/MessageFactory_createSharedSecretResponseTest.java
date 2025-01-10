
package org.ice4j.message;

import static org.junit.jupiter.api.Assertions.*;
import org.ice4j.*;
import org.ice4j.attribute.*;
import org.junit.jupiter.api.*;

public class MessageFactory_createSharedSecretResponseTest {

    @Test
    public void testCreateSharedSecretResponse() {
        UnsupportedOperationException exception = assertThrows(
            UnsupportedOperationException.class,
            () -> MessageFactory.createSharedSecretResponse()
        );
        assertEquals(
            "Shared Secret Support is not currently implemented",
            exception.getMessage()
        );
    }
}
