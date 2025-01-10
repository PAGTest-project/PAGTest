
package org.ice4j.message;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class Response_setMessageTypeTest {

    @Test
    void testSetMessageType_ValidResponseType() {
        Response response = new Response();
        char validResponseType = 'A'; // Assuming 'A' is a valid response type

        response.setMessageType(validResponseType);

        assertTrue(response.isSuccessResponse());
    }

    @Test
    void testSetMessageType_InvalidResponseType() {
        Response response = new Response();
        char invalidResponseType = 'Z'; // Assuming 'Z' is an invalid response type

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            response.setMessageType(invalidResponseType);
        });

        String expectedMessage = Integer.toString(invalidResponseType) + " is not a valid response type.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}
