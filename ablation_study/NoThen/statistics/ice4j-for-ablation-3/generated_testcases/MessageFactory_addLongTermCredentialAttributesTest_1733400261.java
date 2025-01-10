
package org.ice4j.message;

import static org.junit.jupiter.api.Assertions.*;
import org.ice4j.*;
import org.ice4j.attribute.*;
import org.junit.jupiter.api.*;

public class MessageFactory_addLongTermCredentialAttributesTest {

    @Test
    public void testAddLongTermCredentialAttributes() throws StunException {
        Request request = new Request();
        byte[] username = "user".getBytes();
        byte[] realm = "realm".getBytes();
        byte[] nonce = "nonce".getBytes();

        MessageFactory.addLongTermCredentialAttributes(request, username, realm, nonce);

        assertTrue(request.containsAttribute(Attribute.USERNAME));
        assertTrue(request.containsAttribute(Attribute.REALM));
        assertTrue(request.containsAttribute(Attribute.NONCE));
        assertTrue(request.containsAttribute(Attribute.MESSAGE_INTEGRITY));
    }

    @Test
    public void testAddLongTermCredentialAttributesWithInvalidUsername() {
        Request request = new Request();
        byte[] username = new byte[513]; // Invalid username length
        byte[] realm = "realm".getBytes();
        byte[] nonce = "nonce".getBytes();

        assertThrows(StunException.class, () -> {
            MessageFactory.addLongTermCredentialAttributes(request, username, realm, nonce);
        });
    }
}
