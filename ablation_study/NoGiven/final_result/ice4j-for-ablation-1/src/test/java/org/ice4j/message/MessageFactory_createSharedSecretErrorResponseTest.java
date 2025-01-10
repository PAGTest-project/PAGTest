
package org.ice4j.message;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class MessageFactory_createSharedSecretErrorResponseTest {

    @Test
    public void testCreateSharedSecretErrorResponse() {
        assertThrows(UnsupportedOperationException.class, () -> {
            MessageFactory.createSharedSecretErrorResponse();
        });
    }
}
