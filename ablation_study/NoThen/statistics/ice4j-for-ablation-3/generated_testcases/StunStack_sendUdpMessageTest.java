
package org.ice4j.stack;

import org.ice4j.*;
import org.ice4j.message.*;
import org.ice4j.socket.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.net.InetSocketAddress;

public class StunStack_sendUdpMessageTest {
    private StunStack stunStack;
    private TransportAddress clientAddress;
    private TransportAddress serverAddress;
    private IceSocketWrapper clientSock;
    private IceSocketWrapper serverSock;
    private RawMessage udpMessage;

    @BeforeEach
    public void setUp() throws Exception {
        clientSock = new IceUdpSocketWrapper(
            new SafeCloseDatagramSocket(new InetSocketAddress("127.0.0.1", 0)));
        serverSock = new IceUdpSocketWrapper(
            new SafeCloseDatagramSocket(new InetSocketAddress("127.0.0.1", 0)));

        clientAddress = new TransportAddress("127.0.0.1", clientSock.getLocalPort(), Transport.UDP);
        serverAddress = new TransportAddress("127.0.0.1", serverSock.getLocalPort(), Transport.UDP);

        stunStack = new StunStack();
        stunStack.addSocket(clientSock);
        stunStack.addSocket(serverSock);

        udpMessage = RawMessage.build(new byte[]{0x00, 0x01, 0x00, 0x08, 0x21, 0x12, (byte) 0xa4, 0x42, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00}, 16, clientAddress, serverAddress);
    }

    @Test
    public void testSendUdpMessageSuccess() throws StunException {
        assertDoesNotThrow(() -> stunStack.sendUdpMessage(udpMessage, serverAddress, clientAddress));
    }

    @Test
    public void testSendUdpMessageIllegalArgumentException() {
        udpMessage = RawMessage.build(new byte[]{}, 0, clientAddress, serverAddress);
        StunException exception = assertThrows(StunException.class, () -> stunStack.sendUdpMessage(udpMessage, serverAddress, clientAddress));
        assertEquals(StunException.ILLEGAL_ARGUMENT, exception.getID());
        assertTrue(exception.getMessage().contains("Failed to send STUN indication"));
    }

    @Test
    public void testSendUdpMessageIOException() {
        udpMessage = RawMessage.build(new byte[]{0x00, 0x01, 0x00, 0x08, 0x21, 0x12, (byte) 0xa4, 0x42, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00}, 16, clientAddress, serverAddress);
        // Simulate IOException by setting an invalid address
        serverAddress = new TransportAddress("invalid.address", 12345, Transport.UDP);
        StunException exception = assertThrows(StunException.class, () -> stunStack.sendUdpMessage(udpMessage, serverAddress, clientAddress));
        assertEquals(StunException.NETWORK_ERROR, exception.getID());
        assertTrue(exception.getMessage().contains("Failed to send STUN indication"));
    }
}
