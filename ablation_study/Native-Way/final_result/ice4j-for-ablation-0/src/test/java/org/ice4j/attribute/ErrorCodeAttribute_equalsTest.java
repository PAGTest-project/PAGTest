
package org.ice4j.attribute;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class ErrorCodeAttribute_equalsTest {
    private ErrorCodeAttribute errorCodeAttribute;

    @BeforeEach
    public void setUp() {
        errorCodeAttribute = new ErrorCodeAttribute();
    }

    @Test
    public void testEquals_SameObject() {
        assertTrue(errorCodeAttribute.equals(errorCodeAttribute));
    }

    @Test
    public void testEquals_DifferentClass() {
        Object obj = new Object();
        assertFalse(errorCodeAttribute.equals(obj));
    }

    @Test
    public void testEquals_DifferentAttributeType() {
        ErrorCodeAttribute other = new ErrorCodeAttribute();
        other.setErrorCode(ErrorCodeAttribute.BAD_REQUEST);
        assertFalse(errorCodeAttribute.equals(other));
    }

    @Test
    public void testEquals_DifferentDataLength() {
        ErrorCodeAttribute other = new ErrorCodeAttribute();
        other.setReasonPhrase("Reason Phrase");
        assertFalse(errorCodeAttribute.equals(other));
    }

    @Test
    public void testEquals_DifferentErrorClass() {
        ErrorCodeAttribute other = new ErrorCodeAttribute();
        other.setErrorClass((byte) 4);
        assertFalse(errorCodeAttribute.equals(other));
    }

    @Test
    public void testEquals_DifferentErrorNumber() {
        ErrorCodeAttribute other = new ErrorCodeAttribute();
        other.setErrorNumber((byte) 20);
        assertFalse(errorCodeAttribute.equals(other));
    }

    @Test
    public void testEquals_DifferentReasonPhrase() {
        ErrorCodeAttribute other = new ErrorCodeAttribute();
        other.setReasonPhrase("Reason Phrase");
        assertFalse(errorCodeAttribute.equals(other));
    }

    @Test
    public void testEquals_SameAttributes() {
        ErrorCodeAttribute other = new ErrorCodeAttribute();
        other.setErrorCode(ErrorCodeAttribute.BAD_REQUEST);
        other.setReasonPhrase("Bad Request");
        errorCodeAttribute.setErrorCode(ErrorCodeAttribute.BAD_REQUEST);
        errorCodeAttribute.setReasonPhrase("Bad Request");
        assertTrue(errorCodeAttribute.equals(other));
    }
}
