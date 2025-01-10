
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
    private TransportAddress clientAddress;
    private TransportAddress serverAddress;
    private Request bindingRequest;
    private Response bindingResponse;

    @BeforeEach
    public void setUp() throws Exception {
        stunStack = new StunStack();

        clientAddress = new TransportAddress("127.0.0.1", 0, Transport.UDP);
        serverAddress = new TransportAddress("127.0.0.1", 0, Transport.UDP);

        bindingRequest = MessageFactory.createBindingRequest();
        bindingResponse = MessageFactory.create3489BindingResponse(
            clientAddress, clientAddress, serverAddress);
    }

    @Test
    public void testValidateMessageIntegrityValid() {
        String username = "validUser:validFrag";
        boolean shortTermCredentialMechanism = true;
        byte[] messageBytes = new byte[100];
        RawMessage message = new RawMessage(messageBytes, messageBytes.length, serverAddress, clientAddress);
        MessageIntegrityAttribute msgInt = new MessageIntegrityAttribute();
        msgInt.setHmacSha1Content(new byte[20]);

        assertTrue(stunStack.validateMessageIntegrity(msgInt, username, shortTermCredentialMechanism, message));
    }

    @Test
    public void testValidateMessageIntegrityInvalidUsername() {
        String username = "";
        boolean shortTermCredentialMechanism = true;
        byte[] messageBytes = new byte[100];
        RawMessage message = new RawMessage(messageBytes, messageBytes.length, serverAddress, clientAddress);
        MessageIntegrityAttribute msgInt = new MessageIntegrityAttribute();
        msgInt.setHmacSha1Content(new byte[20]);

        assertFalse(stunStack.validateMessageIntegrity(msgInt, username, shortTermCredentialMechanism, message));
    }

    @Test
    public void testValidateMessageIntegrityInvalidKey() {
        String username = "validUser:validFrag";
        boolean shortTermCredentialMechanism = true;
        byte[] messageBytes = new byte[100];
        RawMessage message = new RawMessage(messageBytes, messageBytes.length, serverAddress, clientAddress);
        MessageIntegrityAttribute msgInt = new MessageIntegrityAttribute();
        msgInt.setHmacSha1Content(new byte[20]);

        // Mocking the local key to return null
        CredentialsManager credentialsManager = new CredentialsManager() {
            @Override
            public byte[] getLocalKey(String username) {
                return null;
            }
        };
        stunStack.setCredentialsManager(credentialsManager);

        assertFalse(stunStack.validateMessageIntegrity(msgInt, username, shortTermCredentialMechanism, message));
    }

    @Test
    public void testValidateMessageIntegrityInvalidHmacSha1() {
        String username = "validUser:validFrag";
        boolean shortTermCredentialMechanism = true;
        byte[] messageBytes = new byte[100];
        RawMessage message = new RawMessage(messageBytes, messageBytes.length, serverAddress, clientAddress);
        MessageIntegrityAttribute msgInt = new MessageIntegrityAttribute();

        // Mocking the HMAC-SHA1 calculation to return different values
        byte[] expectedHmacSha1Content = new byte[20];
        byte[] msgIntHmacSha1Content = new byte[20];
        Arrays.fill(expectedHmacSha1Content, (byte) 0xAA);
        Arrays.fill(msgIntHmacSha1Content, (byte) 0xBB);

        msgInt.setHmacSha1Content(msgIntHmacSha1Content);

        assertFalse(stunStack.validateMessageIntegrity(msgInt, username, shortTermCredentialMechanism, message));
    }
}
