
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
    public void testGetReasonPhraseWithSetReasonPhrase() {
        String reasonPhrase = "Test Reason Phrase";
        errorCodeAttribute.setReasonPhrase(reasonPhrase);
        assertEquals(reasonPhrase, errorCodeAttribute.getReasonPhrase(), "Reason phrase should match the set value.");
    }

    @Test
    public void testGetReasonPhraseWithNullReasonPhrase() {
        assertNull(errorCodeAttribute.getReasonPhrase(), "Reason phrase should be null if not set.");
    }
}
