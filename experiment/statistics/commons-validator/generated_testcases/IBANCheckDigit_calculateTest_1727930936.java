
package org.apache.commons.validator.routines.checkdigit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IBANCheckDigit_calculateTest {

    private CheckDigit routine;

    @BeforeEach
    public void setUp() {
        routine = IBANCheckDigit.IBAN_CHECK_DIGIT;
    }

    @Test
    public void testCalculateValidCode() throws CheckDigitException {
        String code = "AD1200012030200359100100";
        String expected = "00"; // The expected check digit for this valid code
        assertEquals(expected, routine.calculate(code));
    }

    @Test
    public void testCalculateInvalidCodeLength() {
        String code = "AD12"; // This code is too short
        CheckDigitException exception = assertThrows(CheckDigitException.class, () -> {
            routine.calculate(code);
        });
        assertEquals("Invalid Code length=4", exception.getMessage());
    }

    @Test
    public void testCalculateNullCode() {
        String code = null;
        CheckDigitException exception = assertThrows(CheckDigitException.class, () -> {
            routine.calculate(code);
        });
        assertEquals("Invalid Code length=0", exception.getMessage());
    }
}
