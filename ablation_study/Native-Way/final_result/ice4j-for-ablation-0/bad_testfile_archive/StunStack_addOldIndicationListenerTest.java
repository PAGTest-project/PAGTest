
package org.ice4j.stack;

import org.ice4j.*;
import org.ice4j.message.*;
import org.ice4j.socket.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class StunStack_addOldIndicationListenerTest {
    private StunStack stunStack;
    private TransportAddress localAddress;
    private TransportAddress remoteAddress;
    private MessageEventHandler indicationListener;

    @BeforeEach
    public void setUp() throws Exception {
        stunStack = new StunStack();
        localAddress = new TransportAddress("127.0.0.1", 0, Transport.UDP);
        remoteAddress = new TransportAddress("127.0.0.1", 0, Transport.UDP);
        indicationListener = new MessageEventHandler() {
            @Override
            public void handleMessageEvent(StunMessageEvent evt) {
                // Dummy implementation
            }
        };
    }

    @Test
    public void testAddOldIndicationListener() {
        stunStack.addOldIndicationListener(localAddress, indicationListener);
        // Assuming EventDispatcher has a method to verify listeners
        assertTrue(stunStack.eventDispatcher.hasRequestListeners(localAddress), "Indication listener was not added");
    }

    @Test
    public void testAddOldIndicationListenerWithNullAddress() {
        assertThrows(NullPointerException.class, () -> {
            stunStack.addOldIndicationListener(null, indicationListener);
        }, "Expected NullPointerException was not thrown");
    }

    @Test
    public void testAddOldIndicationListenerWithNullListener() {
        assertThrows(NullPointerException.class, () -> {
            stunStack.addOldIndicationListener(localAddress, null);
        }, "Expected NullPointerException was not thrown");
    }
}
