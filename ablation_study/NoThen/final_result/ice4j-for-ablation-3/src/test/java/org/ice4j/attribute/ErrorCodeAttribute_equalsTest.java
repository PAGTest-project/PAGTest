
package org.ice4j.attribute;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class ErrorCodeAttribute_equalsTest {
    private ErrorCodeAttribute errorCodeAttribute1;
    private ErrorCodeAttribute errorCodeAttribute2;

    @BeforeEach
    public void setUp() {
        errorCodeAttribute1 = new ErrorCodeAttribute();
        errorCodeAttribute2 = new ErrorCodeAttribute();
    }

    @Test
    public void testEquals_SameObject() {
        assertTrue(errorCodeAttribute1.equals(errorCodeAttribute1));
    }

    @Test
    public void testEquals_DifferentClass() {
        assertFalse(errorCodeAttribute1.equals(new Object()));
    }

    @Test
    public void testEquals_DifferentAttributeType() {
        errorCodeAttribute1.setErrorCode(ErrorCodeAttribute.BAD_REQUEST);
        errorCodeAttribute2.setErrorCode(ErrorCodeAttribute.UNAUTHORIZED);
        assertFalse(errorCodeAttribute1.equals(errorCodeAttribute2));
    }

    @Test
    public void testEquals_DifferentDataLength() {
        errorCodeAttribute1.setErrorCode(ErrorCodeAttribute.BAD_REQUEST);
        errorCodeAttribute2.setErrorCode(ErrorCodeAttribute.BAD_REQUEST);
        errorCodeAttribute2.setReasonPhrase("Different Length");
        assertFalse(errorCodeAttribute1.equals(errorCodeAttribute2));
    }

    @Test
    public void testEquals_DifferentErrorClass() {
        errorCodeAttribute1.setErrorCode(ErrorCodeAttribute.BAD_REQUEST);
        errorCodeAttribute2.setErrorCode(ErrorCodeAttribute.UNAUTHORIZED);
        assertFalse(errorCodeAttribute1.equals(errorCodeAttribute2));
    }

    @Test
    public void testEquals_DifferentErrorNumber() {
        errorCodeAttribute1.setErrorCode(ErrorCodeAttribute.BAD_REQUEST);
        errorCodeAttribute2.setErrorCode(ErrorCodeAttribute.UNAUTHORIZED);
        assertFalse(errorCodeAttribute1.equals(errorCodeAttribute2));
    }

    @Test
    public void testEquals_DifferentReasonPhrase() {
        errorCodeAttribute1.setErrorCode(ErrorCodeAttribute.BAD_REQUEST);
        errorCodeAttribute1.setReasonPhrase("Reason 1");
        errorCodeAttribute2.setErrorCode(ErrorCodeAttribute.BAD_REQUEST);
        errorCodeAttribute2.setReasonPhrase("Reason 2");
        assertFalse(errorCodeAttribute1.equals(errorCodeAttribute2));
    }

    @Test
    public void testEquals_SameAttributes() {
        errorCodeAttribute1.setErrorCode(ErrorCodeAttribute.BAD_REQUEST);
        errorCodeAttribute1.setReasonPhrase("Reason");
        errorCodeAttribute2.setErrorCode(ErrorCodeAttribute.BAD_REQUEST);
        errorCodeAttribute2.setReasonPhrase("Reason");
        assertTrue(errorCodeAttribute1.equals(errorCodeAttribute2));
    }
}
