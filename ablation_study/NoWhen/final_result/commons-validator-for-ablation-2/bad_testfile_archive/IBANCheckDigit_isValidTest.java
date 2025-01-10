
package org.apache.commons.validator.routines.checkdigit;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IBANCheckDigit_isValidTest {

    @Test
    public void testIsValid_ValidCode() {
        IBANCheckDigit checkDigit = new IBANCheckDigit();
        assertTrue(checkDigit.isValid("GB82WEST12345698765432"));
    }

    @Test
    public void testIsValid_NullCode() {
        IBANCheckDigit checkDigit = new IBANCheckDigit();
        assertFalse(checkDigit.isValid(null));
    }

    @Test
    public void testIsValid_ShortCode() {
        IBANCheckDigit checkDigit = new IBANCheckDigit();
        assertFalse(checkDigit.isValid("GB82"));
    }

    @Test
    public void testIsValid_InvalidCheckDigits() {
        IBANCheckDigit checkDigit = new IBANCheckDigit();
        assertFalse(checkDigit.isValid("GB8200WEST12345698765432"));
        assertFalse(checkDigit.isValid("GB8201WEST12345698765432"));
        assertFalse(checkDigit.isValid("GB8299WEST12345698765432"));
    }

    @Test
    public void testIsValid_CheckDigitException() {
        IBANCheckDigit checkDigit = new IBANCheckDigit();
        assertFalse(checkDigit.isValid("GB82XXWEST12345698765432"));
    }
}
