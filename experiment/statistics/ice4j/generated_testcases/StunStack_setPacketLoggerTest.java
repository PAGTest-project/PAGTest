
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

            @Override
            public void logPacket(byte[] fromAddress, int fromPort, byte[] toAddress, int toPort, byte[] packetData, boolean isSent) {
                // Do nothing
            }
        };

        // When
        StunStack.setPacketLogger(mockPacketLogger);

        // Then
        assertEquals(mockPacketLogger, StunStack.getPacketLogger());
        assertTrue(StunStack.isPacketLoggerEnabled());
    }
}
