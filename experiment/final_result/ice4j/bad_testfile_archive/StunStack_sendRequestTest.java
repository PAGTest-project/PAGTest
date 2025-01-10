
package org.ice4j.stack;

import org.ice4j.*;
import org.ice4j.message.*;
import org.ice4j.socket.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class StunStack_sendRequestTest {

    private StunStack stunStack;
    private TransportAddress clientAddress;
    private TransportAddress serverAddress;
    private Request bindingRequest;
    private Response bindingResponse;
    private RequestCollector requestCollector;
    private ResponseCollector responseCollector;

    @BeforeEach
    public void setUp() throws Exception {
        DatagramSocket clientSock = new IceUdpSocketWrapper(
                new SafeCloseDatagramSocket(new InetSocketAddress("127.0.0.1", 0)));
        DatagramSocket serverSock = new IceUdpSocketWrapper(
                new SafeCloseDatagramSocket(new InetSocketAddress("127.0.0.1", 0)));

        clientAddress = new TransportAddress("127.0.0.1", clientSock.getLocalPort(), Transport.UDP);
        serverAddress = new TransportAddress("127.0.0.1", serverSock.getLocalPort(), Transport.UDP);

        stunStack = new StunStack();
        stunStack.addSocket(new IceUdpSocketWrapper(clientSock));
        stunStack.addSocket(new IceUdpSocketWrapper(serverSock));

        bindingRequest = MessageFactory.createBindingRequest();
        bindingResponse = MessageFactory.create3489BindingResponse(
                clientAddress, clientAddress, serverAddress);

        requestCollector = new RequestCollector();
        responseCollector = new ResponseCollector() {
            @Override
            public void processResponse(ResponseEvent evt) {
                // Override the abstract method to avoid instantiation error
            }
        };
    }

    @Test
    public void testSendRequestSuccess() throws IOException {
        stunStack.addRequestListener(serverAddress, requestCollector);
        TransactionID transactionID = stunStack.sendRequest(bindingRequest, serverAddress, clientAddress, responseCollector);

        assertNotNull(transactionID, "TransactionID should not be null");
    }

    @Test
    public void testSendRequestIOException() {
        // Simulate an IOException by providing an invalid server address
        TransportAddress invalidServerAddress = new TransportAddress("255.255.255.255", 12345, Transport.UDP);

        assertThrows(IOException.class, () -> {
            stunStack.sendRequest(bindingRequest, invalidServerAddress, clientAddress, responseCollector);
        }, "Expected IOException to be thrown");
    }

    @Test
    public void testSendRequestIllegalArgumentException() {
        // Simulate an IllegalArgumentException by providing a null request
        assertThrows(IllegalArgumentException.class, () -> {
            stunStack.sendRequest(null, serverAddress, clientAddress, responseCollector);
        }, "Expected IllegalArgumentException to be thrown");
    }
}
