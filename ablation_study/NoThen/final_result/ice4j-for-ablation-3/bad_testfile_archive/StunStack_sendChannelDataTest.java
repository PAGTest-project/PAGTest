
package org.ice4j.stack;

import org.ice4j.*;
import org.ice4j.attribute.*;
import org.ice4j.message.*;
import org.ice4j.security.*;
import org.ice4j.socket.*;
import org.jitsi.utils.concurrent.*;
import javax.crypto.*;
import java.io.*;
import java.net.*;
import java.security.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.logging.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class StunStack_sendChannelDataTest {

    @Test
    public void testSendChannelData_Success() throws StunException, IOException {
        // Given
        StunStack stunStack = new StunStack();
        ChannelData channelData = mock(ChannelData.class);
        TransportAddress sendTo = mock(TransportAddress.class);
        TransportAddress sendThrough = mock(TransportAddress.class);
        NetAccessManager netAccessManager = mock(NetAccessManager.class);
        when(stunStack.getNetAccessManager()).thenReturn(netAccessManager);

        // When
        stunStack.sendChannelData(channelData, sendTo, sendThrough);

        // Then
        verify(netAccessManager).sendMessage(channelData, sendThrough, sendTo);
    }

    @Test
    public void testSendChannelData_IllegalArgumentException() throws IOException {
        // Given
        StunStack stunStack = new StunStack();
        ChannelData channelData = mock(ChannelData.class);
        TransportAddress sendTo = mock(TransportAddress.class);
        TransportAddress sendThrough = mock(TransportAddress.class);
        NetAccessManager netAccessManager = mock(NetAccessManager.class);
        when(stunStack.getNetAccessManager()).thenReturn(netAccessManager);
        doThrow(new IllegalArgumentException()).when(netAccessManager).sendMessage(channelData, sendThrough, sendTo);

        // When
        StunException exception = assertThrows(StunException.class, () -> {
            stunStack.sendChannelData(channelData, sendTo, sendThrough);
        });

        // Then
        assertEquals(StunException.ILLEGAL_ARGUMENT, exception.getID());
        assertTrue(exception.getMessage().contains("Failed to send STUN indication: " + channelData));
    }

    @Test
    public void testSendChannelData_IOException() throws IOException {
        // Given
        StunStack stunStack = new StunStack();
        ChannelData channelData = mock(ChannelData.class);
        TransportAddress sendTo = mock(TransportAddress.class);
        TransportAddress sendThrough = mock(TransportAddress.class);
        NetAccessManager netAccessManager = mock(NetAccessManager.class);
        when(stunStack.getNetAccessManager()).thenReturn(netAccessManager);
        doThrow(new IOException()).when(netAccessManager).sendMessage(channelData, sendThrough, sendTo);

        // When
        StunException exception = assertThrows(StunException.class, () -> {
            stunStack.sendChannelData(channelData, sendTo, sendThrough);
        });

        // Then
        assertEquals(StunException.NETWORK_ERROR, exception.getID());
        assertTrue(exception.getMessage().contains("Failed to send STUN indication: " + channelData));
    }
}
