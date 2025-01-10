
package org.ice4j.attribute;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class ErrorCodeAttribute_setErrorCodeTest {
    private ErrorCodeAttribute errorCodeAttribute;

    @BeforeEach
    public void setUp() {
        errorCodeAttribute = new ErrorCodeAttribute();
    }

    @Test
    public void testSetErrorCodeValid() {
        char errorCode = 404;
        errorCodeAttribute.setErrorCode(errorCode);
        assertEquals(4, errorCodeAttribute.getErrorClass());
        assertEquals(4, errorCodeAttribute.getErrorNumber());
    }

    @Test
    public void testSetErrorCodeInvalid() {
        char invalidErrorCode = 700;
        assertThrows(IllegalArgumentException.class, () -> {
            errorCodeAttribute.setErrorCode(invalidErrorCode);
        });
    }
}
