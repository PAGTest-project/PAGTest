
package org.ice4j.stack;

import org.ice4j.*;
import org.ice4j.attribute.*;
import org.ice4j.message.*;
import org.ice4j.security.*;
import org.ice4j.socket.*;
import org.jitsi.utils.concurrent.*;
import javax.crypto.*;
import java.io.*;
import java.net.*;
import java.security.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.logging.*;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

public class StunStack_shutDownTest {

    private StunStack stunStack;
    private EventDispatcher eventDispatcher;
    private NetAccessManager netAccessManager;
    private ExpiredServerTransactionsCollector expiredTransactionsCollector;
    private Hashtable<TransactionID, StunClientTransaction> clientTransactions;
    private Hashtable<TransactionID, StunServerTransaction> serverTransactions;
    private StunClientTransaction mockClientTransaction;
    private StunServerTransaction mockServerTransaction;

    @BeforeEach
    public void setUp() {
        eventDispatcher = mock(EventDispatcher.class);
        netAccessManager = mock(NetAccessManager.class);
        expiredTransactionsCollector = mock(ExpiredServerTransactionsCollector.class);
        clientTransactions = new Hashtable<>();
        serverTransactions = new Hashtable<>();
        mockClientTransaction = mock(StunClientTransaction.class);
        mockServerTransaction = mock(StunServerTransaction.class);

        stunStack = new StunStack() {
            @Override
            EventDispatcher getEventDispatcher() {
                return eventDispatcher;
            }

            @Override
            NetAccessManager getNetAccessManager() {
                return netAccessManager;
            }

            @Override
            ExpiredServerTransactionsCollector getExpiredTransactionsCollector() {
                return expiredTransactionsCollector;
            }

            @Override
            Hashtable<TransactionID, StunClientTransaction> getClientTransactions() {
                return clientTransactions;
            }

            @Override
            Hashtable<TransactionID, StunServerTransaction> getServerTransactions() {
                return serverTransactions;
            }
        };

        clientTransactions.put(mock(TransactionID.class), mockClientTransaction);
        serverTransactions.put(mock(TransactionID.class), mockServerTransaction);
    }

    @Test
    public void testShutDown() {
        stunStack.shutDown();

        verify(eventDispatcher).removeAllListeners();
        verify(expiredTransactionsCollector).cancel();
        verify(mockClientTransaction).cancel();
        verify(mockServerTransaction).expire();
        verify(netAccessManager).stop();

        assertTrue(clientTransactions.isEmpty());
        assertTrue(serverTransactions.isEmpty());
    }
}
