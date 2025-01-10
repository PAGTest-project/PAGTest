
package org.ice4j.stack;

import org.ice4j.*;
import org.ice4j.message.*;
import org.ice4j.socket.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class StunStack_cancelTransactionTest {
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
    public void testCancelTransaction() throws Exception {
        // Given
        stunStack.addRequestListener(serverAddress, requestCollector);
        stunStack.sendRequest(bindingRequest, serverAddress, clientAddress, responseCollector);
        requestCollector.waitForRequest();
        StunMessageEvent evt = requestCollector.receivedRequests.get(0);
        TransactionID tid = evt.getTransactionID();

        // When
        stunStack.cancelTransaction(tid);

        // Then
        assertNull(stunStack.getClientTransaction(tid.getBytes()), "Transaction was not canceled");
    }

    @Test
    public void testCancelNonExistentTransaction() {
        // Given
        TransactionID nonExistentTid = TransactionID.createNewTransactionID();

        // When
        stunStack.cancelTransaction(nonExistentTid);

        // Then
        assertNull(stunStack.getClientTransaction(nonExistentTid.getBytes()), "Non-existent transaction was canceled");
    }
}
