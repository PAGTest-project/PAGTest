
package org.ice4j.attribute;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class ErrorCodeAttribute_getDataLengthTest {
    private ErrorCodeAttribute errorCodeAttribute;

    @BeforeEach
    public void setUp() throws Exception {
        errorCodeAttribute = new ErrorCodeAttribute();
    }

    @Test
    public void testGetDataLengthWithNullReasonPhrase() {
        char expectedReturn = 4;
        char actualReturn = errorCodeAttribute.getDataLength();
        assertEquals(expectedReturn, actualReturn);
    }

    @Test
    public void testGetDataLengthWithReasonPhrase() {
        String reasonPhrase = "Test Reason Phrase";
        errorCodeAttribute.setReasonPhrase(reasonPhrase);
        char expectedReturn = (char)(4 + reasonPhrase.length());
        char actualReturn = errorCodeAttribute.getDataLength();
        assertEquals(expectedReturn, actualReturn);
    }
}
