
package org.ice4j.stack;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StunStack_getPacketLoggerTest {

    @Test
    void testGetPacketLogger() {
        // Given
        PacketLogger mockPacketLogger = mock(PacketLogger.class);
        StunStack.setPacketLogger(mockPacketLogger);

        // When
        PacketLogger result = StunStack.getPacketLogger();

        // Then
        assertEquals(mockPacketLogger, result);
    }
}
