
package org.ice4j.message;

import static org.junit.jupiter.api.Assertions.*;
import org.ice4j.*;
import org.ice4j.attribute.*;
import org.junit.jupiter.api.*;

public class MessageFactory_addLongTermCredentialAttributesTest {

    @Test
    public void testAddLongTermCredentialAttributes() {
        Request request = new Request();
        byte[] username = "testUser".getBytes();
        byte[] realm = "testRealm".getBytes();
        byte[] nonce = "testNonce".getBytes();

        assertDoesNotThrow(() -> MessageFactory.addLongTermCredentialAttributes(request, username, realm, nonce));

        // Verify attributes are added to the request
        assertTrue(request.containsAttribute(Attribute.USERNAME));
        assertTrue(request.containsAttribute(Attribute.REALM));
        assertTrue(request.containsAttribute(Attribute.NONCE));
        assertTrue(request.containsAttribute(Attribute.MESSAGE_INTEGRITY));
    }

    @Test
    public void testAddLongTermCredentialAttributes_UnsupportedEncodingException() {
        Request request = new Request();
        byte[] username = "testUser".getBytes();
        byte[] realm = "testRealm".getBytes();
        byte[] nonce = "testNonce".getBytes();

        // Simulate UnsupportedEncodingException by passing invalid encoding
        byte[] invalidUsername = new byte[] { (byte) 0xFF, (byte) 0xFE, (byte) 0xFD };

        StunException exception = assertThrows(StunException.class, () -> {
            MessageFactory.addLongTermCredentialAttributes(request, invalidUsername, realm, nonce);
        });

        assertEquals("username", exception.getMessage());
    }
}
