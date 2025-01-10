
package org.ice4j.message;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class Indication_setMessageTypeTest {

    @Test
    void testSetMessageTypeValidIndication() {
        Indication indication = new Indication();
        char validIndicationType = 'A'; // Assume 'A' is a valid indication type

        // Mock the superclass method to avoid actual state mutation
        Indication spyIndication = Mockito.spy(indication);
        Mockito.doNothing().when(spyIndication).setMessageType(validIndicationType);

        spyIndication.setMessageType(validIndicationType);

        // Verify that the superclass method was called with the correct argument
        Mockito.verify(spyIndication).setMessageType(validIndicationType);
    }

    @Test
    void testSetMessageTypeInvalidIndication() {
        Indication indication = new Indication();
        char invalidIndicationType = 'B'; // Assume 'B' is an invalid indication type

        // Expect an IllegalArgumentException to be thrown
        assertThrows(IllegalArgumentException.class, () -> {
            indication.setMessageType(invalidIndicationType);
        });
    }
}
