
package org.ice4j.attribute;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class ErrorCodeAttribute_getErrorCodeTest {
    private ErrorCodeAttribute errorCodeAttribute;

    @BeforeEach
    public void setUp() {
        errorCodeAttribute = new ErrorCodeAttribute();
    }

    @Test
    public void testGetErrorCode() {
        byte errorClass = 4;
        byte errorNumber = 30;
        char expectedErrorCode = (char)(errorClass * 100 + errorNumber);

        errorCodeAttribute.setErrorClass(errorClass);
        errorCodeAttribute.setErrorNumber(errorNumber);

        assertEquals(expectedErrorCode, errorCodeAttribute.getErrorCode(), "The error code was not calculated correctly.");
    }
}
