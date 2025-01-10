
package org.ice4j.attribute;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class ErrorCodeAttribute_setErrorClassTest {
    private ErrorCodeAttribute errorCodeAttribute;

    @BeforeEach
    public void setUp() {
        errorCodeAttribute = new ErrorCodeAttribute();
    }

    @Test
    public void testSetErrorClassValid() {
        byte validErrorClass = 42;
        assertDoesNotThrow(() -> errorCodeAttribute.setErrorClass(validErrorClass));
        assertEquals(validErrorClass, errorCodeAttribute.getErrorClass());
    }

    @Test
    public void testSetErrorClassInvalid() {
        byte invalidErrorClass = 100;
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
            () -> errorCodeAttribute.setErrorClass(invalidErrorClass));
        assertEquals("100 is not a valid error number!", exception.getMessage());
    }
}
