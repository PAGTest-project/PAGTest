
package org.ice4j.stack;

import org.ice4j.StunException;
import org.ice4j.TransportAddress;
import org.ice4j.message.RawMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class StunStack_sendUdpMessageTest {

    private StunStack stunStack;
    private NetAccessManager mockNetAccessManager;

    @BeforeEach
    public void setUp() {
        stunStack = new StunStack();
        mockNetAccessManager = mock(NetAccessManager.class);
        stunStack.netAccessManager = mockNetAccessManager;
    }

    @Test
    public void testSendUdpMessage_Success() throws StunException, IOException {
        RawMessage udpMessage = mock(RawMessage.class);
        TransportAddress sendTo = mock(TransportAddress.class);
        TransportAddress sendThrough = mock(TransportAddress.class);

        when(udpMessage.getBytes()).thenReturn(new byte[0]);

        stunStack.sendUdpMessage(udpMessage, sendTo, sendThrough);

        verify(mockNetAccessManager).sendMessage(any(byte[].class), eq(sendThrough), eq(sendTo));
    }

    @Test
    public void testSendUdpMessage_IllegalArgumentException() throws IOException {
        RawMessage udpMessage = mock(RawMessage.class);
        TransportAddress sendTo = mock(TransportAddress.class);
        TransportAddress sendThrough = mock(TransportAddress.class);

        when(udpMessage.getBytes()).thenReturn(new byte[0]);
        doThrow(new IllegalArgumentException()).when(mockNetAccessManager).sendMessage(any(byte[].class), eq(sendThrough), eq(sendTo));

        assertThrows(StunException.class, () -> {
            stunStack.sendUdpMessage(udpMessage, sendTo, sendThrough);
        });
    }

    @Test
    public void testSendUdpMessage_IOException() throws IOException {
        RawMessage udpMessage = mock(RawMessage.class);
        TransportAddress sendTo = mock(TransportAddress.class);
        TransportAddress sendThrough = mock(TransportAddress.class);

        when(udpMessage.getBytes()).thenReturn(new byte[0]);
        doThrow(new IOException()).when(mockNetAccessManager).sendMessage(any(byte[].class), eq(sendThrough), eq(sendTo));

        assertThrows(StunException.class, () -> {
            stunStack.sendUdpMessage(udpMessage, sendTo, sendThrough);
        });
    }
}
