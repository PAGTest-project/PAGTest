
package org.ice4j.stack;

import org.ice4j.*;
import org.ice4j.message.*;
import org.ice4j.socket.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.net.InetSocketAddress;

public class StunStack_cancelTransactionTest {
    private StunStack stunStack;
    private TransportAddress clientAddress;
    private TransportAddress serverAddress;
    private Request bindingRequest;
    private Response bindingResponse;
    private ResponseCollector requestCollector;
    private ResponseCollector responseCollector;

    @BeforeEach
    public void setUp() throws Exception {
        stunStack = new StunStack();

        IceUdpSocketWrapper clientSock = new IceUdpSocketWrapper(
            new SafeCloseDatagramSocket(new InetSocketAddress("127.0.0.1", 0)));
        IceUdpSocketWrapper serverSock = new IceUdpSocketWrapper(
            new SafeCloseDatagramSocket(new InetSocketAddress("127.0.0.1", 0)));

        clientAddress = new TransportAddress("127.0.0.1", clientSock.getLocalPort(), Transport.UDP);
        serverAddress = new TransportAddress("127.0.0.1", serverSock.getLocalPort(), Transport.UDP);

        stunStack.addSocket(clientSock);
        stunStack.addSocket(serverSock);

        bindingRequest = MessageFactory.createBindingRequest();
        bindingResponse = MessageFactory.create3489BindingResponse(
            clientAddress, clientAddress, serverAddress);

        requestCollector = new ResponseCollector() {
            @Override
            public void processResponse(ResponseEvent responseEvent) {
                // Do nothing
            }

            @Override
            public void processTimeout(StunTimeoutEvent timeoutEvent) {
                // Do nothing
            }
        };
        responseCollector = new ResponseCollector() {
            @Override
            public void processResponse(ResponseEvent responseEvent) {
                // Do nothing
            }

            @Override
            public void processTimeout(StunTimeoutEvent timeoutEvent) {
                // Do nothing
            }
        };
    }

    @Test
    public void testCancelTransaction() throws Exception {
        // Send a request to create a transaction
        TransactionID transactionID = stunStack.sendRequest(
            bindingRequest, serverAddress, clientAddress, responseCollector);

        // Ensure the transaction is created
        StunClientTransaction clientTransaction = stunStack.getClientTransaction(transactionID.getBytes());
        assertNotNull(clientTransaction, "Transaction should be created");

        // Cancel the transaction
        stunStack.cancelTransaction(transactionID);

        // Ensure the transaction is canceled
        clientTransaction = stunStack.getClientTransaction(transactionID.getBytes());
        assertNull(clientTransaction, "Transaction should be canceled");
    }

    @Test
    public void testCancelNonExistentTransaction() {
        // Create a non-existent transaction ID
        TransactionID nonExistentTransactionID = TransactionID.createNewTransactionID();

        // Cancel the non-existent transaction
        stunStack.cancelTransaction(nonExistentTransactionID);

        // Ensure no exception is thrown and no transaction is canceled
        StunClientTransaction clientTransaction = stunStack.getClientTransaction(nonExistentTransactionID.getBytes());
        assertNull(clientTransaction, "No transaction should be canceled");
    }
}
