
package org.ice4j.stack;

import org.ice4j.*;
import org.ice4j.message.*;
import org.ice4j.socket.*;
import org.junit.jupiter.api.*;
import java.io.IOException;
import java.net.*;
import java.util.*;
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
                bindingRequest,
                serverAddress,
                clientAddress,
                responseCollector);

        assertNotNull(transactionID, "TransactionID should not be null");

        requestCollector.waitForRequest();

        Vector<StunMessageEvent> reqs = requestCollector.getRequestsForTransaction(transactionID);
        assertFalse(reqs.isEmpty(), "No request received");
    }

    @Test
    public void testSendRequestIOException() {
        // Simulate an IOException by providing an invalid address
        TransportAddress invalidAddress = new TransportAddress("invalid.address", 0, Transport.UDP);

        assertThrows(IOException.class, () -> {
            stunStack.sendRequest(
                    bindingRequest,
                    invalidAddress,
                    clientAddress,
                    responseCollector);
        }, "Expected IOException to be thrown");
    }

    @Test
    public void testSendRequestIllegalArgumentException() {
        // Simulate an IllegalArgumentException by providing null parameters
        assertThrows(IllegalArgumentException.class, () -> {
            stunStack.sendRequest(
                    null,
                    serverAddress,
                    clientAddress,
                    responseCollector);
        }, "Expected IllegalArgumentException to be thrown");
    }
}
