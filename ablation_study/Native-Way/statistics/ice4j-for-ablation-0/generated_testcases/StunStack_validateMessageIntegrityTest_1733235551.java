
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

public class StunStack_validateMessageIntegrityTest {
    private StunStack stunStack;
    private TransportAddress localAddress;
    private TransportAddress remoteAddress;
    private RawMessage rawMessage;
    private MessageIntegrityAttribute msgInt;
    private String username;
    private boolean shortTermCredentialMechanism;

    @BeforeEach
    public void setUp() throws Exception {
        stunStack = new StunStack();
        localAddress = new TransportAddress("127.0.0.1", 0, Transport.UDP);
        remoteAddress = new TransportAddress("127.0.0.1", 0, Transport.UDP);
        rawMessage = RawMessage.build(new byte[100], 100, remoteAddress, localAddress);
        msgInt = new MessageIntegrityAttribute();
        username = "testuser";
        shortTermCredentialMechanism = true;
    }

    @Test
    public void testValidateMessageIntegrityValid() {
        // Mock the necessary dependencies
        CredentialsManager credentialsManager = new CredentialsManager();
        byte[] key = new byte[32];
        new Random().nextBytes(key);
        credentialsManager.setLocalKey(username, key);
        stunStack.setCredentialsManager(credentialsManager);

        // Mock the HMAC-SHA1 calculation
        byte[] expectedHmacSha1 = new byte[20];
        new Random().nextBytes(expectedHmacSha1);
        msgInt.setHmacSha1Content(expectedHmacSha1);

        // Set up the raw message to match the expected HMAC-SHA1
        byte[] binMsg = new byte[msgInt.getLocationInMessage()];
        System.arraycopy(rawMessage.getBytes(), 0, binMsg, 0, binMsg.length);
        char messageLength = (char) (binMsg.length + Attribute.HEADER_LENGTH + msgInt.getDataLength() - Message.HEADER_LENGTH);
        binMsg[2] = (byte) (messageLength >> 8);
        binMsg[3] = (byte) (messageLength & 0xFF);
        byte[] calculatedHmacSha1 = MessageIntegrityAttribute.calculateHmacSha1(binMsg, 0, binMsg.length, key);
        msgInt.setHmacSha1Content(calculatedHmacSha1);

        // Test the method
        assertTrue(stunStack.validateMessageIntegrity(msgInt, username, shortTermCredentialMechanism, rawMessage));
    }

    @Test
    public void testValidateMessageIntegrityInvalidUsername() {
        username = "";
        assertFalse(stunStack.validateMessageIntegrity(msgInt, username, shortTermCredentialMechanism, rawMessage));
    }

    @Test
    public void testValidateMessageIntegrityInvalidKey() {
        // Mock the necessary dependencies
        CredentialsManager credentialsManager = new CredentialsManager();
        credentialsManager.setLocalKey(username, null);
        stunStack.setCredentialsManager(credentialsManager);

        assertFalse(stunStack.validateMessageIntegrity(msgInt, username, shortTermCredentialMechanism, rawMessage));
    }

    @Test
    public void testValidateMessageIntegrityInvalidHmacSha1() {
        // Mock the necessary dependencies
        CredentialsManager credentialsManager = new CredentialsManager();
        byte[] key = new byte[32];
        new Random().nextBytes(key);
        credentialsManager.setLocalKey(username, key);
        stunStack.setCredentialsManager(credentialsManager);

        // Mock the HMAC-SHA1 calculation
        byte[] expectedHmacSha1 = new byte[20];
        new Random().nextBytes(expectedHmacSha1);
        msgInt.setHmacSha1Content(expectedHmacSha1);

        // Set up the raw message to mismatch the expected HMAC-SHA1
        byte[] binMsg = new byte[msgInt.getLocationInMessage()];
        System.arraycopy(rawMessage.getBytes(), 0, binMsg, 0, binMsg.length);
        char messageLength = (char) (binMsg.length + Attribute.HEADER_LENGTH + msgInt.getDataLength() - Message.HEADER_LENGTH);
        binMsg[2] = (byte) (messageLength >> 8);
        binMsg[3] = (byte) (messageLength & 0xFF);
        byte[] calculatedHmacSha1 = MessageIntegrityAttribute.calculateHmacSha1(binMsg, 0, binMsg.length, key);
        msgInt.setHmacSha1Content(Arrays.copyOf(calculatedHmacSha1, calculatedHmacSha1.length - 1));

        // Test the method
        assertFalse(stunStack.validateMessageIntegrity(msgInt, username, shortTermCredentialMechanism, rawMessage));
    }
}
