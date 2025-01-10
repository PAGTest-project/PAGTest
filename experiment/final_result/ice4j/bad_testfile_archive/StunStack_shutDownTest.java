
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
        StunStack stunStack = Mockito.spy(new StunStack());
        doReturn(new Hashtable<>()).when(stunStack).clientTransactions;
        doReturn(new Hashtable<>()).when(stunStack).serverTransactions;

        StunClientTransaction clientTransaction = mock(StunClientTransaction.class);
        StunServerTransaction serverTransaction = mock(StunServerTransaction.class);

        stunStack.clientTransactions.put(mock(TransactionID.class), clientTransaction);
        stunStack.serverTransactions.put(mock(TransactionID.class), serverTransaction);

        ExpiredServerTransactionsCollector expiredTransactionsCollector = mock(ExpiredServerTransactionsCollector.class);
        NetAccessManager netAccessManager = mock(NetAccessManager.class);

        doReturn(expiredTransactionsCollector).when(stunStack).expiredTransactionsCollector;
        doReturn(netAccessManager).when(stunStack).netAccessManager;

        // When
        stunStack.shutDown();

        // Then
        verify(stunStack.eventDispatcher).removeAllListeners();
        verify(clientTransaction).cancel();
        verify(serverTransaction).expire();
        verify(expiredTransactionsCollector).cancel();
        verify(netAccessManager).stop();
    }
}
