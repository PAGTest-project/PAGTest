
package org.ice4j.stack;

import org.ice4j.*;
import org.ice4j.attribute.UnknownAttribute;
import org.ice4j.message.*;
import org.ice4j.socket.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.net.InetSocketAddress;

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

        IceSocketWrapper clientSock = new IceUdpSocketWrapper(
            new SafeCloseDatagramSocket(new InetSocketAddress("127.0.0.1", 0)));
        IceSocketWrapper serverSock = new IceUdpSocketWrapper(
            new SafeCloseDatagramSocket(new InetSocketAddress("127.0.0.1", 0)));

        clientAddress = new TransportAddress("127.0.0.1", clientSock.getLocalPort(), Transport.UDP);
        serverAddress = new TransportAddress("127.0.0.1", serverSock.getLocalPort(), Transport.UDP);

        stunStack.addSocket(clientSock, clientAddress);
        stunStack.addSocket(serverSock, serverAddress);

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
    public void testSendRequestFailure() throws Exception {
        stunStack.addRequestListener(serverAddress, requestCollector);

        Request invalidRequest = MessageFactory.createBindingRequest();
        invalidRequest.addAttribute(new UnknownAttribute(0xFFFF));

        assertThrows(IllegalArgumentException.class, () -> {
            stunStack.sendRequest(invalidRequest, serverAddress, clientAddress, responseCollector);
        }, "IllegalArgumentException should be thrown for invalid request");
    }
}
