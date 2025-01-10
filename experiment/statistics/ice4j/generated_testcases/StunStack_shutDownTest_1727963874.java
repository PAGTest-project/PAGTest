
package org.ice4j.stack;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Hashtable;

import static org.mockito.Mockito.*;

public class StunStack_shutDownTest {

    @Test
    public void testShutDown() {
        // Given
        StunStack stunStack = new StunStack();
        stunStack.clientTransactions = new Hashtable<>();
        stunStack.serverTransactions = new Hashtable<>();

        StunClientTransaction clientTransaction = mock(StunClientTransaction.class);
        StunServerTransaction serverTransaction = mock(StunServerTransaction.class);

        stunStack.clientTransactions.put(mock(TransactionID.class), clientTransaction);
        stunStack.serverTransactions.put(mock(TransactionID.class), serverTransaction);

        stunStack.expiredTransactionsCollector = mock(ExpiredServerTransactionsCollector.class);
        stunStack.netAccessManager = mock(NetAccessManager.class);

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
