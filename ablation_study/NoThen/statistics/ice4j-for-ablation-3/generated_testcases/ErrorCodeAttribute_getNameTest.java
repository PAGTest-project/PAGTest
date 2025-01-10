
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
    public void testGetNameAfterSettingErrorCode() {
        errorCodeAttribute.setErrorCode((char) 400);
        assertEquals("ERROR-CODE", errorCodeAttribute.getName());
    }

    @Test
    public void testGetNameAfterSettingReasonPhrase() {
        errorCodeAttribute.setReasonPhrase("Bad Request");
        assertEquals("ERROR-CODE", errorCodeAttribute.getName());
    }

    @Test
    public void testGetNameAfterSettingErrorCodeAndReasonPhrase() {
        errorCodeAttribute.setErrorCode((char) 400);
        errorCodeAttribute.setReasonPhrase("Bad Request");
        assertEquals("ERROR-CODE", errorCodeAttribute.getName());
    }
}
