
package org.apache.commons.validator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GenericValidator_isBlankOrNullTest {

    @Test
    public void testIsBlankOrNull() {
        // Test for null value
        assertTrue(GenericValidator.isBlankOrNull(null));

        // Test for empty string
        assertTrue(GenericValidator.isBlankOrNull(""));

        // Test for string with only whitespace
        assertTrue(GenericValidator.isBlankOrNull("   "));

        // Test for non-empty string
        assertFalse(GenericValidator.isBlankOrNull("abc"));
    }
}
