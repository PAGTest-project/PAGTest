
package org.ice4j.stunclient;

import org.ice4j.*;
import org.ice4j.message.*;
import org.ice4j.stack.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class BlockingRequestSender_sendRequestAndWaitForResponseTest {

    private BlockingRequestSender sender;
    private StunStack mockStunStack;
    private TransportAddress mockLocalAddress;
    private TransportAddress mockServerAddress;
    private Request mockRequest;
    private StunResponseEvent mockResponseEvent;

    @BeforeEach
    public void setUp() {
        mockStunStack = mock(StunStack.class);
        mockLocalAddress = mock(TransportAddress.class);
        mockServerAddress = mock(TransportAddress.class);
        mockRequest = mock(Request.class);
        mockResponseEvent = mock(StunResponseEvent.class);

        sender = new BlockingRequestSender(mockStunStack, mockLocalAddress);
    }

    @Test
    public void testSendRequestAndWaitForResponse_Success() throws StunException, IOException {
        // Given
        doAnswer(invocation -> {
            sender.processResponse(mockResponseEvent);
            return null;
        }).when(mockStunStack).sendRequest(any(Request.class), any(TransportAddress.class), any(TransportAddress.class), any(BlockingRequestSender.class));

        // When
        StunMessageEvent result = sender.sendRequestAndWaitForResponse(mockRequest, mockServerAddress);

        // Then
        assertNotNull(result);
        assertEquals(mockResponseEvent, result);
    }

    @Test
    public void testSendRequestAndWaitForResponse_Failure() throws StunException, IOException {
        // Given
        doAnswer(invocation -> {
            sender.processFailure(mock(BaseStunMessageEvent.class));
            return null;
        }).when(mockStunStack).sendRequest(any(Request.class), any(TransportAddress.class), any(TransportAddress.class), any(BlockingRequestSender.class));

        // When
        StunMessageEvent result = sender.sendRequestAndWaitForResponse(mockRequest, mockServerAddress);

        // Then
        assertNotNull(result);
        assertEquals(null, result);
    }
}
