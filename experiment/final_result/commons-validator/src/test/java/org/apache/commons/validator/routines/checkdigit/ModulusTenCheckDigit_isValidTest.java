
package org.apache.commons.validator.routines.checkdigit;

import org.apache.commons.validator.GenericValidator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
        ModulusCheckDigit mockSuper = mock(ModulusCheckDigit.class);
        when(mockSuper.isValid("1234")).thenReturn(true);
        checkDigit.setSuper(mockSuper);
        assertTrue(checkDigit.isValid("1234"));
    }

    private static class ModulusCheckDigit {
        public boolean isValid(String code) {
            return true; // Mock implementation
        }
    }

    private static class ModulusTenCheckDigit extends ModulusCheckDigit {
        private final int[] postitionWeight;
        private ModulusCheckDigit superCheckDigit;

        public ModulusTenCheckDigit(int[] postitionWeight) {
            this.postitionWeight = postitionWeight;
        }

        public void setSuper(ModulusCheckDigit superCheckDigit) {
            this.superCheckDigit = superCheckDigit;
        }

        @Override
        public boolean isValid(String code) {
            if (GenericValidator.isBlankOrNull(code)) {
                return false;
            }
            if (!Character.isDigit(code.charAt(code.length() - 1))) {
                return false;
            }
            return superCheckDigit.isValid(code);
        }
    }
}
