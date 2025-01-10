
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

public class StunStack_sendRequestTest {
    private StunStack stunStack;
    private TransportAddress clientAddress;
    private TransportAddress serverAddress;
    private Request bindingRequest;
    private Response bindingResponse;
    private PlainRequestCollector requestCollector;
    private PlainResponseCollector responseCollector;

    @BeforeEach
    public void setUp() throws Exception {
        stunStack = new StunStack();

        clientAddress = new TransportAddress("127.0.0.1", 0, Transport.UDP);
        serverAddress = new TransportAddress("127.0.0.1", 0, Transport.UDP);

        IceUdpSocketWrapper clientSock = new IceUdpSocketWrapper(
            new SafeCloseDatagramSocket(clientAddress));
        IceUdpSocketWrapper serverSock = new IceUdpSocketWrapper(
            new SafeCloseDatagramSocket(serverAddress));

        stunStack.addSocket(clientSock);
        stunStack.addSocket(serverSock);

        bindingRequest = MessageFactory.createBindingRequest();
        bindingResponse = MessageFactory.create3489BindingResponse(
            clientAddress, clientAddress, serverAddress);

        requestCollector = new PlainRequestCollector();
        responseCollector = new PlainResponseCollector();
    }

    @Test
    public void testSendRequestSuccess() throws Exception {
        stunStack.addRequestListener(serverAddress, requestCollector);
        TransactionID transactionID = stunStack.sendRequest(
            bindingRequest, serverAddress, clientAddress, responseCollector);

        assertNotNull(transactionID, "TransactionID should not be null");

        requestCollector.waitForRequest();

        StunMessageEvent evt = requestCollector.receivedRequests.get(0);
        byte[] tid = evt.getMessage().getTransactionID();
        stunStack.sendResponse(tid, bindingResponse, serverAddress, clientAddress);

        responseCollector.waitForResponse();

        assertEquals(1, responseCollector.receivedResponses.size(),
            "There should be one response received");
    }

    @Test
    public void testSendRequestIOException() {
        // Simulate an IOException by providing an invalid address
        TransportAddress invalidAddress = new TransportAddress("invalid.address", 0, Transport.UDP);

        assertThrows(IOException.class, () -> {
            stunStack.sendRequest(bindingRequest, invalidAddress, clientAddress, responseCollector);
        }, "IOException should be thrown for invalid address");
    }

    @Test
    public void testSendRequestIllegalArgumentException() {
        // Simulate an IllegalArgumentException by providing a null request
        assertThrows(IllegalArgumentException.class, () -> {
            stunStack.sendRequest(null, serverAddress, clientAddress, responseCollector);
        }, "IllegalArgumentException should be thrown for null request");
    }
}
