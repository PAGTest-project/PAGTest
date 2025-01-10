
package org.apache.commons.validator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class GenericValidator_minLengthTest {

    @Test
    public void testMinLength_WithLineEndingAdjustment() {
        // Given
        String value = "test\nstring";
        int min = 10;
        int lineEndLength = 1;

        // When
        boolean result = GenericValidator.minLength(value, min, lineEndLength);

        // Then
        assertTrue(result);
    }

    @Test
    public void testMinLength_WithoutLineEndingAdjustment() {
        // Given
        String value = "test";
        int min = 5;
        int lineEndLength = 1;

        // When
        boolean result = GenericValidator.minLength(value, min, lineEndLength);

        // Then
        assertFalse(result);
    }
}
