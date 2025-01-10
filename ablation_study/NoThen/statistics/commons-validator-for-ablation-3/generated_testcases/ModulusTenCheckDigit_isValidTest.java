
package org.apache.commons.validator.routines.checkdigit;

import org.apache.commons.validator.GenericValidator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ModulusTenCheckDigit_isValidTest {

    @Test
    void testIsValid_BlankOrNullCode() {
        ModulusTenCheckDigit checkDigit = new ModulusTenCheckDigit(new int[]{1});
        assertFalse(checkDigit.isValid(null));
        assertFalse(checkDigit.isValid(""));
        assertFalse(checkDigit.isValid("   "));
    }

    @Test
    void testIsValid_NonDigitCheckDigit() {
        ModulusTenCheckDigit checkDigit = new ModulusTenCheckDigit(new int[]{1});
        assertFalse(checkDigit.isValid("123A"));
    }

    @Test
    void testIsValid_ValidCode() {
        ModulusTenCheckDigit checkDigit = new ModulusTenCheckDigit(new int[]{1});
        assertTrue(checkDigit.isValid("1234"));
    }
}
