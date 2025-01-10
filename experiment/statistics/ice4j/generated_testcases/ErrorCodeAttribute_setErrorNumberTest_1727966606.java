
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
        assertEquals(validErrorNumber, errorCodeAttribute.getErrorNumber(), "Error number was not set correctly.");
    }

    @Test
    public void testSetErrorNumberInvalid() {
        byte invalidErrorNumber = 100;
        assertThrows(IllegalArgumentException.class, () -> {
            errorCodeAttribute.setErrorNumber(invalidErrorNumber);
        }, "Expected IllegalArgumentException was not thrown.");
    }
}
