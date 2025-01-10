
package org.ice4j.stack;

import org.ice4j.*;
import org.ice4j.message.*;
import org.ice4j.socket.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class StunStack_removeRequestListenerTest {
    private StunStack stunStack;
    private TransportAddress localAddress;
    private DatagramSocket dummyServerSocket;

    @BeforeEach
    public void setUp() throws Exception {
        stunStack = new StunStack();
        IceUdpSocketWrapper localSock = new IceUdpSocketWrapper(
            new SafeCloseDatagramSocket(new InetSocketAddress("127.0.0.1", 0)));
        localAddress = new TransportAddress(
            "127.0.0.1", localSock.getLocalPort(), Transport.UDP);
        stunStack.addSocket(localSock);
        dummyServerSocket = new DatagramSocket(new InetSocketAddress("127.0.0.1", 0));
    }

    @Test
    public void testRemoveRequestListener() {
        SimpleRequestCollector requestCollector = new SimpleRequestCollector();
        stunStack.addRequestListener(requestCollector);
        stunStack.removeRequestListener(requestCollector);

        // Ensure the listener is removed by attempting to send a request
        try {
            dummyServerSocket.send(new DatagramPacket(
                new byte[]{}, 0, localAddress));
        } catch (Exception e) {
            fail("Exception should not be thrown");
        }

        // Verify that the listener is no longer active
        assertNull(requestCollector.collectedRequest, "Request listener was not removed");
    }
}
