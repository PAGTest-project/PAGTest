
package org.apache.commons.validator.routines;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.apache.commons.validator.Validator;

public class IBANValidator_getValidatorTest {

    private static final IBANValidator VALIDATOR = new IBANValidator();

    @BeforeEach
    public void setUp() {
        // Ensure the validatorMap is initialized correctly before each test
        VALIDATOR.setValidator("DE", 22, "DE\\d{20}");
        VALIDATOR.setValidator("GB", 22, "GB\\d{2}[A-Z]{4}\\d{14}");
    }

    @Test
    public void testGetValidator_ValidCode() {
        Validator validator = VALIDATOR.getValidator("DE12345678901234567890");
        assertNotNull(validator);
        assertEquals("DE", validator.countryCode);
    }

    @Test
    public void testGetValidator_NullCode() {
        Validator validator = VALIDATOR.getValidator(null);
        assertNull(validator);
    }

    @Test
    public void testGetValidator_ShortCode() {
        Validator validator = VALIDATOR.getValidator("D");
        assertNull(validator);
    }

    @Test
    public void testGetValidator_UnknownCountryCode() {
        Validator validator = VALIDATOR.getValidator("ZZ12345678901234567890");
        assertNull(validator);
    }

    @Test
    public void testGetValidator_CaseInsensitive() {
        Validator validator = VALIDATOR.getValidator("de12345678901234567890");
        assertNotNull(validator);
        assertEquals("DE", validator.countryCode);
    }

    @Test
    public void testGetValidator_WithOtherCountryCodes() {
        Validator validator = VALIDATOR.getValidator("GB12345678901234567890");
        assertNotNull(validator);
        assertEquals("GB", validator.countryCode);
    }

    @Test
    public void testSetValidator_ModifySingleton() {
        final IllegalStateException thrown = assertThrows(IllegalStateException.class, () -> IBANValidator.DEFAULT_IBAN_VALIDATOR.setValidator("GB", 15, "GB"));
        assertEquals("The singleton validator cannot be modified", thrown.getMessage());
    }
}
