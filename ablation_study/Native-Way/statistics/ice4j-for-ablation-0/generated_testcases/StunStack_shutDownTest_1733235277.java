
package org.ice4j.stack;

import org.ice4j.*;
import org.ice4j.message.*;
import org.ice4j.socket.*;
import org.junit.jupiter.api.*;
import java.net.*;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class StunStack_shutDownTest {
    private StunStack stunStack;
    private TransportAddress clientAddress;
    private TransportAddress serverAddress;
    private IceSocketWrapper clientSock;
    private IceSocketWrapper serverSock;
    private Request bindingRequest;
    private Response bindingResponse;
    private PlainRequestCollector requestCollector;
    private PlainResponseCollector responseCollector;

    @BeforeEach
    public void setUp() throws Exception {
        clientSock = new IceUdpSocketWrapper(
            new SafeCloseDatagramSocket(new InetSocketAddress("127.0.0.1", 0)));
        serverSock = new IceUdpSocketWrapper(
            new SafeCloseDatagramSocket(new InetSocketAddress("127.0.0.1", 0)));

        clientAddress = new TransportAddress("127.0.0.1", clientSock.getLocalPort(), Transport.UDP);
        serverAddress = new TransportAddress("127.0.0.1", serverSock.getLocalPort(), Transport.UDP);

        stunStack = new StunStack();
        stunStack.addSocket(clientSock);
        stunStack.addSocket(serverSock);

        bindingRequest = MessageFactory.createBindingRequest();
        bindingResponse = MessageFactory.create3489BindingResponse(
            clientAddress, clientAddress, serverAddress);

        requestCollector = new PlainRequestCollector();
        responseCollector = new PlainResponseCollector();

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
    public void testShutDown() throws Exception {
        // Prepare to listen
        stunStack.addRequestListener(serverAddress, requestCollector);
        // Send a request to populate clientTransactions and serverTransactions
        stunStack.sendRequest(bindingRequest, serverAddress, clientAddress, responseCollector);
        // Wait for the message to arrive
        requestCollector.waitForRequest();

        // Call shutDown method
        stunStack.shutDown();

        // Verify that clientTransactions and serverTransactions are cleared
        synchronized (stunStack.clientTransactions) {
            assertTrue(stunStack.clientTransactions.isEmpty());
        }
        synchronized (stunStack.serverTransactions) {
            assertTrue(stunStack.serverTransactions.isEmpty());
        }

        // Verify that netAccessManager is stopped
        assertFalse(stunStack.netAccessManager.isRunning());
    }
}
