
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
    public void testIsValidNull() {
        assertFalse(routine.isValid(null), "isValid() null");
    }

    @Test
    public void testIsValidISBN10() {
        assertTrue(routine.isValid("020163385X"), "isValid() ISBN-10");
    }

    @Test
    public void testIsValidISBN13() {
        assertTrue(routine.isValid("9780072129519"), "isValid() ISBN-13");
    }

    @Test
    public void testIsValidInvalidLength() {
        assertFalse(routine.isValid("123456789"), "isValid() Lth 9");
        assertFalse(routine.isValid("12345678901"), "isValid() Lth 11");
        assertFalse(routine.isValid("123456789012"), "isValid() Lth 12");
        assertFalse(routine.isValid("12345678901234"), "isValid() Lth 14");
    }
}
