
package org.ice4j;

import org.ice4j.message.Message;
import org.ice4j.stack.StunStack;
import org.ice4j.stack.TransactionID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BaseStunMessageEvent_getTransactionIDTest {

    private BaseStunMessageEvent event;
    private StunStack mockStunStack;
    private Message mockMessage;
    private TransactionID mockTransactionID;

    @BeforeEach
    public void setUp() {
        mockStunStack = mock(StunStack.class);
        mockMessage = mock(Message.class);
        mockTransactionID = mock(TransactionID.class);
        event = new BaseStunMessageEvent(mockStunStack, null, mockMessage);
    }

    @Test
    public void testGetTransactionID_InitialNull() {
        // Given
        when(mockMessage.getTransactionID()).thenReturn(mockTransactionID);
        when(TransactionID.createTransactionID(eq(mockStunStack), eq(mockTransactionID))).thenReturn(mockTransactionID);

        // When
        TransactionID result = event.getTransactionID();

        // Then
        assertNotNull(result);
        assertEquals(mockTransactionID, result);
    }

    @Test
    public void testGetTransactionID_AlreadySet() {
        // Given
        event.setTransactionID(mockTransactionID);

        // When
        TransactionID result = event.getTransactionID();

        // Then
        assertNotNull(result);
        assertEquals(mockTransactionID, result);
    }
}
