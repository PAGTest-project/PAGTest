
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
        assertNull(errorCodeAttribute.getReasonPhrase());
    }

    @Test
    public void testGetReasonPhraseWithNonNullReasonPhrase() {
        String reasonPhrase = "Test Reason Phrase";
        errorCodeAttribute.setReasonPhrase(reasonPhrase);
        assertEquals(reasonPhrase, errorCodeAttribute.getReasonPhrase());
    }
}
