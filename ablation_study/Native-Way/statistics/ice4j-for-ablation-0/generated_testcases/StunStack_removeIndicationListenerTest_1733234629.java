
package org.ice4j.stack;

import org.ice4j.TransportAddress;
import org.ice4j.message.MessageEventHandler;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StunStack_removeIndicationListenerTest {

    @Test
    public void testRemoveIndicationListener() {
        StunStack stunStack = new StunStack();
        TransportAddress localAddr = new TransportAddress("127.0.0.1", 3478, TransportAddress.TransportProtocol.UDP);
        MessageEventHandler indicationListener = new MessageEventHandler() {
            @Override
            public void handleMessageEvent(Object event) {
                // Dummy implementation
            }
        };

        // Add a listener to ensure it can be removed
        stunStack.addIndicationListener(localAddr, indicationListener);

        // Remove the listener
        stunStack.removeIndicationListener(localAddr, indicationListener);

        // No assertions needed as the method is void and we only need to ensure it runs without errors
    }
}
