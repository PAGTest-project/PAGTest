
package org.apache.commons.validator.routines.checkdigit;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class IBANCheckDigit_isValidTest {

    @Test
    void testIsValid_ValidCode() {
        IBANCheckDigit checkDigit = new IBANCheckDigit();
        assertTrue(checkDigit.isValid("GB82WEST12345698765432"));
    }

    @Test
    void testIsValid_NullCode() {
        IBANCheckDigit checkDigit = new IBANCheckDigit();
        assertFalse(checkDigit.isValid(null));
    }

    @Test
    void testIsValid_ShortCode() {
        IBANCheckDigit checkDigit = new IBANCheckDigit();
        assertFalse(checkDigit.isValid("GB82"));
    }

    @Test
    void testIsValid_InvalidCheckDigits() {
        IBANCheckDigit checkDigit = new IBANCheckDigit();
        assertFalse(checkDigit.isValid("GB82WEST12345698765400"));
        assertFalse(checkDigit.isValid("GB82WEST12345698765401"));
        assertFalse(checkDigit.isValid("GB82WEST12345698765499"));
    }

    @Test
    void testIsValid_CheckDigitException() {
        IBANCheckDigit checkDigit = new IBANCheckDigit();
        assertFalse(checkDigit.isValid("GB82WEST123456987654XX"));
    }
}
