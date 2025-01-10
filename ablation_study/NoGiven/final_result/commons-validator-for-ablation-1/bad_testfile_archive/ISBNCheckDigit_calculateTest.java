
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
        String expectedCheckDigit = ISBN10CheckDigit.ISBN10_CHECK_DIGIT.calculate(code);
        assertEquals(expectedCheckDigit, routine.calculate(code), "ISBN-10 check digit calculation");
    }

    @Test
    public void testCalculateISBN13() throws CheckDigitException {
        String code = "978007212951";
        String expectedCheckDigit = ISBN13CheckDigit.ISBN13_CHECK_DIGIT.calculate(code);
        assertEquals(expectedCheckDigit, routine.calculate(code), "ISBN-13 check digit calculation");
    }

    @Test
    public void testCalculateMissingCode() {
        assertThrows(CheckDigitException.class, () -> {
            routine.calculate(null);
        }, "ISBN Code is missing");
    }

    @Test
    public void testCalculateInvalidLength() {
        assertThrows(CheckDigitException.class, () -> {
            routine.calculate("12345678");
        }, "Invalid ISBN Length = 8");

        assertThrows(CheckDigitException.class, () -> {
            routine.calculate("1234567890");
        }, "Invalid ISBN Length = 10");

        assertThrows(CheckDigitException.class, () -> {
            routine.calculate("12345678901");
        }, "Invalid ISBN Length = 11");

        assertThrows(CheckDigitException.class, () -> {
            routine.calculate("1234567890123");
        }, "Invalid ISBN Length = 13");
    }
}
