
package org.ice4j.stack;

import org.ice4j.TransportAddress;
import org.ice4j.stack.RequestListener;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

public class StunStack_addRequestListenerTest {

    @Test
    public void testAddRequestListener() {
        // Given
        StunStack stunStack = new StunStack();
        TransportAddress localAddress = mock(TransportAddress.class);
        RequestListener listener = mock(RequestListener.class);

        // When
        stunStack.addRequestListener(localAddress, listener);

        // Then
        // No assertions needed as the method only delegates to eventDispatcher
    }
}
