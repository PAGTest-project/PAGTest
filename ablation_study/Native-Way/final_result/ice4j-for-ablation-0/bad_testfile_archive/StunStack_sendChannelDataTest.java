
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
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class StunStack_sendChannelDataTest {
    private StunStack stunStack;
    private TransportAddress clientAddress;
    private TransportAddress serverAddress;
    private ChannelData channelData;

    @BeforeEach
    public void setUp() throws Exception {
        stunStack = new StunStack();

        clientAddress = new TransportAddress("127.0.0.1", 0, Transport.UDP);
        serverAddress = new TransportAddress("127.0.0.1", 0, Transport.UDP);

        channelData = new ChannelData(new byte[]{0x01, 0x02, 0x03, 0x04});
    }

    @Test
    public void testSendChannelDataSuccess() throws Exception {
        assertDoesNotThrow(() -> {
            stunStack.sendChannelData(channelData, serverAddress, clientAddress);
        });
    }

    @Test
    public void testSendChannelDataIllegalArgumentException() {
        assertThrows(StunException.class, () -> {
            stunStack.sendChannelData(null, serverAddress, clientAddress);
        });
    }

    @Test
    public void testSendChannelDataIOException() {
        assertThrows(StunException.class, () -> {
            stunStack.sendChannelData(channelData, null, clientAddress);
        });
    }
}
