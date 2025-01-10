
package org.apache.commons.validator.routines;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ISINValidator_isValidTest {

    @Test
    void testIsValid_ValidCodeWithCountryCheck() {
        ISINValidator validator = ISINValidator.getInstance(true);
        CodeValidator mockValidator = mock(CodeValidator.class);
        when(mockValidator.isValid(anyString())).thenReturn(true);

        ISINValidator spyValidator = spy(validator);
        doReturn(mockValidator).when(spyValidator).getValidator();
        doReturn(true).when(spyValidator).checkCode(anyString());

        assertTrue(spyValidator.isValid("US0378331005"));
    }

    @Test
    void testIsValid_ValidCodeWithoutCountryCheck() {
        ISINValidator validator = ISINValidator.getInstance(false);
        CodeValidator mockValidator = mock(CodeValidator.class);
        when(mockValidator.isValid(anyString())).thenReturn(true);

        ISINValidator spyValidator = spy(validator);
        doReturn(mockValidator).when(spyValidator).getValidator();

        assertTrue(spyValidator.isValid("US0378331005"));
    }

    @Test
    void testIsValid_InvalidCode() {
        ISINValidator validator = ISINValidator.getInstance(true);
        CodeValidator mockValidator = mock(CodeValidator.class);
        when(mockValidator.isValid(anyString())).thenReturn(false);

        ISINValidator spyValidator = spy(validator);
        doReturn(mockValidator).when(spyValidator).getValidator();

        assertFalse(spyValidator.isValid("INVALIDCODE"));
    }

    @Test
    void testIsValid_ValidCodeWithInvalidCountry() {
        ISINValidator validator = ISINValidator.getInstance(true);
        CodeValidator mockValidator = mock(CodeValidator.class);
        when(mockValidator.isValid(anyString())).thenReturn(true);

        ISINValidator spyValidator = spy(validator);
        doReturn(mockValidator).when(spyValidator).getValidator();
        doReturn(false).when(spyValidator).checkCode(anyString());

        assertFalse(spyValidator.isValid("XX0378331005"));
    }

    private CodeValidator getValidator() {
        return ISINValidator.getInstance(true).getValidator();
    }
}
