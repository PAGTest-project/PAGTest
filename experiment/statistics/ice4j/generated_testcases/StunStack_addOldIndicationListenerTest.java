
package org.ice4j.stack;

import org.ice4j.TransportAddress;
import org.ice4j.message.MessageEventHandler;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

public class StunStack_addOldIndicationListenerTest {

    @Test
    public void testAddOldIndicationListener() {
        // Given
        StunStack stunStack = new StunStack();
        TransportAddress localAddr = mock(TransportAddress.class);
        MessageEventHandler indicationListener = mock(MessageEventHandler.class);

        // When
        stunStack.addOldIndicationListener(localAddr, indicationListener);

        // Then
        // No assertions needed as the method only delegates to eventDispatcher
    }
}
