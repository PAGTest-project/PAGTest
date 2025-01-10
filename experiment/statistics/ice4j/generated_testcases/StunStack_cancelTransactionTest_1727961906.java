
package org.ice4j.stack;

import org.ice4j.TransactionID;
import org.ice4j.StunClientTransaction;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class StunStack_cancelTransactionTest {

    @Test
    public void testCancelTransaction_TransactionExists() {
        // Given
        StunStack stunStack = new StunStack();
        TransactionID transactionID = mock(TransactionID.class);
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
        TransactionID transactionID = mock(TransactionID.class);

        // When
        stunStack.cancelTransaction(transactionID);

        // Then
        // No exception should be thrown, and no transaction should be canceled
        verifyNoInteractions(transactionID);
    }
}
