
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

        clientAddress = new TransportAddress("127.0.0.1", 5000, Transport.UDP);
        serverAddress = new TransportAddress("127.0.0.1", 5001, Transport.UDP);

        bindingRequest = MessageFactory.createBindingRequest();
        bindingResponse = MessageFactory.create3489BindingResponse(
            clientAddress, clientAddress, serverAddress);

        requestCollector = new RequestCollector();
        responseCollector = new ResponseCollector();
    }

    @Test
    public void testHandleMessageEventRequest() throws Exception {
        // Prepare to listen
        stunStack.addRequestListener(serverAddress, requestCollector);

        // Send request
        stunStack.sendRequest(bindingRequest, serverAddress, clientAddress, responseCollector);

        // Wait for the message to arrive
        requestCollector.waitForRequest();

        StunMessageEvent evt = requestCollector.receivedRequests.get(0);
        byte[] tid = evt.getMessage().getTransactionID();

        // Handle the message event
        stunStack.handleMessageEvent(evt);

        // Verify that the response was retransmitted
        responseCollector.waitForResponse();
        assertEquals(1, responseCollector.receivedResponses.size(), "There were no retransmissions of a binding response");
    }

    @Test
    public void testHandleMessageEventResponse() throws Exception {
        // Prepare to listen
        stunStack.addRequestListener(serverAddress, requestCollector);

        // Send request
        stunStack.sendRequest(bindingRequest, serverAddress, clientAddress, responseCollector);

        // Wait for the message to arrive
        requestCollector.waitForRequest();

        StunMessageEvent evt = requestCollector.receivedRequests.get(0);
        byte[] tid = evt.getMessage().getTransactionID();

        // Send response
        stunStack.sendResponse(tid, bindingResponse, serverAddress, clientAddress);

        // Handle the message event
        stunStack.handleMessageEvent(evt);

        // Verify that the response was handled correctly
        responseCollector.waitForResponse();
        assertEquals(1, responseCollector.receivedResponses.size(), "The response was not handled correctly");
    }

    @Test
    public void testHandleMessageEventIndication() throws Exception {
        // Prepare to listen
        stunStack.addRequestListener(serverAddress, requestCollector);

        // Send request
        stunStack.sendRequest(bindingRequest, serverAddress, clientAddress, responseCollector);

        // Wait for the message to arrive
        requestCollector.waitForRequest();

        StunMessageEvent evt = requestCollector.receivedRequests.get(0);
        byte[] tid = evt.getMessage().getTransactionID();

        // Create an indication event
        StunMessageEvent indicationEvent = new StunMessageEvent(stunStack, new RawMessage(new byte[0], 0, serverAddress, clientAddress), new Indication());

        // Handle the indication event
        stunStack.handleMessageEvent(indicationEvent);

        // Verify that the indication was handled correctly
        responseCollector.waitForResponse();
        assertEquals(0, responseCollector.receivedResponses.size(), "The indication was not handled correctly");
    }
}
