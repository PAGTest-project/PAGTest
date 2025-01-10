
package org.apache.commons.validator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GenericValidator_maxLengthTest {

    @Test
    public void testMaxLength_WithinLimit() {
        String value = "Hello\nWorld";
        int max = 15;
        int lineEndLength = 1;
        assertTrue(GenericValidator.maxLength(value, max, lineEndLength));
    }

    @Test
    public void testMaxLength_ExceedsLimit() {
        String value = "Hello\nWorld\r";
        int max = 10;
        int lineEndLength = 1;
        assertFalse(GenericValidator.maxLength(value, max, lineEndLength));
    }

    @Test
    public void testMaxLength_NoLineEndings() {
        String value = "HelloWorld";
        int max = 10;
        int lineEndLength = 1;
        assertTrue(GenericValidator.maxLength(value, max, lineEndLength));
    }

    @Test
    public void testMaxLength_EmptyString() {
        String value = "";
        int max = 5;
        int lineEndLength = 1;
        assertTrue(GenericValidator.maxLength(value, max, lineEndLength));
    }

    @Test
    public void testMaxLength_ExactlyAtLimit() {
        String value = "Hello\nWorld";
        int max = 11;
        int lineEndLength = 1;
        assertTrue(GenericValidator.maxLength(value, max, lineEndLength));
    }
}
