
package org.apache.commons.validator.routines.checkdigit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ISBNCheckDigit_calculateTest {
    private ISBNCheckDigit routine;

    @BeforeEach
    protected void setUp() {
        routine = ISBNCheckDigit.ISBN_CHECK_DIGIT;
    }

    @Test
    public void testCalculateISBN10() throws CheckDigitException {
        String code = "020163385";
        String expected = "X"; // Example check digit for ISBN-10
        assertEquals(expected, routine.calculate(code), "calculate() ISBN-10");
    }

    @Test
    public void testCalculateISBN13() throws CheckDigitException {
        String code = "978007212951";
        String expected = "9"; // Example check digit for ISBN-13
        assertEquals(expected, routine.calculate(code), "calculate() ISBN-13");
    }

    @Test
    public void testCalculateMissingCode() {
        String code = null;
        CheckDigitException exception = assertThrows(CheckDigitException.class, () -> {
            routine.calculate(code);
        });
        assertEquals("ISBN Code is missing", exception.getMessage(), "calculate() missing code");
    }

    @Test
    public void testCalculateInvalidLength() {
        String code = "1234567890123";
        CheckDigitException exception = assertThrows(CheckDigitException.class, () -> {
            routine.calculate(code);
        });
        assertEquals("Invalid ISBN Length = 13", exception.getMessage(), "calculate() invalid length");
    }
}
