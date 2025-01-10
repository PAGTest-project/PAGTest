
package org.ice4j.message;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class MessageFactory_createSharedSecretResponseTest {

    @Test
    public void testCreateSharedSecretResponse() {
        assertThrows(UnsupportedOperationException.class, () -> {
            MessageFactory.createSharedSecretResponse();
        });
    }
}
