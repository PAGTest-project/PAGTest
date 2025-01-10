
package org.ice4j.stack;

import org.ice4j.*;
import org.ice4j.message.*;
import org.ice4j.socket.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.net.InetSocketAddress;

public class StunStack_removeSocketTest {

    private StunStack stunStack;
    private TransportAddress localAddress;
    private IceSocketWrapper localSock;

    @BeforeEach
    public void setUp() throws Exception {
        stunStack = new StunStack();
        localSock = new IceUdpSocketWrapper(
            new SafeCloseDatagramSocket(new InetSocketAddress("127.0.0.1", 0)));
        localAddress = new TransportAddress(
            "127.0.0.1", localSock.getLocalPort(), Transport.UDP);
        stunStack.addSocket(localSock);
    }

    @Test
    public void testRemoveSocket() {
        // Given: A socket is added to the StunStack
        stunStack.addSocket(localSock);

        // When: The socket is removed
        stunStack.removeSocket(localAddress);

        // Then: Verify the socket is removed by checking the state of NetAccessManager
        NetAccessManager netAccessManager = stunStack.getNetAccessManager();
        assertFalse(netAccessManager.getSockets().contains(localSock), "Socket should be removed");
    }
}
