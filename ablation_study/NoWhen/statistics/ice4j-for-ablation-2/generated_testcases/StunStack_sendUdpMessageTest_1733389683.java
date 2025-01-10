
package org.ice4j.stack;

import org.ice4j.*;
import org.ice4j.message.*;
import org.ice4j.socket.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.net.*;

public class StunStack_sendUdpMessageTest {
    private StunStack stunStack;
    private TransportAddress localAddress;
    private TransportAddress remoteAddress;
    private DatagramSocket dummyServerSocket;

    @BeforeEach
    public void setUp() throws Exception {
        stunStack = new StunStack();
        IceSocketWrapper localSock = new IceUdpSocketWrapper(
            new SafeCloseDatagramSocket(new InetSocketAddress("127.0.0.1", 0)));
        localAddress = new TransportAddress(
            "127.0.0.1", localSock.getLocalPort(), Transport.UDP);
        stunStack.addSocket(localSock);

        dummyServerSocket = new DatagramSocket(new InetSocketAddress("127.0.0.1", 0));
        remoteAddress = new TransportAddress(
            "127.0.0.1", dummyServerSocket.getLocalPort(), Transport.UDP);
    }

    @Test
    public void testSendUdpMessageSuccess() throws Exception {
        RawMessage udpMessage = RawMessage.build(new byte[]{0x01, 0x02}, 2, remoteAddress, localAddress);
        TransportAddress sendThrough = localAddress;

        assertDoesNotThrow(() -> stunStack.sendUdpMessage(udpMessage, remoteAddress, sendThrough));
    }

    @Test
    public void testSendUdpMessageIllegalArgumentException() {
        RawMessage udpMessage = RawMessage.build(new byte[]{0x01, 0x02}, 2, remoteAddress, localAddress);
        TransportAddress invalidAddress = new TransportAddress("invalid", 1234, Transport.UDP);

        Exception exception = assertThrows(StunException.class, () -> {
            stunStack.sendUdpMessage(udpMessage, invalidAddress, localAddress);
        });

        assertEquals(StunException.ILLEGAL_ARGUMENT, ((StunException) exception).getID());
    }

    @Test
    public void testSendUdpMessageIOException() throws Exception {
        RawMessage udpMessage = RawMessage.build(new byte[]{0x01, 0x02}, 2, remoteAddress, localAddress);
        TransportAddress sendThrough = localAddress;

        // Simulate IOException by closing the socket
        dummyServerSocket.close();

        Exception exception = assertThrows(StunException.class, () -> {
            stunStack.sendUdpMessage(udpMessage, remoteAddress, sendThrough);
        });

        assertEquals(StunException.NETWORK_ERROR, ((StunException) exception).getID());
    }
}
