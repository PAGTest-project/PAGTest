
package org.apache.commons.validator.routines;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ISINValidator_isValidTest {

    private ISINValidator validatorTrue;
    private ISINValidator validatorFalse;

    @BeforeEach
    public void setUp() {
        validatorTrue = ISINValidator.getInstance(true);
        validatorFalse = ISINValidator.getInstance(false);
    }

    @Test
    public void testIsValidWithCountryCodeCheck() {
        // Valid ISIN with valid country code
        assertTrue(validatorTrue.isValid("US0378331005"));
        // Valid ISIN with invalid country code
        assertFalse(validatorTrue.isValid("ZZ0378331005"));
    }

    @Test
    public void testIsValidWithoutCountryCodeCheck() {
        // Valid ISIN with valid country code
        assertTrue(validatorFalse.isValid("US0378331005"));
        // Valid ISIN with invalid country code
        assertTrue(validatorFalse.isValid("ZZ0378331005"));
    }

    @Test
    public void testIsValidInvalidISIN() {
        // Invalid ISIN format
        assertFalse(validatorTrue.isValid("INVALIDISIN"));
        assertFalse(validatorFalse.isValid("INVALIDISIN"));
    }
}
