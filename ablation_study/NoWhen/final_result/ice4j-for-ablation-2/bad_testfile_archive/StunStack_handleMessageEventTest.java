
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
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class StunStack_handleMessageEventTest {
    private StunStack stunStack;
    private TransportAddress clientAddress;
    private TransportAddress serverAddress;
    private Request bindingRequest;
    private Response bindingResponse;
    private RequestCollector requestCollector;
    private ResponseCollector responseCollector;

    @BeforeEach
    public void setUp() throws Exception {
        stunStack = new StunStack();

        clientAddress = new TransportAddress("127.0.0.1", 0, Transport.UDP);
        serverAddress = new TransportAddress("127.0.0.1", 0, Transport.UDP);

        bindingRequest = MessageFactory.createBindingRequest();
        bindingResponse = MessageFactory.create3489BindingResponse(
            clientAddress, clientAddress, serverAddress);

        requestCollector = new RequestCollector();
        responseCollector = new ResponseCollector() {
            @Override
            public void addResponse(ResponseEvent evt) {
                // Dummy implementation
            }
        };

        System.setProperty(
                StackProperties.PROPAGATE_RECEIVED_RETRANSMISSIONS,
                "false");
        System.setProperty(
                StackProperties.KEEP_CRANS_AFTER_A_RESPONSE,
                "false");
        System.setProperty(
                StackProperties.MAX_CTRAN_RETRANSMISSIONS,
                "");
        System.setProperty(
                StackProperties.MAX_CTRAN_RETRANS_TIMER,
                "");
        System.setProperty(
                StackProperties.FIRST_CTRAN_RETRANS_AFTER,
                "");
    }

    @Test
    public void testHandleMessageEventRequest() throws Exception {
        stunStack.addRequestListener(serverAddress, requestCollector);
        stunStack.sendRequest(bindingRequest, serverAddress, clientAddress, responseCollector);

        requestCollector.waitForRequest();

        StunMessageEvent evt = requestCollector.receivedRequests.get(0);
        byte[] tid = evt.getMessage().getTransactionID();
        stunStack.sendResponse(tid, bindingResponse, serverAddress, clientAddress);

        responseCollector.waitForResponse();

        assertEquals(1, responseCollector.receivedResponses.size(), "No response was received");
    }

    @Test
    public void testHandleMessageEventResponse() throws Exception {
        stunStack.addRequestListener(serverAddress, requestCollector);
        stunStack.sendRequest(bindingRequest, serverAddress, clientAddress, responseCollector);

        requestCollector.waitForRequest();

        StunMessageEvent evt = requestCollector.receivedRequests.get(0);
        byte[] tid = evt.getMessage().getTransactionID();
        stunStack.sendResponse(tid, bindingResponse, serverAddress, clientAddress);

        responseCollector.waitForResponse();

        assertEquals(1, responseCollector.receivedResponses.size(), "No response was received");
    }

    @Test
    public void testHandleMessageEventIndication() throws Exception {
        stunStack.addRequestListener(serverAddress, requestCollector);
        stunStack.sendRequest(bindingRequest, serverAddress, clientAddress, responseCollector);

        requestCollector.waitForRequest();

        StunMessageEvent evt = requestCollector.receivedRequests.get(0);
        byte[] tid = evt.getMessage().getTransactionID();
        stunStack.sendResponse(tid, bindingResponse, serverAddress, clientAddress);

        responseCollector.waitForResponse();

        assertEquals(1, responseCollector.receivedResponses.size(), "No response was received");
    }
}
