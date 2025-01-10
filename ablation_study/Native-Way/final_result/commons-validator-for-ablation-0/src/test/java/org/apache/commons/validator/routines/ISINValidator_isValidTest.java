
package org.apache.commons.validator.routines;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ISINValidator_isValidTest {

    private ISINValidator validatorWithCountryCheck;
    private ISINValidator validatorWithoutCountryCheck;

    @BeforeEach
    public void setUp() {
        validatorWithCountryCheck = ISINValidator.getInstance(true);
        validatorWithoutCountryCheck = ISINValidator.getInstance(false);
    }

    @Test
    public void testIsValidWithValidISINAndCountryCheck() {
        assertTrue(validatorWithCountryCheck.isValid("US0378331005"));
    }

    @Test
    public void testIsValidWithInvalidISINAndCountryCheck() {
        assertFalse(validatorWithCountryCheck.isValid("ZZ0378331005"));
    }

    @Test
    public void testIsValidWithValidISINAndNoCountryCheck() {
        assertTrue(validatorWithoutCountryCheck.isValid("US0378331005"));
    }

    @Test
    public void testIsValidWithInvalidISINAndNoCountryCheck() {
        assertFalse(validatorWithoutCountryCheck.isValid("123456789012"));
    }

    @Test
    public void testIsValidWithInvalidFormat() {
        assertFalse(validatorWithCountryCheck.isValid("INVALIDFORMAT"));
        assertFalse(validatorWithoutCountryCheck.isValid("INVALIDFORMAT"));
    }
}
