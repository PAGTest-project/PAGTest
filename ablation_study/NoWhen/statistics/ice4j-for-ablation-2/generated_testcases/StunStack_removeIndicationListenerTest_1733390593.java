
package org.ice4j.stack;

import org.ice4j.TransportAddress;
import org.ice4j.message.MessageEventHandler;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StunStack_removeIndicationListenerTest {

    @Test
    public void testRemoveIndicationListener() {
        // Given
        StunStack stunStack = new StunStack();
        TransportAddress localAddr = new TransportAddress("127.0.0.1", 3478, TransportAddress.TransportProtocol.UDP);
        MessageEventHandler indicationListener = event -> {};
        stunStack.addIndicationListener(localAddr, indicationListener);

        // When
        stunStack.removeIndicationListener(localAddr, indicationListener);

        // Then
        // No exception thrown, indicating successful removal
    }
}
