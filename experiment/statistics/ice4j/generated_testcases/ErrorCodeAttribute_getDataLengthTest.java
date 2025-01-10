
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
    public void testGetDataLength_NoReasonPhrase() {
        errorCodeAttribute.setErrorClass((byte) 4);
        errorCodeAttribute.setErrorNumber((byte) 33);
        assertEquals(4, errorCodeAttribute.getDataLength());
    }

    @Test
    public void testGetDataLength_WithReasonPhrase() {
        errorCodeAttribute.setErrorClass((byte) 4);
        errorCodeAttribute.setErrorNumber((byte) 33);
        errorCodeAttribute.setReasonPhrase("Test");
        assertEquals(8, errorCodeAttribute.getDataLength());
    }
}
