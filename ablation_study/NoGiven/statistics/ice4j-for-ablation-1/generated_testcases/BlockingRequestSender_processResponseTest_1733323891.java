
package org.ice4j.stunclient;

import org.ice4j.TransportAddress;
import org.ice4j.message.Request;
import org.ice4j.message.StunMessageEvent;
import org.ice4j.stack.StunStack;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BlockingRequestSender_processResponseTest {

    private BlockingRequestSender sender;
    private StunStack stunStack;
    private TransportAddress localAddress;
    private TransportAddress serverAddress;
    private Request request;

    @BeforeEach
    public void setUp() {
        stunStack = mock(StunStack.class);
        localAddress = mock(TransportAddress.class);
        serverAddress = mock(TransportAddress.class);
        request = mock(Request.class);
        sender = new BlockingRequestSender(stunStack, localAddress);
    }

    @Test
    public void testProcessResponse() throws IOException, InterruptedException {
        // Given
        StunMessageEvent responseEvent = mock(StunMessageEvent.class);
        when(stunStack.sendRequest(request, serverAddress, localAddress, sender)).thenReturn(responseEvent);

        // When
        Thread senderThread = new Thread(() -> {
            try {
                sender.sendRequestAndWaitForResponse(request, serverAddress);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        senderThread.start();

        // Simulate response processing
        sender.processResponse(responseEvent);

        // Then
        assertTrue(sender.ended);
    }
}
