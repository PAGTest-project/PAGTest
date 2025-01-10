
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
        errorCodeAttribute.setErrorNumber((byte) 30);
        assertEquals(4, errorCodeAttribute.getDataLength(), "getDataLength() should return 4 when reasonPhrase is null.");
    }

    @Test
    public void testGetDataLengthWithReasonPhrase() {
        errorCodeAttribute.setErrorClass((byte) 4);
        errorCodeAttribute.setErrorNumber((byte) 30);
        errorCodeAttribute.setReasonPhrase("Test Reason Phrase");
        assertEquals(22, errorCodeAttribute.getDataLength(), "getDataLength() should return the correct length when reasonPhrase is set.");
    }

    @Test
    public void testGetDataLengthWithEmptyReasonPhrase() {
        errorCodeAttribute.setErrorClass((byte) 4);
        errorCodeAttribute.setErrorNumber((byte) 30);
        errorCodeAttribute.setReasonPhrase("");
        assertEquals(4, errorCodeAttribute.getDataLength(), "getDataLength() should return 4 when reasonPhrase is empty.");
    }
}
