
package org.apache.commons.validator.routines;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class IBANValidator_getValidatorTest {

    private IBANValidator validator;

    @BeforeEach
    public void setUp() {
        validator = new IBANValidator();
    }

    @Test
    public void testGetValidator_ValidCode() {
        String validCode = "DE89370400440532013000";
        IBANValidator.Validator result = validator.getValidator(validCode);
        assertNotNull(result);
        assertEquals("DE", result.countryCode);
    }

    @Test
    public void testGetValidator_NullCode() {
        IBANValidator.Validator result = validator.getValidator(null);
        assertNull(result);
    }

    @Test
    public void testGetValidator_ShortCode() {
        String shortCode = "D";
        IBANValidator.Validator result = validator.getValidator(shortCode);
        assertNull(result);
    }

    @Test
    public void testGetValidator_InvalidCode() {
        String invalidCode = "ZZ89370400440532013000";
        IBANValidator.Validator result = validator.getValidator(invalidCode);
        assertNull(result);
    }
}
