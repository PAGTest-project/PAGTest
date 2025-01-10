
package org.apache.commons.validator.routines.checkdigit;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IBANCheckDigit_isValidTest {

    @Test
    public void testIsValid_NullCode() {
        IBANCheckDigit ibanCheckDigit = new IBANCheckDigit();
        assertFalse(ibanCheckDigit.isValid(null));
    }

    @Test
    public void testIsValid_ShortCode() {
        IBANCheckDigit ibanCheckDigit = new IBANCheckDigit();
        assertFalse(ibanCheckDigit.isValid("1234"));
    }

    @Test
    public void testIsValid_InvalidCheckDigits() {
        IBANCheckDigit ibanCheckDigit = new IBANCheckDigit();
        assertFalse(ibanCheckDigit.isValid("12003456789012345678901234567890123456789012345678901234"));
        assertFalse(ibanCheckDigit.isValid("12013456789012345678901234567890123456789012345678901234"));
        assertFalse(ibanCheckDigit.isValid("12993456789012345678901234567890123456789012345678901234"));
    }

    @Test
    public void testIsValid_ValidCode() {
        IBANCheckDigit ibanCheckDigit = new IBANCheckDigit();
        assertTrue(ibanCheckDigit.isValid("123456789012345678901234567890123456789012345678901234"));
    }

    @Test
    public void testIsValid_CheckDigitException() {
        IBANCheckDigit ibanCheckDigit = new IBANCheckDigit();
        assertFalse(ibanCheckDigit.isValid("123456789012345678901234567890123456789012345678901234"));
    }
}
