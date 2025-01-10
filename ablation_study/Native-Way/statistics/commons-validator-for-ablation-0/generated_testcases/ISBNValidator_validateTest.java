
package org.apache.commons.validator.routines;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ISBNValidator_validateTest {

    private ISBNValidator validator;

    @BeforeEach
    public void setUp() {
        validator = new ISBNValidator(true);
    }

    @Test
    public void testValidateValidISBN13() {
        String validISBN13 = "9781234567897";
        assertEquals(validISBN13, validator.validate(validISBN13));
    }

    @Test
    public void testValidateValidISBN10() {
        String validISBN10 = "123456789X";
        String expectedISBN13 = "9781234567897";
        assertEquals(expectedISBN13, validator.validate(validISBN10));
    }

    @Test
    public void testValidateInvalidISBN() {
        String invalidISBN = "1234567890123";
        assertNull(validator.validate(invalidISBN));
    }

    @Test
    public void testValidateNull() {
        assertNull(validator.validate(null));
    }

    @Test
    public void testValidateEmptyString() {
        assertNull(validator.validate(""));
    }

    @Test
    public void testValidateISBN10WithoutConversion() {
        validator = new ISBNValidator(false);
        String validISBN10 = "123456789X";
        assertEquals(validISBN10, validator.validate(validISBN10));
    }
}
