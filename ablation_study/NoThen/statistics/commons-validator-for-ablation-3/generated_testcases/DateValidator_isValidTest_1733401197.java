
package org.apache.commons.validator;

import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DateValidator_isValidTest {

    @Test
    public void testIsValid_NullValue() {
        DateValidator validator = DateValidator.getInstance();
        assertFalse(validator.isValid(null, Locale.US));
    }

    @Test
    public void testIsValid_ValidDateWithLocale() {
        DateValidator validator = DateValidator.getInstance();
        assertTrue(validator.isValid("12/31/2023", Locale.US));
    }

    @Test
    public void testIsValid_InvalidDateWithLocale() {
        DateValidator validator = DateValidator.getInstance();
        assertFalse(validator.isValid("31/12/2023", Locale.US));
    }

    @Test
    public void testIsValid_ValidDateWithoutLocale() {
        DateValidator validator = DateValidator.getInstance();
        assertTrue(validator.isValid("12/31/2023", Locale.getDefault()));
    }

    @Test
    public void testIsValid_InvalidDateWithoutLocale() {
        DateValidator validator = DateValidator.getInstance();
        assertFalse(validator.isValid("31/12/2023", Locale.getDefault()));
    }
}
