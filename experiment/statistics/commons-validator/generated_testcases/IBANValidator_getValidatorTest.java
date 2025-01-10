
package org.apache.commons.validator.routines;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IBANValidator_getValidatorTest {

    private IBANValidator validator;

    @BeforeEach
    public void setUp() {
        validator = new IBANValidator();
    }

    @Test
    public void testGetValidatorValidCode() {
        String validCode = "DE89370400440532013000";
        IBANValidator.Validator result = validator.getValidator(validCode);
        assertNotNull(result);
        assertEquals("DE", result.countryCode);
    }

    @Test
    public void testGetValidatorNullCode() {
        IBANValidator.Validator result = validator.getValidator(null);
        assertNull(result);
    }

    @Test
    public void testGetValidatorShortCode() {
        String shortCode = "D";
        IBANValidator.Validator result = validator.getValidator(shortCode);
        assertNull(result);
    }

    @Test
    public void testGetValidatorInvalidCode() {
        String invalidCode = "ZZ89370400440532013000";
        IBANValidator.Validator result = validator.getValidator(invalidCode);
        assertNull(result);
    }
}
