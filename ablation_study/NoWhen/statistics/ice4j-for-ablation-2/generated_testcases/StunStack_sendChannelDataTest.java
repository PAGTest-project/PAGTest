
package org.ice4j.stack;

import org.ice4j.*;
import org.ice4j.message.*;
import org.ice4j.socket.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

public class StunStack_sendChannelDataTest {
    private StunStack stunStack;
    private TransportAddress clientAddress;
    private TransportAddress serverAddress;
    private ChannelData channelData;

    @BeforeEach
    public void setUp() throws Exception {
        stunStack = new StunStack();

        IceSocketWrapper clientSock = new IceUdpSocketWrapper(
            new SafeCloseDatagramSocket(new InetSocketAddress("127.0.0.1", 0)));
        IceSocketWrapper serverSock = new IceUdpSocketWrapper(
            new SafeCloseDatagramSocket(new InetSocketAddress("127.0.0.1", 0)));

        clientAddress = new TransportAddress("127.0.0.1", clientSock.getLocalPort(), Transport.UDP);
        serverAddress = new TransportAddress("127.0.0.1", serverSock.getLocalPort(), Transport.UDP);

        stunStack.addSocket(clientSock);
        stunStack.addSocket(serverSock);

        channelData = new ChannelData();
    }

    @Test
    public void testSendChannelDataSuccess() throws StunException {
        assertDoesNotThrow(() -> stunStack.sendChannelData(channelData, serverAddress, clientAddress));
    }

    @Test
    public void testSendChannelDataIllegalArgumentException() {
        TransportAddress invalidAddress = new TransportAddress("invalid", 0, Transport.UDP);
        Exception exception = assertThrows(StunException.class, () -> stunStack.sendChannelData(channelData, invalidAddress, clientAddress));
        assertEquals(StunException.ILLEGAL_ARGUMENT, exception.getCause().getClass());
    }

    @Test
    public void testSendChannelDataIOException() {
        // Simulate IOException by using an invalid socket
        IceSocketWrapper invalidSocket = new IceUdpSocketWrapper(new SafeCloseDatagramSocket(new InetSocketAddress("127.0.0.1", 0)));
        try {
            invalidSocket.close();
        } catch (SocketException e) {
            e.printStackTrace();
        }
        TransportAddress invalidAddress = new TransportAddress("127.0.0.1", invalidSocket.getLocalPort(), Transport.UDP);

        Exception exception = assertThrows(StunException.class, () -> stunStack.sendChannelData(channelData, serverAddress, invalidAddress));
        assertEquals(StunException.NETWORK_ERROR, exception.getCause().getClass());
    }
}
