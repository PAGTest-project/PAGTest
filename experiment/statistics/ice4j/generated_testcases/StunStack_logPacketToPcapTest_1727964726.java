
package org.ice4j.stack;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.UnknownHostException;

import static org.mockito.Mockito.*;

public class StunStack_logPacketToPcapTest {

    @Test
    public void testLogPacketToPcap_PacketLoggerEnabled() throws UnknownHostException {
        // Given
        DatagramPacket p = mock(DatagramPacket.class);
        InetAddress interfaceAddress = InetAddress.getByName("127.0.0.1");
        int interfacePort = 12345;
        boolean isSent = true;

        PacketLogger packetLogger = mock(PacketLogger.class);
        when(packetLogger.isEnabled()).thenReturn(true);
        StunStack.setPacketLogger(packetLogger);

        when(p.getAddress()).thenReturn(InetAddress.getByName("192.168.1.1"));
        when(p.getPort()).thenReturn(54321);
        when(p.getData()).thenReturn(new byte[0]);

        // When
        StunStack.logPacketToPcap(p, isSent, interfaceAddress, interfacePort);

        // Then
        verify(packetLogger).logPacket(
                interfaceAddress.getAddress(),
                interfacePort,
                p.getAddress().getAddress(),
                p.getPort(),
                p.getData(),
                isSent
        );
    }

    @Test
    public void testLogPacketToPcap_PacketLoggerDisabled() throws UnknownHostException {
        // Given
        DatagramPacket p = mock(DatagramPacket.class);
        InetAddress interfaceAddress = InetAddress.getByName("127.0.0.1");
        int interfacePort = 12345;
        boolean isSent = true;

        PacketLogger packetLogger = mock(PacketLogger.class);
        when(packetLogger.isEnabled()).thenReturn(false);
        StunStack.setPacketLogger(packetLogger);

        // When
        StunStack.logPacketToPcap(p, isSent, interfaceAddress, interfacePort);

        // Then
        verify(packetLogger, never()).logPacket(any(), anyInt(), any(), anyInt(), any(), anyBoolean());
    }

    @Test
    public void testLogPacketToPcap_InterfaceAddressNull() throws UnknownHostException {
        // Given
        DatagramPacket p = mock(DatagramPacket.class);
        InetAddress interfaceAddress = null;
        int interfacePort = 12345;
        boolean isSent = true;

        PacketLogger packetLogger = mock(PacketLogger.class);
        when(packetLogger.isEnabled()).thenReturn(true);
        StunStack.setPacketLogger(packetLogger);

        // When
        StunStack.logPacketToPcap(p, isSent, interfaceAddress, interfacePort);

        // Then
        verify(packetLogger, never()).logPacket(any(), anyInt(), any(), anyInt(), any(), anyBoolean());
    }
}
