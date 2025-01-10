
package org.ice4j.message;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class MessageFactory_createSharedSecretErrorResponseTest {

    @Test
    public void testCreateSharedSecretErrorResponse() {
        assertThrows(UnsupportedOperationException.class, () -> {
            MessageFactory.createSharedSecretErrorResponse();
        });
    }
}
