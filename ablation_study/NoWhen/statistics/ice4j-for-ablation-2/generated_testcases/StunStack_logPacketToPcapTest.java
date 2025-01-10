
package org.ice4j.stack;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.UnknownHostException;

import static org.mockito.Mockito.*;

public class StunStack_logPacketToPcapTest {

    @Test
    public void testLogPacketToPcap_LoggerEnabled_Sent() throws UnknownHostException {
        // Given
        DatagramPacket mockPacket = mock(DatagramPacket.class);
        InetAddress mockInterfaceAddress = InetAddress.getByName("127.0.0.1");
        int interfacePort = 12345;
        boolean isSent = true;

        PacketLogger mockPacketLogger = mock(PacketLogger.class);
        when(StunStack.isPacketLoggerEnabled()).thenReturn(true);
        StunStack.packetLogger = mockPacketLogger;

        when(mockPacket.getAddress()).thenReturn(mockInterfaceAddress);
        when(mockPacket.getPort()).thenReturn(interfacePort);
        when(mockPacket.getData()).thenReturn(new byte[0]);

        // When
        StunStack.logPacketToPcap(mockPacket, isSent, mockInterfaceAddress, interfacePort);

        // Then
        verify(mockPacketLogger).logPacket(
                mockInterfaceAddress.getAddress(),
                interfacePort,
                mockInterfaceAddress.getAddress(),
                interfacePort,
                new byte[0],
                isSent
        );
    }

    @Test
    public void testLogPacketToPcap_LoggerDisabled() throws UnknownHostException {
        // Given
        DatagramPacket mockPacket = mock(DatagramPacket.class);
        InetAddress mockInterfaceAddress = InetAddress.getByName("127.0.0.1");
        int interfacePort = 12345;
        boolean isSent = true;

        when(StunStack.isPacketLoggerEnabled()).thenReturn(false);

        // When
        StunStack.logPacketToPcap(mockPacket, isSent, mockInterfaceAddress, interfacePort);

        // Then
        // No interaction with PacketLogger should occur
        verifyNoInteractions(mockPacket);
    }

    @Test
    public void testLogPacketToPcap_LoggerEnabled_NotSent() throws UnknownHostException {
        // Given
        DatagramPacket mockPacket = mock(DatagramPacket.class);
        InetAddress mockInterfaceAddress = InetAddress.getByName("127.0.0.1");
        int interfacePort = 12345;
        boolean isSent = false;

        PacketLogger mockPacketLogger = mock(PacketLogger.class);
        when(StunStack.isPacketLoggerEnabled()).thenReturn(true);
        StunStack.packetLogger = mockPacketLogger;

        when(mockPacket.getAddress()).thenReturn(mockInterfaceAddress);
        when(mockPacket.getPort()).thenReturn(interfacePort);
        when(mockPacket.getData()).thenReturn(new byte[0]);

        // When
        StunStack.logPacketToPcap(mockPacket, isSent, mockInterfaceAddress, interfacePort);

        // Then
        verify(mockPacketLogger).logPacket(
                mockInterfaceAddress.getAddress(),
                interfacePort,
                mockInterfaceAddress.getAddress(),
                interfacePort,
                new byte[0],
                isSent
        );
    }
}
