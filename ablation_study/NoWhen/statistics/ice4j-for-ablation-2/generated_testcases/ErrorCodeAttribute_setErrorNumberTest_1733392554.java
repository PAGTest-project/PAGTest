
package org.ice4j.attribute;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class ErrorCodeAttribute_setErrorNumberTest {
    private ErrorCodeAttribute errorCodeAttribute;

    @BeforeEach
    public void setUp() {
        errorCodeAttribute = new ErrorCodeAttribute();
    }

    @Test
    public void testSetErrorNumberValid() {
        byte validErrorNumber = 50;
        errorCodeAttribute.setErrorNumber(validErrorNumber);
        assertEquals(validErrorNumber, errorCodeAttribute.getErrorNumber());
    }

    @Test
    public void testSetErrorNumberInvalid() {
        byte invalidErrorNumber = 100;
        assertThrows(IllegalArgumentException.class, () -> {
            errorCodeAttribute.setErrorNumber(invalidErrorNumber);
        });
    }

    @Test
    public void testSetErrorNumberAndGetErrorCode() {
        byte errorClass = 4;
        byte errorNumber = 33;
        char expectedErrorCode = (char) (errorClass * 100 + errorNumber);

        errorCodeAttribute.setErrorClass(errorClass);
        errorCodeAttribute.setErrorNumber(errorNumber);

        assertEquals(expectedErrorCode, errorCodeAttribute.getErrorCode());
    }
}
