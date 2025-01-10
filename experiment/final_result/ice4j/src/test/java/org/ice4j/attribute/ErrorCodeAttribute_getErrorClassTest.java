
package org.ice4j.attribute;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class ErrorCodeAttribute_getErrorClassTest {
    private ErrorCodeAttribute errorCodeAttribute;

    @BeforeEach
    public void setUp() {
        errorCodeAttribute = new ErrorCodeAttribute();
    }

    @Test
    public void testGetErrorClass() {
        byte expectedErrorClass = 4;
        errorCodeAttribute.setErrorClass(expectedErrorClass);
        assertEquals(expectedErrorClass, errorCodeAttribute.getErrorClass());
    }
}
