
package org.ice4j.message;

import org.junit.jupiter.api.Test;
import org.ice4j.StunException;
import org.ice4j.attribute.AttributeFactory;
import org.ice4j.attribute.MessageIntegrityAttribute;
import org.mockito.Mockito;

import java.io.UnsupportedEncodingException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MessageFactory_addLongTermCredentialAttributesTest {

    @Test
    void testAddLongTermCredentialAttributes_Success() throws StunException {
        // Given
        Request request = new Request();
        byte[] username = "testUser".getBytes();
        byte[] realm = "testRealm".getBytes();
        byte[] nonce = "testNonce".getBytes();

        // When
        MessageFactory.addLongTermCredentialAttributes(request, username, realm, nonce);

        // Then
        assertNotNull(request.getAttribute(AttributeFactory.createUsernameAttribute(username)));
        assertNotNull(request.getAttribute(AttributeFactory.createRealmAttribute(realm)));
        assertNotNull(request.getAttribute(AttributeFactory.createNonceAttribute(nonce)));
        assertNotNull(request.getAttribute(AttributeFactory.createMessageIntegrityAttribute("testUser")));
    }

    @Test
    void testAddLongTermCredentialAttributes_UnsupportedEncodingException() {
        // Given
        Request request = new Request();
        byte[] username = "testUser".getBytes();
        byte[] realm = "testRealm".getBytes();
        byte[] nonce = "testNonce".getBytes();

        // Mocking the behavior of AttributeFactory.createMessageIntegrityAttribute to throw UnsupportedEncodingException
        doThrow(new UnsupportedEncodingException()).when(AttributeFactory.class, "createMessageIntegrityAttribute", any(String.class));

        // When & Then
        assertThrows(StunException.class, () -> {
            MessageFactory.addLongTermCredentialAttributes(request, username, realm, nonce);
        });
    }
}
