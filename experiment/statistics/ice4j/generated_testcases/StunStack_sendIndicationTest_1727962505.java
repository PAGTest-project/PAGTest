
package org.ice4j.stack;

import org.ice4j.*;
import org.ice4j.message.*;
import org.ice4j.socket.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class StunStack_sendIndicationTest {
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
    public void testSendIndicationSuccess() throws StunException {
        Indication indication = new Indication();
        indication.setTransactionID(TransactionID.createNewTransactionID().getBytes());

        assertDoesNotThrow(() -> stunStack.sendIndication(indication, serverAddress, clientAddress));
    }

    @Test
    public void testSendIndicationNullTransactionID() throws StunException {
        Indication indication = new Indication();

        assertDoesNotThrow(() -> stunStack.sendIndication(indication, serverAddress, clientAddress));
    }

    @Test
    public void testSendIndicationIllegalArgumentException() {
        Indication indication = new Indication();
        indication.setTransactionID(TransactionID.createNewTransactionID().getBytes());

        doThrow(new IllegalArgumentException("Invalid address")).when(stunStack.getNetAccessManager()).sendMessage(any(), any(), any());

        StunException exception = assertThrows(StunException.class, () -> stunStack.sendIndication(indication, serverAddress, clientAddress));
        assertEquals(StunException.ILLEGAL_ARGUMENT, exception.getID());
        assertTrue(exception.getMessage().contains("Failed to send STUN indication"));
    }

    @Test
    public void testSendIndicationIOException() {
        Indication indication = new Indication();
        indication.setTransactionID(TransactionID.createNewTransactionID().getBytes());

        doThrow(new IOException("Network error")).when(stunStack.getNetAccessManager()).sendMessage(any(), any(), any());

        StunException exception = assertThrows(StunException.class, () -> stunStack.sendIndication(indication, serverAddress, clientAddress));
        assertEquals(StunException.NETWORK_ERROR, exception.getID());
        assertTrue(exception.getMessage().contains("Failed to send STUN indication"));
    }
}
