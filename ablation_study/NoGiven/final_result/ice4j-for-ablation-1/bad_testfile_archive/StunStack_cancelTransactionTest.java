
package org.ice4j.stack;

import org.ice4j.TransactionID;
import org.ice4j.StunClientTransaction;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

public class StunStack_cancelTransactionTest {

    @Test
    public void testCancelTransaction_TransactionExists() {
        // Given
        StunStack stunStack = new StunStack();
        TransactionID transactionID = TransactionID.createNewTransactionID();
        StunClientTransaction clientTransaction = mock(StunClientTransaction.class);
        stunStack.clientTransactions.put(transactionID, clientTransaction);

        // When
        stunStack.cancelTransaction(transactionID);

        // Then
        verify(clientTransaction).cancel();
    }

    @Test
    public void testCancelTransaction_TransactionDoesNotExist() {
        // Given
        StunStack stunStack = new StunStack();
        TransactionID transactionID = TransactionID.createNewTransactionID();

        // When
        stunStack.cancelTransaction(transactionID);

        // Then
        // No exception is thrown, and no action is taken
    }
}
