
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
        assertDoesNotThrow(() -> errorCodeAttribute.setErrorNumber(validErrorNumber));
        assertEquals(validErrorNumber, errorCodeAttribute.getErrorNumber());
    }

    @Test
    public void testSetErrorNumberInvalid() {
        byte invalidErrorNumber = 100;
        assertThrows(IllegalArgumentException.class, () -> errorCodeAttribute.setErrorNumber(invalidErrorNumber));
    }

    @Test
    public void testSetErrorNumberWithReasonPhrase() {
        byte validErrorNumber = 50;
        String reasonPhrase = "Test Reason Phrase";
        errorCodeAttribute.setErrorNumber(validErrorNumber);
        errorCodeAttribute.setReasonPhrase(reasonPhrase);
        assertEquals(validErrorNumber, errorCodeAttribute.getErrorNumber());
        assertEquals(reasonPhrase, errorCodeAttribute.getReasonPhrase());
    }

    @Test
    public void testSetErrorNumberWithErrorCode() {
        byte validErrorNumber = 50;
        byte validErrorClass = 4;
        errorCodeAttribute.setErrorNumber(validErrorNumber);
        errorCodeAttribute.setErrorClass(validErrorClass);
        assertEquals(validErrorNumber, errorCodeAttribute.getErrorNumber());
        assertEquals(validErrorClass, errorCodeAttribute.getErrorClass());
        assertEquals((char)(validErrorClass * 100 + validErrorNumber), errorCodeAttribute.getErrorCode());
    }

    @Test
    public void testSetErrorNumberWithEncode() {
        byte validErrorNumber = 50;
        byte validErrorClass = 4;
        errorCodeAttribute.setErrorNumber(validErrorNumber);
        errorCodeAttribute.setErrorClass(validErrorClass);
        byte[] encoded = errorCodeAttribute.encode();
        assertNotNull(encoded);
        assertEquals(validErrorClass, encoded[6]);
        assertEquals(validErrorNumber, encoded[7]);
    }
}
