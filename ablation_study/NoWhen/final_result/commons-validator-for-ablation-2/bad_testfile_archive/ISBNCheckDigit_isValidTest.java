
package org.apache.commons.validator.routines.checkdigit;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ISBNCheckDigit_isValidTest {
    private CheckDigit routine;

    @BeforeEach
    protected void setUp() {
        routine = ISBNCheckDigit.ISBN10_CHECK_DIGIT;
    }

    @Test
    public void testIsValidWithNullCode() {
        assertFalse(routine.isValid(null));
    }

    @Test
    public void testIsValidWithInvalidLength() {
        assertFalse(routine.isValid("123456789")); // Length 9
        assertFalse(routine.isValid("12345678901")); // Length 11
        assertFalse(routine.isValid("123456789012")); // Length 12
        assertFalse(routine.isValid("12345678901234")); // Length 14
    }

    @Test
    public void testIsValidWithValidISBN10() {
        assertTrue(routine.isValid("020163385X"));
    }

    @Test
    public void testIsValidWithInvalidISBN10() {
        assertFalse(routine.isValid("0201633851"));
    }

    @Test
    public void testIsValidWithValidISBN13() {
        assertTrue(routine.isValid("9780072129519"));
    }

    @Test
    public void testIsValidWithInvalidISBN13() {
        assertFalse(routine.isValid("9780072129518"));
    }
}
