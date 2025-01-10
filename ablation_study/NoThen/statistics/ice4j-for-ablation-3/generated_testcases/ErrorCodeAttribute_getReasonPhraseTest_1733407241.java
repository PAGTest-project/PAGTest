
package org.ice4j.attribute;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class ErrorCodeAttribute_getReasonPhraseTest {
    private ErrorCodeAttribute errorCodeAttribute;

    @BeforeEach
    public void setUp() {
        errorCodeAttribute = new ErrorCodeAttribute();
    }

    @Test
    public void testGetReasonPhraseWithNullReasonPhrase() {
        assertNull(errorCodeAttribute.getReasonPhrase(), "Reason phrase should be null when not set.");
    }

    @Test
    public void testGetReasonPhraseWithSetReasonPhrase() {
        String reasonPhrase = "Test Reason Phrase";
        errorCodeAttribute.setReasonPhrase(reasonPhrase);
        assertEquals(reasonPhrase, errorCodeAttribute.getReasonPhrase(), "Reason phrase should match the set value.");
    }

    @Test
    public void testGetReasonPhraseAfterSettingErrorCode() {
        char errorCode = 400;
        errorCodeAttribute.setErrorCode(errorCode);
        String expectedReasonPhrase = ErrorCodeAttribute.getDefaultReasonPhrase(errorCode);
        assertEquals(expectedReasonPhrase, errorCodeAttribute.getReasonPhrase(), "Reason phrase should match the default reason phrase for the error code.");
    }

    @Test
    public void testGetReasonPhraseAfterOverridingWithSetReasonPhrase() {
        char errorCode = 400;
        String customReasonPhrase = "Custom Reason Phrase";
        errorCodeAttribute.setErrorCode(errorCode);
        errorCodeAttribute.setReasonPhrase(customReasonPhrase);
        assertEquals(customReasonPhrase, errorCodeAttribute.getReasonPhrase(), "Reason phrase should match the custom set value.");
    }
}
