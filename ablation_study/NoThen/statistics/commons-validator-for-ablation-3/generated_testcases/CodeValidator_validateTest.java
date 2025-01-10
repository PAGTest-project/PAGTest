
package org.apache.commons.validator.routines;

import org.apache.commons.validator.routines.checkdigit.CheckDigit;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.mockito.Mockito;

class CodeValidator_validateTest {

    @Test
    void testValidate_NullInput() {
        CodeValidator validator = new CodeValidator((RegexValidator) null, -1, -1, null);
        assertNull(validator.validate(null));
    }

    @Test
    void testValidate_EmptyInput() {
        CodeValidator validator = new CodeValidator((RegexValidator) null, -1, -1, null);
        assertNull(validator.validate("   "));
    }

    @Test
    void testValidate_RegexValidatorFails() {
        RegexValidator regexValidator = Mockito.mock(RegexValidator.class);
        when(regexValidator.validate("code")).thenReturn(null);
        CodeValidator validator = new CodeValidator(regexValidator, -1, -1, null);
        assertNull(validator.validate("code"));
    }

    @Test
    void testValidate_LengthCheckFails() {
        RegexValidator regexValidator = Mockito.mock(RegexValidator.class);
        when(regexValidator.validate("code")).thenReturn("code");
        CodeValidator validator = new CodeValidator(regexValidator, 5, 10, null);
        assertNull(validator.validate("code"));
    }

    @Test
    void testValidate_CheckDigitFails() {
        RegexValidator regexValidator = Mockito.mock(RegexValidator.class);
        when(regexValidator.validate("code")).thenReturn("code");
        CheckDigit checkDigit = Mockito.mock(CheckDigit.class);
        when(checkDigit.isValid("code")).thenReturn(false);
        CodeValidator validator = new CodeValidator(regexValidator, 4, 4, checkDigit);
        assertNull(validator.validate("code"));
    }

    @Test
    void testValidate_AllChecksPass() {
        RegexValidator regexValidator = Mockito.mock(RegexValidator.class);
        when(regexValidator.validate("code")).thenReturn("code");
        CheckDigit checkDigit = Mockito.mock(CheckDigit.class);
        when(checkDigit.isValid("code")).thenReturn(true);
        CodeValidator validator = new CodeValidator(regexValidator, 4, 4, checkDigit);
        assertEquals("code", validator.validate("code"));
    }
}
