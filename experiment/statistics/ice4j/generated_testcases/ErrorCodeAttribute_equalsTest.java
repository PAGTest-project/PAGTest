
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
    public void testEquals_SameInstance() {
        assertTrue(errorCodeAttribute1.equals(errorCodeAttribute1));
    }

    @Test
    public void testEquals_DifferentType() {
        assertFalse(errorCodeAttribute1.equals("NotAnErrorCodeAttribute"));
    }

    @Test
    public void testEquals_DifferentErrorClass() {
        errorCodeAttribute1.setErrorClass((byte) 4);
        errorCodeAttribute2.setErrorClass((byte) 5);
        assertFalse(errorCodeAttribute1.equals(errorCodeAttribute2));
    }

    @Test
    public void testEquals_DifferentErrorNumber() {
        errorCodeAttribute1.setErrorNumber((byte) 10);
        errorCodeAttribute2.setErrorNumber((byte) 20);
        assertFalse(errorCodeAttribute1.equals(errorCodeAttribute2));
    }

    @Test
    public void testEquals_DifferentReasonPhrase() {
        errorCodeAttribute1.setReasonPhrase("Reason1");
        errorCodeAttribute2.setReasonPhrase("Reason2");
        assertFalse(errorCodeAttribute1.equals(errorCodeAttribute2));
    }

    @Test
    public void testEquals_SameAttributes() {
        errorCodeAttribute1.setErrorClass((byte) 4);
        errorCodeAttribute1.setErrorNumber((byte) 10);
        errorCodeAttribute1.setReasonPhrase("Reason");

        errorCodeAttribute2.setErrorClass((byte) 4);
        errorCodeAttribute2.setErrorNumber((byte) 10);
        errorCodeAttribute2.setReasonPhrase("Reason");

        assertTrue(errorCodeAttribute1.equals(errorCodeAttribute2));
    }
}
