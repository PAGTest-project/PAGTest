
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
        char errorCode = ErrorCodeAttribute.TRY_ALTERNATE;
        errorCodeAttribute.setErrorCode(errorCode);
        assertEquals(3, errorCodeAttribute.getErrorClass());
        assertEquals(0, errorCodeAttribute.getErrorNumber());
    }

    @Test
    public void testSetErrorCodeInvalid() {
        char invalidErrorCode = 700;
        assertThrows(IllegalArgumentException.class, () -> {
            errorCodeAttribute.setErrorCode(invalidErrorCode);
        });
    }

    @Test
    public void testSetErrorCodeBoundary() {
        char boundaryErrorCode = 699;
        errorCodeAttribute.setErrorCode(boundaryErrorCode);
        assertEquals(6, errorCodeAttribute.getErrorClass());
        assertEquals(99, errorCodeAttribute.getErrorNumber());
    }

    @Test
    public void testSetErrorCodeZero() {
        char zeroErrorCode = 0;
        assertThrows(IllegalArgumentException.class, () -> {
            errorCodeAttribute.setErrorCode(zeroErrorCode);
        });
    }

    @Test
    public void testSetErrorCodeMax() {
        char maxErrorCode = 699;
        errorCodeAttribute.setErrorCode(maxErrorCode);
        assertEquals(6, errorCodeAttribute.getErrorClass());
        assertEquals(99, errorCodeAttribute.getErrorNumber());
    }
}
