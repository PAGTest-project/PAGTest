
package org.ice4j.attribute;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class ErrorCodeAttribute_setErrorCodeTest {
    private ErrorCodeAttribute errorCodeAttribute;

    @BeforeEach
    public void setUp() throws Exception {
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
    public void testSetErrorCodeInvalidClass() {
        char errorCode = 700;
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            errorCodeAttribute.setErrorCode(errorCode);
        });
        assertEquals("7 is not a valid error number!", exception.getMessage());
    }

    @Test
    public void testSetErrorCodeInvalidNumber() {
        char errorCode = 4000;
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            errorCodeAttribute.setErrorCode(errorCode);
        });
        assertEquals("40 is not a valid error number!", exception.getMessage());
    }

    @Test
    public void testSetErrorCodeBoundary() {
        char errorCode = 699;
        errorCodeAttribute.setErrorCode(errorCode);
        assertEquals(6, errorCodeAttribute.getErrorClass());
        assertEquals(99, errorCodeAttribute.getErrorNumber());
    }

    @Test
    public void testSetErrorCodeZero() {
        char errorCode = 0;
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            errorCodeAttribute.setErrorCode(errorCode);
        });
        assertEquals("0 is not a valid error number!", exception.getMessage());
    }
}
