
package org.ice4j.stack;

import org.ice4j.*;
import org.ice4j.message.*;
import org.ice4j.socket.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class StunStack_addRequestListenerTest {
    private StunStack stunStack;
    private TransportAddress localAddress;
    private TransportAddress remoteAddress;
    private RequestListener requestListener;

    @BeforeEach
    public void setUp() throws Exception {
        stunStack = new StunStack();
        localAddress = new TransportAddress("127.0.0.1", 0, Transport.UDP);
        remoteAddress = new TransportAddress("127.0.0.1", 0, Transport.UDP);
        requestListener = new RequestListener() {
            @Override
            public void processRequest(StunMessageEvent evt) {
                // Dummy implementation
            }
        };
    }

    @Test
    public void testAddRequestListener() {
        stunStack.addRequestListener(localAddress, requestListener);
        assertTrue(stunStack.eventDispatcher.hasRequestListeners(localAddress), "Request listener was not added successfully.");
    }

    @Test
    public void testRemoveRequestListener() {
        stunStack.addRequestListener(localAddress, requestListener);
        stunStack.removeRequestListener(localAddress, requestListener);
        assertFalse(stunStack.eventDispatcher.hasRequestListeners(localAddress), "Request listener was not removed successfully.");
    }
}
