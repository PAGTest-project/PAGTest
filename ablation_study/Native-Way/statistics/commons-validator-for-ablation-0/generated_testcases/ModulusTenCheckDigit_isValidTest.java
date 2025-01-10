
package org.apache.commons.validator.routines.checkdigit;

import org.apache.commons.validator.GenericValidator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ModulusTenCheckDigit_isValidTest {

    @Test
    void testIsValid_BlankOrNull() {
        ModulusTenCheckDigit checkDigit = new ModulusTenCheckDigit(new int[]{1});
        assertFalse(checkDigit.isValid(null));
        assertFalse(checkDigit.isValid(""));
        assertFalse(checkDigit.isValid("   "));
    }

    @Test
    void testIsValid_NonDigitLastCharacter() {
        ModulusTenCheckDigit checkDigit = new ModulusTenCheckDigit(new int[]{1});
        assertFalse(checkDigit.isValid("123A"));
        assertFalse(checkDigit.isValid("123!"));
    }

    @Test
    void testIsValid_ValidCode() {
        ModulusTenCheckDigit checkDigit = spy(new ModulusTenCheckDigit(new int[]{1}));
        doReturn(true).when(checkDigit).isValid(anyString());
        assertTrue(checkDigit.isValid("1234"));
    }
}
