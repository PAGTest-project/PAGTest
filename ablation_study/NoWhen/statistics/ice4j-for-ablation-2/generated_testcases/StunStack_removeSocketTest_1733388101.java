
package org.ice4j.stack;

import org.ice4j.TransportAddress;
import org.ice4j.socket.StunClientTransaction;
import org.ice4j.socket.StunServerTransaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Hashtable;

import static org.mockito.Mockito.*;

public class StunStack_removeSocketTest {

    private StunStack stunStack;
    private NetAccessManager netAccessManager;
    private Hashtable<TransactionID, StunClientTransaction> clientTransactions;
    private Hashtable<TransactionID, StunServerTransaction> serverTransactions;

    @BeforeEach
    public void setUp() {
        stunStack = new StunStack();
        netAccessManager = mock(NetAccessManager.class);
        clientTransactions = spy(new Hashtable<>());
        serverTransactions = spy(new Hashtable<>());

        stunStack.clientTransactions = clientTransactions;
        stunStack.serverTransactions = serverTransactions;
        stunStack.netAccessManager = netAccessManager;
    }

    @Test
    public void testRemoveSocket() {
        TransportAddress localAddr = new TransportAddress("127.0.0.1", 12345, TransportAddress.UDP_TRANSPORT);
        TransportAddress remoteAddr = new TransportAddress("127.0.0.1", 54321, TransportAddress.UDP_TRANSPORT);

        StunClientTransaction clientTransaction = mock(StunClientTransaction.class);
        StunServerTransaction serverTransaction = mock(StunServerTransaction.class);

        clientTransactions.put(TransactionID.createTransactionID(stunStack, new byte[16]), clientTransaction);
        serverTransactions.put(TransactionID.createTransactionID(stunStack, new byte[16]), serverTransaction);

        stunStack.removeSocket(localAddr, remoteAddr);

        verify(clientTransactions, times(1)).values();
        verify(serverTransactions, times(1)).values();
        verify(netAccessManager, times(1)).removeSocket(localAddr, remoteAddr);
    }
}
