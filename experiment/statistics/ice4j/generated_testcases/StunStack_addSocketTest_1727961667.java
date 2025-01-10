
package org.ice4j.stack;

import org.ice4j.socket.IceSocketWrapper;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

public class StunStack_addSocketTest {

    @Test
    public void testAddSocket() {
        // Given
        StunStack stunStack = new StunStack();
        IceSocketWrapper mockSocket = mock(IceSocketWrapper.class);
        NetAccessManager mockNetAccessManager = mock(NetAccessManager.class);
        stunStack.netAccessManager = mockNetAccessManager;

        // When
        stunStack.addSocket(mockSocket);

        // Then
        verify(mockNetAccessManager).addSocket(mockSocket);
    }
}
