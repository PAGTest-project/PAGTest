
package org.apache.commons.validator.routines;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ISINValidator_validateTest {

    private ISINValidator validatorWithCountryCheck;
    private ISINValidator validatorWithoutCountryCheck;

    @BeforeEach
    public void setUp() {
        validatorWithCountryCheck = ISINValidator.getInstance(true);
        validatorWithoutCountryCheck = ISINValidator.getInstance(false);
    }

    @Test
    public void testValidateWithCountryCheckValid() {
        String validISIN = "US0378331005"; // Valid ISIN with valid country code
        Object result = validatorWithCountryCheck.validate(validISIN);
        assertNotNull(result);
    }

    @Test
    public void testValidateWithCountryCheckInvalidCountryCode() {
        String invalidCountryISIN = "ZZ0378331005"; // Invalid country code
        Object result = validatorWithCountryCheck.validate(invalidCountryISIN);
        assertNull(result);
    }

    @Test
    public void testValidateWithoutCountryCheckValid() {
        String validISIN = "ZZ0378331005"; // Valid ISIN but invalid country code
        Object result = validatorWithoutCountryCheck.validate(validISIN);
        assertNotNull(result);
    }

    @Test
    public void testValidateWithoutCountryCheckInvalid() {
        String invalidISIN = "US037833100"; // Invalid ISIN length
        Object result = validatorWithoutCountryCheck.validate(invalidISIN);
        assertNull(result);
    }
}
