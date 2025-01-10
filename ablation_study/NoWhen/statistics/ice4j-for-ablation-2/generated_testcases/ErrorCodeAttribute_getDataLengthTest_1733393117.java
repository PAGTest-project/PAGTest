
package org.ice4j.attribute;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class ErrorCodeAttribute_getDataLengthTest {
    private ErrorCodeAttribute errorCodeAttribute;

    @BeforeEach
    public void setUp() {
        errorCodeAttribute = new ErrorCodeAttribute();
    }

    @Test
    public void testGetDataLengthWithNullReasonPhrase() {
        errorCodeAttribute.setErrorClass((byte) 4);
        errorCodeAttribute.setErrorNumber((byte) 33);
        assertEquals(4, errorCodeAttribute.getDataLength(), "getDataLength() did not return the expected length with null reason phrase.");
    }

    @Test
    public void testGetDataLengthWithReasonPhrase() {
        errorCodeAttribute.setErrorClass((byte) 4);
        errorCodeAttribute.setErrorNumber((byte) 33);
        errorCodeAttribute.setReasonPhrase("Test Reason Phrase");
        assertEquals(24, errorCodeAttribute.getDataLength(), "getDataLength() did not return the expected length with non-null reason phrase.");
    }

    @Test
    public void testGetDataLengthWithEmptyReasonPhrase() {
        errorCodeAttribute.setErrorClass((byte) 4);
        errorCodeAttribute.setErrorNumber((byte) 33);
        errorCodeAttribute.setReasonPhrase("");
        assertEquals(4, errorCodeAttribute.getDataLength(), "getDataLength() did not return the expected length with empty reason phrase.");
    }
}
