
package org.apache.commons.validator.routines.checkdigit;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ISBNCheckDigit_isValidTest {
    private ISBNCheckDigit routine;

    @BeforeEach
    protected void setUp() {
        routine = ISBNCheckDigit.ISBN_CHECK_DIGIT;
    }

    @Test
    public void testIsValidWithNullCode() {
        assertFalse(routine.isValid(null));
    }

    @Test
    public void testIsValidWithValidISBN10() {
        assertTrue(routine.isValid("020163385X"));
    }

    @Test
    public void testIsValidWithValidISBN13() {
        assertTrue(routine.isValid("9781590596272"));
    }

    @Test
    public void testIsValidWithInvalidLength() {
        assertFalse(routine.isValid("123456789"));
        assertFalse(routine.isValid("12345678901"));
        assertFalse(routine.isValid("123456789012"));
        assertFalse(routine.isValid("12345678901234"));
    }
}
