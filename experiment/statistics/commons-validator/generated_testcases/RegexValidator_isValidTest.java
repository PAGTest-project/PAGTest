
package org.apache.commons.validator.routines;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RegexValidator_isValidTest {

    private RegexValidator validator;

    @BeforeEach
    public void setUp() {
        validator = new RegexValidator(new String[]{"^[a-z]+-[A-Z]+-\\d+$", "^[A-Z]+-[a-z]+-\\d+$"});
    }

    @Test
    public void testIsValid_NullValue() {
        assertFalse(validator.isValid(null), "Null value should be invalid");
    }

    @Test
    public void testIsValid_ValidPattern() {
        assertTrue(validator.isValid("abc-DEF-123"), "Valid pattern should return true");
    }

    @Test
    public void testIsValid_InvalidPattern() {
        assertFalse(validator.isValid("abc-def-123"), "Invalid pattern should return false");
    }
}
