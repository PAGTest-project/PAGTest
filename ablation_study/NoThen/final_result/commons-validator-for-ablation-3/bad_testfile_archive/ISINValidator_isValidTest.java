
package org.apache.commons.validator.routines;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.mockito.Mockito;

class ISINValidator_isValidTest {

    @Test
    void testIsValid_ValidCodeWithCountryCheck() {
        ISINValidator validator = ISINValidator.getInstance(true);
        CodeValidator mockValidator = mock(CodeValidator.class);
        when(mockValidator.isValid(anyString())).thenReturn(true);
        when(mockValidator.validate(anyString())).thenReturn("US");

        ISINValidator spyValidator = spy(validator);
        doReturn(mockValidator).when(spyValidator).getValidator();
        doReturn(true).when(spyValidator).checkCode(anyString());

        assertTrue(spyValidator.isValid("US0123456789"));
    }

    @Test
    void testIsValid_ValidCodeWithoutCountryCheck() {
        ISINValidator validator = ISINValidator.getInstance(false);
        CodeValidator mockValidator = mock(CodeValidator.class);
        when(mockValidator.isValid(anyString())).thenReturn(true);
        when(mockValidator.validate(anyString())).thenReturn("US");

        ISINValidator spyValidator = spy(validator);
        doReturn(mockValidator).when(spyValidator).getValidator();

        assertTrue(spyValidator.isValid("US0123456789"));
    }

    @Test
    void testIsValid_InvalidCode() {
        ISINValidator validator = ISINValidator.getInstance(true);
        CodeValidator mockValidator = mock(CodeValidator.class);
        when(mockValidator.isValid(anyString())).thenReturn(false);
        when(mockValidator.validate(anyString())).thenReturn(null);

        ISINValidator spyValidator = spy(validator);
        doReturn(mockValidator).when(spyValidator).getValidator();

        assertFalse(spyValidator.isValid("INVALIDCODE"));
    }

    private CodeValidator getValidator() {
        return mock(CodeValidator.class);
    }
}
