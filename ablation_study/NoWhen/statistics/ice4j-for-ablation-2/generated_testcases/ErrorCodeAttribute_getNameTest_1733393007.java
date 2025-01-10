
package org.ice4j.attribute;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class ErrorCodeAttribute_getNameTest {
    private ErrorCodeAttribute errorCodeAttribute;

    @BeforeEach
    public void setUp() {
        errorCodeAttribute = new ErrorCodeAttribute();
    }

    @Test
    public void testGetName() {
        assertEquals("ERROR-CODE", errorCodeAttribute.getName());
    }

    @Test
    public void testGetNameWithSetErrorCode() {
        char errorCode = (char)(MsgFixture.ERROR_CLASS * 100 + MsgFixture.ERROR_NUMBER);
        errorCodeAttribute.setErrorCode(errorCode);
        assertEquals("ERROR-CODE", errorCodeAttribute.getName());
    }

    @Test
    public void testGetNameWithSetReasonPhrase() {
        errorCodeAttribute.setReasonPhrase("Test Reason Phrase");
        assertEquals("ERROR-CODE", errorCodeAttribute.getName());
    }

    @Test
    public void testGetNameWithSetErrorCodeAndReasonPhrase() {
        char errorCode = (char)(MsgFixture.ERROR_CLASS * 100 + MsgFixture.ERROR_NUMBER);
        errorCodeAttribute.setErrorCode(errorCode);
        errorCodeAttribute.setReasonPhrase("Test Reason Phrase");
        assertEquals("ERROR-CODE", errorCodeAttribute.getName());
    }
}
