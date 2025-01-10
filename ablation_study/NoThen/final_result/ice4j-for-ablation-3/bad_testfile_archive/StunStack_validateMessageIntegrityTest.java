
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

        clientAddress = new TransportAddress("127.0.0.1", 5000, Transport.UDP);
        serverAddress = new TransportAddress("127.0.0.1", 5001, Transport.UDP);

        bindingRequest = MessageFactory.createBindingRequest();
        bindingResponse = MessageFactory.create3489BindingResponse(
            clientAddress, clientAddress, serverAddress);
    }

    @Test
    public void testValidateMessageIntegrityValid() {
        String username = "validUser:password";
        boolean shortTermCredentialMechanism = true;
        byte[] messageBytes = new byte[100];
        RawMessage message = new RawMessage(messageBytes, messageBytes.length, clientAddress, serverAddress);
        MessageIntegrityAttribute msgInt = createMessageIntegrityAttribute();

        assertTrue(stunStack.validateMessageIntegrity(msgInt, username, shortTermCredentialMechanism, message));
    }

    @Test
    public void testValidateMessageIntegrityInvalidUsername() {
        String username = "";
        boolean shortTermCredentialMechanism = true;
        byte[] messageBytes = new byte[100];
        RawMessage message = new RawMessage(messageBytes, messageBytes.length, clientAddress, serverAddress);
        MessageIntegrityAttribute msgInt = createMessageIntegrityAttribute();

        assertFalse(stunStack.validateMessageIntegrity(msgInt, username, shortTermCredentialMechanism, message));
    }

    @Test
    public void testValidateMessageIntegrityInvalidKey() {
        String username = "validUser:password";
        boolean shortTermCredentialMechanism = true;
        byte[] messageBytes = new byte[100];
        RawMessage message = new RawMessage(messageBytes, messageBytes.length, clientAddress, serverAddress);
        MessageIntegrityAttribute msgInt = createMessageIntegrityAttribute();

        // Mocking the CredentialsManager to return null key
        CredentialsManager mockCredentialsManager = new CredentialsManager() {
            @Override
            public byte[] getLocalKey(String username) {
                return null;
            }
        };
        stunStack = new StunStack() {
            @Override
            public CredentialsManager getCredentialsManager() {
                return mockCredentialsManager;
            }
        };

        assertFalse(stunStack.validateMessageIntegrity(msgInt, username, shortTermCredentialMechanism, message));
    }

    @Test
    public void testValidateMessageIntegrityInvalidHmacSha1() {
        String username = "validUser:password";
        boolean shortTermCredentialMechanism = true;
        byte[] messageBytes = new byte[100];
        RawMessage message = new RawMessage(messageBytes, messageBytes.length, clientAddress, serverAddress);
        MessageIntegrityAttribute msgInt = createMessageIntegrityAttribute();

        // Mocking the MessageIntegrityAttribute to return invalid HMAC-SHA1 content
        MessageIntegrityAttribute mockMsgInt = new MessageIntegrityAttribute() {
            @Override
            public byte[] getHmacSha1Content() {
                return new byte[0];
            }
        };

        assertFalse(stunStack.validateMessageIntegrity(mockMsgInt, username, shortTermCredentialMechanism, message));
    }

    private MessageIntegrityAttribute createMessageIntegrityAttribute() {
        try {
            return MessageIntegrityAttribute.class.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Failed to create MessageIntegrityAttribute", e);
        }
    }
}
