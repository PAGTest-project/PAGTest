
package org.ice4j.stack;

import org.ice4j.*;
import org.ice4j.message.*;
import org.ice4j.socket.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.net.*;

public class StunStack_logPacketToPcapTest {

    private StunStack stunStack;
    private DatagramPacket datagramPacket;
    private InetAddress interfaceAddress;
    private int interfacePort;

    @BeforeEach
    public void setUp() throws Exception {
        stunStack = new StunStack();
        datagramPacket = new DatagramPacket(new byte[1024], 1024);
        interfaceAddress = InetAddress.getByName("127.0.0.1");
        interfacePort = 12345;
    }

    @Test
    public void testLogPacketToPcapEnabled() throws Exception {
        // Given
        PacketLogger mockPacketLogger = new PacketLogger() {
            @Override
            public void logPacket(byte[] fromAddress, int fromPort, byte[] toAddress, int toPort, byte[] data, boolean isSent) {
                // Mock implementation
            }

            @Override
            public boolean isEnabled() {
                return true;
            }
        };
        StunStack.packetLogger = mockPacketLogger;

        // When
        StunStack.logPacketToPcap(datagramPacket, true, interfaceAddress, interfacePort);

        // Then
        // No exception means the packet was logged successfully
    }

    @Test
    public void testLogPacketToPcapDisabled() throws Exception {
        // Given
        PacketLogger mockPacketLogger = new PacketLogger() {
            @Override
            public void logPacket(byte[] fromAddress, int fromPort, byte[] toAddress, int toPort, byte[] data, boolean isSent) {
                // Mock implementation
            }

            @Override
            public boolean isEnabled() {
                return false;
            }
        };
        StunStack.packetLogger = mockPacketLogger;

        // When
        StunStack.logPacketToPcap(datagramPacket, true, interfaceAddress, interfacePort);

        // Then
        // No exception means the packet logging was skipped as expected
    }

    @Test
    public void testLogPacketToPcapNullInterfaceAddress() throws Exception {
        // Given
        PacketLogger mockPacketLogger = new PacketLogger() {
            @Override
            public void logPacket(byte[] fromAddress, int fromPort, byte[] toAddress, int toPort, byte[] data, boolean isSent) {
                // Mock implementation
            }

            @Override
            public boolean isEnabled() {
                return true;
            }
        };
        StunStack.packetLogger = mockPacketLogger;

        // When
        StunStack.logPacketToPcap(datagramPacket, true, null, interfacePort);

        // Then
        // No exception means the packet logging was skipped as expected
    }
}
