
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
    public void testEquals_DifferentType() {
        assertFalse(errorCodeAttribute1.equals("Not an ErrorCodeAttribute"));
    }

    @Test
    public void testEquals_DifferentErrorClass() {
        errorCodeAttribute1.setErrorClass((byte) 4);
        errorCodeAttribute2.setErrorClass((byte) 5);
        assertFalse(errorCodeAttribute1.equals(errorCodeAttribute2));
    }

    @Test
    public void testEquals_DifferentErrorNumber() {
        errorCodeAttribute1.setErrorNumber((byte) 30);
        errorCodeAttribute2.setErrorNumber((byte) 31);
        assertFalse(errorCodeAttribute1.equals(errorCodeAttribute2));
    }

    @Test
    public void testEquals_DifferentReasonPhrase() {
        errorCodeAttribute1.setReasonPhrase("Reason 1");
        errorCodeAttribute2.setReasonPhrase("Reason 2");
        assertFalse(errorCodeAttribute1.equals(errorCodeAttribute2));
    }

    @Test
    public void testEquals_SameAttributes() {
        errorCodeAttribute1.setErrorClass((byte) 4);
        errorCodeAttribute1.setErrorNumber((byte) 30);
        errorCodeAttribute1.setReasonPhrase("Reason");

        errorCodeAttribute2.setErrorClass((byte) 4);
        errorCodeAttribute2.setErrorNumber((byte) 30);
        errorCodeAttribute2.setReasonPhrase("Reason");

        assertTrue(errorCodeAttribute1.equals(errorCodeAttribute2));
    }

    @Test
    public void testEquals_NullReasonPhrase() {
        errorCodeAttribute1.setReasonPhrase(null);
        errorCodeAttribute2.setReasonPhrase("Reason");
        assertFalse(errorCodeAttribute1.equals(errorCodeAttribute2));
    }

    @Test
    public void testEquals_BothNullReasonPhrase() {
        errorCodeAttribute1.setReasonPhrase(null);
        errorCodeAttribute2.setReasonPhrase(null);
        assertTrue(errorCodeAttribute1.equals(errorCodeAttribute2));
    }
}
