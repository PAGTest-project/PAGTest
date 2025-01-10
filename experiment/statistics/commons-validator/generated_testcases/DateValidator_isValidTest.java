
package org.apache.commons.validator;

import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DateValidator_isValidTest {

    @Test
    public void testIsValid_NullValue() {
        DateValidator validator = new DateValidator();
        assertFalse(validator.isValid(null, Locale.US));
    }

    @Test
    public void testIsValid_ValidDateWithLocale() {
        DateValidator validator = new DateValidator();
        assertTrue(validator.isValid("12/31/2023", Locale.US));
    }

    @Test
    public void testIsValid_InvalidDateWithLocale() {
        DateValidator validator = new DateValidator();
        assertFalse(validator.isValid("31/12/2023", Locale.US));
    }

    @Test
    public void testIsValid_ValidDateWithoutLocale() {
        DateValidator validator = new DateValidator();
        assertTrue(validator.isValid("12/31/2023", Locale.getDefault()));
    }

    @Test
    public void testIsValid_InvalidDateWithoutLocale() {
        DateValidator validator = new DateValidator();
        assertFalse(validator.isValid("31/12/2023", Locale.getDefault()));
    }

    private static class DateValidator {
        public boolean isValid(final String value, final Locale locale) {
            if (value == null) {
                return false;
            }

            DateFormat formatter;
            if (locale != null) {
                formatter = DateFormat.getDateInstance(DateFormat.SHORT, locale);
            } else {
                formatter = DateFormat.getDateInstance(DateFormat.SHORT, Locale.getDefault());
            }

            formatter.setLenient(false);

            try {
                formatter.parse(value);
            } catch (final ParseException e) {
                return false;
            }

            return true;
        }
    }
}
