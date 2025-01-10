
package org.apache.commons.validator.routines.checkdigit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ISBNCheckDigit_isValidTest {
    private CheckDigit routine;

    @BeforeEach
    protected void setUp() {
        routine = ISBNCheckDigit.ISBN_CHECK_DIGIT;
    }

    @Test
    public void testIsValidWithNullCode() {
        assertFalse(routine.isValid(null), "isValid() with null code");
    }

    @Test
    public void testIsValidWithValidISBN10() {
        assertTrue(routine.isValid("020163385X"), "isValid() with valid ISBN-10 code");
    }

    @Test
    public void testIsValidWithInvalidISBN10() {
        assertFalse(routine.isValid("0201633851"), "isValid() with invalid ISBN-10 code");
    }

    @Test
    public void testIsValidWithValidISBN13() {
        assertTrue(routine.isValid("9780072129519"), "isValid() with valid ISBN-13 code");
    }

    @Test
    public void testIsValidWithInvalidISBN13() {
        assertFalse(routine.isValid("9780072129518"), "isValid() with invalid ISBN-13 code");
    }

    @Test
    public void testIsValidWithInvalidLength() {
        assertFalse(routine.isValid("123456789"), "isValid() with length 9");
        assertFalse(routine.isValid("12345678901"), "isValid() with length 11");
        assertFalse(routine.isValid("123456789012"), "isValid() with length 12");
        assertFalse(routine.isValid("12345678901234"), "isValid() with length 14");
    }
}
