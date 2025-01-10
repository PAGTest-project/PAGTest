
package org.ice4j.stack;

import org.ice4j.*;
import org.ice4j.message.*;
import org.ice4j.socket.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class StunStack_sendUdpMessageTest {

    private StunStack stunStack;
    private TransportAddress clientAddress;
    private TransportAddress serverAddress;
    private DatagramSocket clientSocket;
    private DatagramSocket serverSocket;

    @BeforeEach
    public void setUp() throws Exception {
        clientSocket = new DatagramSocket(new InetSocketAddress("127.0.0.1", 0));
        serverSocket = new DatagramSocket(new InetSocketAddress("127.0.0.1", 0));

        clientAddress = new TransportAddress("127.0.0.1", clientSocket.getLocalPort(), Transport.UDP);
        serverAddress = new TransportAddress("127.0.0.1", serverSocket.getLocalPort(), Transport.UDP);

        stunStack = new StunStack();
        stunStack.addSocket(new IceUdpSocketWrapper(new SafeCloseDatagramSocket(clientSocket)));
        stunStack.addSocket(new IceUdpSocketWrapper(new SafeCloseDatagramSocket(serverSocket)));
    }

    @Test
    public void testSendUdpMessageSuccess() throws StunException {
        RawMessage udpMessage = RawMessage.build(new byte[]{1, 2, 3}, 3, serverAddress, clientAddress);
        stunStack.sendUdpMessage(udpMessage, serverAddress, clientAddress);
        // No exception thrown, success
    }

    @Test
    public void testSendUdpMessageIllegalArgumentException() {
        RawMessage udpMessage = RawMessage.build(new byte[]{1, 2, 3}, 3, null, clientAddress);
        assertThrows(IllegalArgumentException.class, () -> {
            stunStack.sendUdpMessage(udpMessage, serverAddress, clientAddress);
        });
    }

    @Test
    public void testSendUdpMessageIOException() {
        RawMessage udpMessage = RawMessage.build(new byte[]{1, 2, 3}, 3, serverAddress, clientAddress);
        // Mocking IOException by closing the socket
        clientSocket.close();
        assertThrows(StunException.class, () -> {
            stunStack.sendUdpMessage(udpMessage, serverAddress, clientAddress);
        });
    }
}
