
package org.ice4j;

import org.ice4j.message.Message;
import org.ice4j.stack.StunStack;
import org.ice4j.stack.TransportAddress;
import org.ice4j.stack.TransactionID;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class StunTimeoutEvent_toStringTest {

    @Test
    public void testToString() {
        // Given
        StunStack stunStack = mock(StunStack.class);
        Message message = mock(Message.class);
        TransportAddress localAddress = mock(TransportAddress.class);
        TransactionID transactionID = mock(TransactionID.class);

        when(message.toString()).thenReturn("MockedMessage");
        when(localAddress.toString()).thenReturn("MockedLocalAddress");

        StunTimeoutEvent event = new StunTimeoutEvent(stunStack, message, localAddress, transactionID);

        // When
        String result = event.toString();

        // Then
        assertEquals("StunTimeoutEvent:\n\tMessage=MockedMessage localAddr=MockedLocalAddress", result);
    }
}
