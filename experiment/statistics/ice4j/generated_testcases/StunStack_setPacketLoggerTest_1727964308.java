
package org.ice4j.stack;

import org.ice4j.PacketLogger;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StunStack_setPacketLoggerTest {

    @Test
    public void testSetPacketLogger() {
        // Given
        PacketLogger mockPacketLogger = new PacketLogger() {
            @Override
            public boolean isEnabled() {
                return true;
            }
        };

        // When
        StunStack.setPacketLogger(mockPacketLogger);

        // Then
        assertEquals(mockPacketLogger, StunStack.getPacketLogger());
        assertTrue(StunStack.isPacketLoggerEnabled());
    }
}
