
package org.ice4j.attribute;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class ErrorCodeAttribute_getErrorNumberTest {
    private ErrorCodeAttribute errorCodeAttribute;

    @BeforeEach
    public void setUp() {
        errorCodeAttribute = new ErrorCodeAttribute();
    }

    @Test
    public void testGetErrorNumber() {
        byte expectedErrorNumber = 42;
        errorCodeAttribute.setErrorNumber(expectedErrorNumber);
        byte actualErrorNumber = errorCodeAttribute.getErrorNumber();
        assertEquals(expectedErrorNumber, actualErrorNumber);
    }
}
