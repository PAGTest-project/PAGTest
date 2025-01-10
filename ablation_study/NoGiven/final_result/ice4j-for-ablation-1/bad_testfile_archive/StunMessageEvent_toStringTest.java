
package org.ice4j;

import org.ice4j.message.*;
import org.ice4j.stack.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class StunMessageEvent_toStringTest {

    @Test
    public void testToString() {
        // Given
        StunStack stunStack = mock(StunStack.class);
        TransportAddress localAddress = mock(TransportAddress.class);
        TransportAddress remoteAddress = mock(TransportAddress.class);
        Message parsedMessage = mock(Message.class);
        RawMessage rawMessage = mock(RawMessage.class);

        when(rawMessage.getLocalAddress()).thenReturn(localAddress);
        when(rawMessage.getRemoteAddress()).thenReturn(remoteAddress);
        when(parsedMessage.toString()).thenReturn("MockedMessage");
        when(localAddress.toString()).thenReturn("127.0.0.1:1234");
        when(remoteAddress.toString()).thenReturn("192.168.1.1:5678");

        StunMessageEvent event = new StunMessageEvent(stunStack, rawMessage, parsedMessage);

        // When
        String result = event.toString();

        // Then
        String expected = "StunMessageEvent:\n\tMessage=MockedMessage remoteAddr=192.168.1.1:5678 localAddr=127.0.0.1:1234";
        assertEquals(expected, result);
    }
}
