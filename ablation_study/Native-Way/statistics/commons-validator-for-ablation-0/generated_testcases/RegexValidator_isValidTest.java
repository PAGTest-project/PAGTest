
package org.apache.commons.validator.routines;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RegexValidator_isValidTest {

    private RegexValidator validator;

    @BeforeEach
    public void setUp() {
        validator = new RegexValidator("^[A-Z]{3}\\s[A-Z]{3}\\s\\d{3}$", "^[A-Z]{3}[A-Z]{3}\\d{3}$");
    }

    @Test
    public void testIsValidWithNullValue() {
        assertFalse(validator.isValid(null));
    }

    @Test
    public void testIsValidWithMatchingValue() {
        assertTrue(validator.isValid("AAC FDE 321"));
    }

    @Test
    public void testIsValidWithNonMatchingValue() {
        assertFalse(validator.isValid("AAC*FDE*321"));
    }

    @Test
    public void testIsValidWithCaseInsensitiveMatchingValue() {
        RegexValidator caseInsensitiveValidator = new RegexValidator("^[a-z]{3}\\s[a-z]{3}\\s\\d{3}$", false);
        assertTrue(caseInsensitiveValidator.isValid("aac fde 321"));
    }

    @Test
    public void testIsValidWithCaseInsensitiveNonMatchingValue() {
        RegexValidator caseInsensitiveValidator = new RegexValidator("^[a-z]{3}\\s[a-z]{3}\\s\\d{3}$", false);
        assertFalse(caseInsensitiveValidator.isValid("AAC FDE 321"));
    }
}
