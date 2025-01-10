
package org.ice4j.attribute;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class ErrorCodeAttribute_getDefaultReasonPhraseTest {
    private ErrorCodeAttribute errorCodeAttribute;

    @BeforeEach
    public void setUp() {
        errorCodeAttribute = new ErrorCodeAttribute();
    }

    @Test
    public void testGetDefaultReasonPhrase_KnownErrorCode() {
        char errorCode = ErrorCodeAttribute.BAD_REQUEST;
        String expectedReasonPhrase = "(Bad Request): The request was malformed.  The client should not retry the request without modification from the previous attempt.";
        assertEquals(expectedReasonPhrase, ErrorCodeAttribute.getDefaultReasonPhrase(errorCode));
    }

    @Test
    public void testGetDefaultReasonPhrase_UnknownErrorCode() {
        char errorCode = 999;
        String expectedReasonPhrase = "Unknown Error";
        assertEquals(expectedReasonPhrase, ErrorCodeAttribute.getDefaultReasonPhrase(errorCode));
    }
}
