
package org.ice4j;

import org.ice4j.message.Message;
import org.ice4j.stack.StunStack;
import org.ice4j.TransportAddress;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class StunFailureEvent_toStringTest {

    @Test
    public void testToString() {
        // Given
        StunStack stunStack = mock(StunStack.class);
        Message message = mock(Message.class);
        TransportAddress localAddress = mock(TransportAddress.class);
        Throwable cause = mock(Throwable.class);

        when(message.toString()).thenReturn("MockedMessage");
        when(localAddress.toString()).thenReturn("MockedLocalAddress");

        StunFailureEvent event = new StunFailureEvent(stunStack, message, localAddress, cause);

        // When
        String result = event.toString();

        // Then
        assertEquals("StunFailureEvent:\n\tMessage=MockedMessage localAddr=MockedLocalAddress", result);
    }
}
