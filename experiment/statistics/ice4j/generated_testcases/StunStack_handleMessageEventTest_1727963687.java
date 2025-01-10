
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
        responseCollector = new ResponseCollector();
    }

    @Test
    public void testHandleMessageEventRequest() throws Exception {
        StunMessageEvent event = new StunMessageEvent(
            stunStack,
            new RawMessage(new byte[0], clientAddress, serverAddress, stunStack),
            bindingRequest
        );

        stunStack.handleMessageEvent(event);

        // Verify that the request was processed and a response was sent
        assertTrue(responseCollector.receivedResponses.size() > 0,
            "No response was sent for the request");
    }

    @Test
    public void testHandleMessageEventResponse() throws Exception {
        StunMessageEvent event = new StunMessageEvent(
            stunStack,
            new RawMessage(new byte[0], clientAddress, serverAddress, stunStack),
            bindingResponse
        );

        stunStack.handleMessageEvent(event);

        // Verify that the response was processed
        assertTrue(responseCollector.receivedResponses.size() > 0,
            "No response was processed");
    }

    @Test
    public void testHandleMessageEventIndication() throws Exception {
        Indication indication = MessageFactory.createBindingIndication();
        StunMessageEvent event = new StunMessageEvent(
            stunStack,
            new RawMessage(new byte[0], clientAddress, serverAddress, stunStack),
            indication
        );

        stunStack.handleMessageEvent(event);

        // Verify that the indication was processed
        assertTrue(responseCollector.receivedResponses.size() > 0,
            "No indication was processed");
    }

    private static class RequestCollector implements ResponseCollector {
        public List<Response> receivedResponses = new ArrayList<>();

        @Override
        public void processResponse(Response response, TransactionID id) {
            receivedResponses.add(response);
        }

        @Override
        public void processTimeout(TransactionID id) {
            // Do nothing
        }
    }

    private static class ResponseCollector implements ResponseCollector {
        public List<Response> receivedResponses = new ArrayList<>();

        @Override
        public void processResponse(Response response, TransactionID id) {
            receivedResponses.add(response);
        }

        @Override
        public void processTimeout(TransactionID id) {
            // Do nothing
        }
    }
}
