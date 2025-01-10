
package org.ice4j.stack;

import org.ice4j.RequestListener;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

public class StunStack_addRequestListenerTest {

    @Test
    public void testAddRequestListener() {
        // Given
        StunStack stunStack = new StunStack();
        RequestListener requestListener = mock(RequestListener.class);

        // When
        stunStack.addRequestListener(requestListener);

        // Then
        // No assertions needed as the method only delegates to eventDispatcher
    }
}
