
package org.ice4j.stack;

import org.ice4j.*;
import org.ice4j.message.*;
import org.ice4j.socket.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.net.*;

public class StunStack_sendChannelDataTest {
    private StunStack stunStack;
    private TransportAddress clientAddress;
    private TransportAddress serverAddress;
    private IceSocketWrapper clientSock;
    private IceSocketWrapper serverSock;

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
    }

    @Test
    public void testSendChannelDataSuccess() throws StunException {
        ChannelData channelData = new ChannelData();
        assertDoesNotThrow(() -> stunStack.sendChannelData(channelData, serverAddress, clientAddress));
    }

    @Test
    public void testSendChannelDataIllegalArgumentException() {
        ChannelData channelData = new ChannelData();
        serverAddress = null; // Invalid address to trigger IllegalArgumentException
        StunException exception = assertThrows(StunException.class, () -> stunStack.sendChannelData(channelData, serverAddress, clientAddress));
        assertEquals(StunException.ILLEGAL_ARGUMENT, exception.getID());
    }

    @Test
    public void testSendChannelDataIOException() {
        ChannelData channelData = new ChannelData();
        // Mocking a scenario where IOException is thrown
        stunStack.getNetAccessManager().sendMessage(channelData, serverAddress, clientAddress);
        StunException exception = assertThrows(StunException.class, () -> stunStack.sendChannelData(channelData, serverAddress, clientAddress));
        assertEquals(StunException.NETWORK_ERROR, exception.getID());
    }
}
