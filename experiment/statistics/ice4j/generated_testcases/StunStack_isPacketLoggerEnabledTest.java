
package org.ice4j.stack;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StunStack_isPacketLoggerEnabledTest {

    @Test
    void testIsPacketLoggerEnabled_LoggerEnabled() {
        // Given
        PacketLogger mockPacketLogger = mock(PacketLogger.class);
        when(mockPacketLogger.isEnabled()).thenReturn(true);
        StunStack.setPacketLogger(mockPacketLogger);

        // When
        boolean result = StunStack.isPacketLoggerEnabled();

        // Then
        assertTrue(result);
    }

    @Test
    void testIsPacketLoggerEnabled_LoggerDisabled() {
        // Given
        PacketLogger mockPacketLogger = mock(PacketLogger.class);
        when(mockPacketLogger.isEnabled()).thenReturn(false);
        StunStack.setPacketLogger(mockPacketLogger);

        // When
        boolean result = StunStack.isPacketLoggerEnabled();

        // Then
        assertFalse(result);
    }

    @Test
    void testIsPacketLoggerEnabled_LoggerNull() {
        // Given
        StunStack.setPacketLogger(null);

        // When
        boolean result = StunStack.isPacketLoggerEnabled();

        // Then
        assertFalse(result);
    }
}
