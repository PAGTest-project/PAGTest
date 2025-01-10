
package org.ice4j.message;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class Request_setMessageTypeTest {

    @Test
    void testSetMessageTypeValid() {
        Request request = new Request();
        char validRequestType = 'A'; // Assuming 'A' is a valid request type

        // Mock the superclass method to avoid actual state mutation
        Request spyRequest = Mockito.spy(request);
        Mockito.doNothing().when(spyRequest).superSetMessageType(validRequestType);

        assertDoesNotThrow(() -> spyRequest.setMessageType(validRequestType));
        Mockito.verify(spyRequest).superSetMessageType(validRequestType);
    }

    @Test
    void testSetMessageTypeInvalid() {
        Request request = new Request();
        char invalidRequestType = 'Z'; // Assuming 'Z' is an invalid request type

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> request.setMessageType(invalidRequestType));

        assertEquals((int) invalidRequestType + " - is not a valid request type.", exception.getMessage());
    }

    // Helper method to mock super.setMessageType
    private void superSetMessageType(char requestType) {
        super.setMessageType(requestType);
    }
}
