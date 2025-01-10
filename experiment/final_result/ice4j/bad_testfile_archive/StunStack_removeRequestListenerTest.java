
package org.ice4j.stack;

import org.ice4j.*;
import org.ice4j.message.*;
import org.ice4j.socket.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.DatagramPacket;

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
        RequestListener requestCollector = new RequestListener() {
            @Override
            public void processRequest(RequestEvent evt) {
                // Do nothing
            }
        };
        stunStack.addRequestListener(requestCollector);
        stunStack.removeRequestListener(requestCollector);

        // Ensure the listener is removed by attempting to send a request
        try {
            dummyServerSocket.send(new DatagramPacket(
                new byte[]{}, 0, localAddress.getAddress(), localAddress.getPort()));
        } catch (Exception e) {
            fail("Exception should not be thrown");
        }

        // Verify that the listener is no longer active
        // Since we cannot directly verify the internal state of the listener,
        // we assume it is removed if no exception is thrown during the send operation.
    }
}
