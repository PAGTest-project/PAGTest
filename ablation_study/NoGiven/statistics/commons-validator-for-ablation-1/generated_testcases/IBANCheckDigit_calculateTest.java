
package org.apache.commons.validator.routines.checkdigit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IBANCheckDigit_calculateTest {

    private IBANCheckDigit ibanCheckDigit;

    @BeforeEach
    public void setUp() {
        ibanCheckDigit = new IBANCheckDigit();
    }

    @Test
    public void testCalculateValidCode() throws CheckDigitException {
        String code = "GB82WEST12345698765432";
        String expectedCheckDigit = "82";
        assertEquals(expectedCheckDigit, ibanCheckDigit.calculate(code));
    }

    @Test
    public void testCalculateInvalidCodeLength() {
        String code = "GB82";
        CheckDigitException exception = assertThrows(CheckDigitException.class, () -> {
            ibanCheckDigit.calculate(code);
        });
        assertEquals("Invalid Code length=4", exception.getMessage());
    }

    @Test
    public void testCalculateNullCode() {
        CheckDigitException exception = assertThrows(CheckDigitException.class, () -> {
            ibanCheckDigit.calculate(null);
        });
        assertEquals("Invalid Code length=0", exception.getMessage());
    }

    @Test
    public void testIsValidWithValidCode() {
        String code = "GB82WEST12345698765432";
        assertTrue(ibanCheckDigit.isValid(code));
    }

    @Test
    public void testIsValidWithInvalidCodeLength() {
        String code = "GB82";
        assertFalse(ibanCheckDigit.isValid(code));
    }

    @Test
    public void testIsValidWithNullCode() {
        assertFalse(ibanCheckDigit.isValid(null));
    }
}
