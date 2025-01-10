
package org.ice4j.attribute;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class ErrorCodeAttribute_setReasonPhraseTest {
    private ErrorCodeAttribute errorCodeAttribute;

    @BeforeEach
    public void setUp() {
        errorCodeAttribute = new ErrorCodeAttribute();
    }

    @Test
    public void testSetReasonPhrase() {
        String reasonPhrase = "Custom Reason Phrase";
        errorCodeAttribute.setReasonPhrase(reasonPhrase);
        assertEquals(reasonPhrase, errorCodeAttribute.getReasonPhrase());
    }
}
