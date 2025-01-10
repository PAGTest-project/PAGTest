
package org.ice4j.stack;

import org.ice4j.*;
import org.ice4j.attribute.*;
import org.ice4j.message.*;
import org.ice4j.security.*;
import org.ice4j.socket.*;
import org.jitsi.utils.concurrent.*;
import javax.crypto.*;
import java.io.*;
import java.net.*;
import java.security.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.logging.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class StunStack_validateMessageIntegrityTest {

    private StunStack stunStack;
    private CredentialsManager credentialsManager;
    private Logger logger;

    @BeforeEach
    public void setUp() {
        stunStack = new StunStack();
        credentialsManager = mock(CredentialsManager.class);
        logger = mock(Logger.class);
        stunStack.logger = logger;
        stunStack.credentialsManager = credentialsManager;
    }

    @Test
    public void testValidateMessageIntegrityValid() {
        // Given
        String username = "validUser:validFrag";
        boolean shortTermCredentialMechanism = true;
        byte[] key = "validKey".getBytes();
        when(credentialsManager.getLocalKey(anyString())).thenReturn(key);

        MessageIntegrityAttribute msgInt = mock(MessageIntegrityAttribute.class);
        when(msgInt.getLocationInMessage()).thenReturn(10);
        when(msgInt.getDataLength()).thenReturn(20);
        when(msgInt.getHmacSha1Content()).thenReturn("validHmacSha1Content".getBytes());

        RawMessage message = mock(RawMessage.class);
        when(message.getBytes()).thenReturn(new byte[30]);

        // When
        boolean result = stunStack.validateMessageIntegrity(msgInt, username, shortTermCredentialMechanism, message);

        // Then
        assertTrue(result);
        verify(logger).finest("Successfully verified msg integrity");
    }

    @Test
    public void testValidateMessageIntegrityInvalidUsername() {
        // Given
        String username = "";
        boolean shortTermCredentialMechanism = true;

        MessageIntegrityAttribute msgInt = mock(MessageIntegrityAttribute.class);
        RawMessage message = mock(RawMessage.class);

        // When
        boolean result = stunStack.validateMessageIntegrity(msgInt, username, shortTermCredentialMechanism, message);

        // Then
        assertFalse(result);
        verify(logger).log(Level.FINE, "Received a message with an improperly formatted username");
    }

    @Test
    public void testValidateMessageIntegrityNullKey() {
        // Given
        String username = "validUser:validFrag";
        boolean shortTermCredentialMechanism = true;
        when(credentialsManager.getLocalKey(anyString())).thenReturn(null);

        MessageIntegrityAttribute msgInt = mock(MessageIntegrityAttribute.class);
        RawMessage message = mock(RawMessage.class);

        // When
        boolean result = stunStack.validateMessageIntegrity(msgInt, username, shortTermCredentialMechanism, message);

        // Then
        assertFalse(result);
    }

    @Test
    public void testValidateMessageIntegrityHmacSha1Mismatch() {
        // Given
        String username = "validUser:validFrag";
        boolean shortTermCredentialMechanism = true;
        byte[] key = "validKey".getBytes();
        when(credentialsManager.getLocalKey(anyString())).thenReturn(key);

        MessageIntegrityAttribute msgInt = mock(MessageIntegrityAttribute.class);
        when(msgInt.getLocationInMessage()).thenReturn(10);
        when(msgInt.getDataLength()).thenReturn(20);
        when(msgInt.getHmacSha1Content()).thenReturn("invalidHmacSha1Content".getBytes());

        RawMessage message = mock(RawMessage.class);
        when(message.getBytes()).thenReturn(new byte[30]);

        // When
        boolean result = stunStack.validateMessageIntegrity(msgInt, username, shortTermCredentialMechanism, message);

        // Then
        assertFalse(result);
        verify(logger).log(Level.FINE, "Received a message with a wrong MESSAGE-INTEGRITY HMAC-SHA1 signature: expected: validHmacSha1Content, received: invalidHmacSha1Content");
    }
}
