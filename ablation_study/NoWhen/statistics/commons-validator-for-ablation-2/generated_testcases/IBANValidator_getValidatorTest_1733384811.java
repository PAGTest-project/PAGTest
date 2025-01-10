
package org.apache.commons.validator.routines;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IBANValidator_getValidatorTest {

    private IBANValidator ibanValidator;

    @BeforeEach
    public void setUp() {
        ibanValidator = new IBANValidator();
    }

    @Test
    public void testGetValidatorWithValidCode() {
        Validator validator = ibanValidator.getValidator("GB82WEST12345698765432");
        assertNotNull(validator);
        assertEquals("GB", validator.countryCode);
    }

    @Test
    public void testGetValidatorWithNullCode() {
        Validator validator = ibanValidator.getValidator(null);
        assertNull(validator);
    }

    @Test
    public void testGetValidatorWithShortCode() {
        Validator validator = ibanValidator.getValidator("G");
        assertNull(validator);
    }

    @Test
    public void testGetValidatorWithInvalidCode() {
        Validator validator = ibanValidator.getValidator("ZZ82WEST12345698765432");
        assertNull(validator);
    }

    @Test
    public void testGetValidatorWithLowerCaseCode() {
        Validator validator = ibanValidator.getValidator("gb82west12345698765432");
        assertNotNull(validator);
        assertEquals("GB", validator.countryCode);
    }
}
