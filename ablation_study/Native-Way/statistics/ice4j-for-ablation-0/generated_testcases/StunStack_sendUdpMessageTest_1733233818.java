
package org.ice4j.stack;

import org.ice4j.*;
import org.ice4j.message.*;
import org.ice4j.socket.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.net.*;

public class StunStack_sendUdpMessageTest {
    private StunStack stunStack;
    private TransportAddress clientAddress;
    private TransportAddress serverAddress;
    private RawMessage udpMessage;

    @BeforeEach
    public void setUp() throws Exception {
        stunStack = new StunStack();

        DatagramSocket clientSocket = new DatagramSocket(new InetSocketAddress("127.0.0.1", 0));
        DatagramSocket serverSocket = new DatagramSocket(new InetSocketAddress("127.0.0.1", 0));

        clientAddress = new TransportAddress("127.0.0.1", clientSocket.getLocalPort(), Transport.UDP);
        serverAddress = new TransportAddress("127.0.0.1", serverSocket.getLocalPort(), Transport.UDP);

        byte[] messageBytes = new byte[]{0x00, 0x01, 0x00, 0x08, 0x21, 0x12, (byte) 0xa4, 0x42, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08};
        udpMessage = new RawMessage(messageBytes, messageBytes.length, serverAddress, clientAddress);
    }

    @Test
    public void testSendUdpMessageSuccess() throws StunException {
        assertDoesNotThrow(() -> stunStack.sendUdpMessage(udpMessage, serverAddress, clientAddress));
    }

    @Test
    public void testSendUdpMessageIllegalArgumentException() {
        udpMessage = new RawMessage(null, 0, serverAddress, clientAddress);
        StunException exception = assertThrows(StunException.class, () -> stunStack.sendUdpMessage(udpMessage, serverAddress, clientAddress));
        assertEquals(StunException.ILLEGAL_ARGUMENT, exception.getID());
    }

    @Test
    public void testSendUdpMessageIOException() {
        udpMessage = new RawMessage(new byte[]{0x00, 0x01, 0x00, 0x08, 0x21, 0x12, (byte) 0xa4, 0x42, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08}, 16, null, clientAddress);
        StunException exception = assertThrows(StunException.class, () -> stunStack.sendUdpMessage(udpMessage, serverAddress, clientAddress));
        assertEquals(StunException.NETWORK_ERROR, exception.getID());
    }
}
