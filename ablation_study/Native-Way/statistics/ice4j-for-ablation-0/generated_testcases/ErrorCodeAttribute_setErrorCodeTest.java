
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
        char errorCode = 400;
        assertDoesNotThrow(() -> errorCodeAttribute.setErrorCode(errorCode));
        assertEquals(4, errorCodeAttribute.getErrorClass());
        assertEquals(0, errorCodeAttribute.getErrorNumber());
    }

    @Test
    public void testSetErrorCodeInvalid() {
        char invalidErrorCode = 700;
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
            () -> errorCodeAttribute.setErrorCode(invalidErrorCode));
        assertEquals("7 is not a valid error number!", exception.getMessage());
    }

    @Test
    public void testSetErrorCodeBoundary() {
        char boundaryErrorCode = 699;
        assertDoesNotThrow(() -> errorCodeAttribute.setErrorCode(boundaryErrorCode));
        assertEquals(6, errorCodeAttribute.getErrorClass());
        assertEquals(99, errorCodeAttribute.getErrorNumber());
    }

    @Test
    public void testSetErrorCodeZero() {
        char zeroErrorCode = 0;
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
            () -> errorCodeAttribute.setErrorCode(zeroErrorCode));
        assertEquals("0 is not a valid error number!", exception.getMessage());
    }

    @Test
    public void testSetErrorCodeMax() {
        char maxErrorCode = 600;
        assertDoesNotThrow(() -> errorCodeAttribute.setErrorCode(maxErrorCode));
        assertEquals(6, errorCodeAttribute.getErrorClass());
        assertEquals(0, errorCodeAttribute.getErrorNumber());
    }
}
