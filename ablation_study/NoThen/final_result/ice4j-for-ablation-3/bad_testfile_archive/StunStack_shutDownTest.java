
package org.ice4j.stack;

import org.ice4j.StunClientTransaction;
import org.ice4j.StunServerTransaction;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;

import static org.mockito.Mockito.*;

public class StunStack_shutDownTest {

    @Test
    public void testShutDown() {
        // Given
        StunStack stunStack = new StunStack();
        stunStack.eventDispatcher = mock(EventDispatcher.class);
        stunStack.netAccessManager = mock(NetAccessManager.class);
        stunStack.expiredTransactionsCollector = mock(StunStack.ExpiredServerTransactionsCollector.class);

        Hashtable<TransactionID, StunClientTransaction> clientTransactions = new Hashtable<>();
        StunClientTransaction clientTransaction = mock(StunClientTransaction.class);
        clientTransactions.put(mock(TransactionID.class), clientTransaction);
        stunStack.clientTransactions = clientTransactions;

        Hashtable<TransactionID, StunServerTransaction> serverTransactions = new Hashtable<>();
        StunServerTransaction serverTransaction = mock(StunServerTransaction.class);
        serverTransactions.put(mock(TransactionID.class), serverTransaction);
        stunStack.serverTransactions = serverTransactions;

        // When
        stunStack.shutDown();

        // Then
        verify(stunStack.eventDispatcher).removeAllListeners();
        verify(clientTransaction).cancel();
        verify(serverTransaction).expire();
        verify(stunStack.expiredTransactionsCollector).cancel();
        verify(stunStack.netAccessManager).stop();
    }
}
