
package org.ice4j.message;

import static org.junit.jupiter.api.Assertions.*;
import org.ice4j.*;
import org.ice4j.attribute.*;
import org.junit.jupiter.api.*;

public class MessageFactory_createBindingIndicationTest {

    @Test
    public void testCreateBindingIndication() {
        Indication expectedIndication = new Indication();
        expectedIndication.setMessageType(Message.BINDING_INDICATION);

        Indication actualIndication = MessageFactory.createBindingIndication();
        assertEquals(expectedIndication.getMessageType(), actualIndication.getMessageType());
    }
}
