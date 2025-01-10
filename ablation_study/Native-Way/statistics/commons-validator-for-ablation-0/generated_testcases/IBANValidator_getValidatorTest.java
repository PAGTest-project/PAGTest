
package org.apache.commons.validator.routines;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.apache.commons.validator.routines.IBANValidator.Validator;

public class IBANValidator_getValidatorTest {

    private IBANValidator ibanValidator;

    @BeforeEach
    public void setUp() {
        ibanValidator = new IBANValidator();
    }

    @Test
    public void testGetValidatorValidCode() {
        Validator validator = ibanValidator.getValidator("GB82WEST12345698765432");
        assertNotNull(validator, "Validator should not be null for a valid code");
        assertEquals("GB", validator.countryCode, "Country code should match");
    }

    @Test
    public void testGetValidatorNullCode() {
        Validator validator = ibanValidator.getValidator(null);
        assertNull(validator, "Validator should be null for a null code");
    }

    @Test
    public void testGetValidatorShortCode() {
        Validator validator = ibanValidator.getValidator("GB");
        assertNull(validator, "Validator should be null for a short code");
    }

    @Test
    public void testGetValidatorInvalidCode() {
        Validator validator = ibanValidator.getValidator("ZZ82WEST12345698765432");
        assertNull(validator, "Validator should be null for an invalid code");
    }
}
