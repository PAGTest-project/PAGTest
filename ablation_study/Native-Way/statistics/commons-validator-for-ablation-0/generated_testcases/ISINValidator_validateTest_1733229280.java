
package org.apache.commons.validator.routines;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ISINValidator_validateTest {

    private ISINValidator validatorTrue;
    private ISINValidator validatorFalse;

    @BeforeEach
    public void setUp() {
        validatorTrue = ISINValidator.getInstance(true);
        validatorFalse = ISINValidator.getInstance(false);
    }

    @Test
    public void testValidateValidISINWithCountryCheck() {
        String validISIN = "US0378331005";
        assertNotNull(validatorTrue.validate(validISIN));
    }

    @Test
    public void testValidateInvalidISINWithCountryCheck() {
        String invalidISIN = "ZZ0378331005";
        assertNull(validatorTrue.validate(invalidISIN));
    }

    @Test
    public void testValidateValidISINWithoutCountryCheck() {
        String validISIN = "ZZ0378331005";
        assertNotNull(validatorFalse.validate(validISIN));
    }

    @Test
    public void testValidateInvalidISINWithoutCountryCheck() {
        String invalidISIN = "ZZ037833100";
        assertNull(validatorFalse.validate(invalidISIN));
    }

    @Test
    public void testValidateNullCode() {
        assertNull(validatorTrue.validate(null));
        assertNull(validatorFalse.validate(null));
    }

    @Test
    public void testValidateInvalidFormat() {
        String invalidFormat = "US037833100";
        assertNull(validatorTrue.validate(invalidFormat));
        assertNull(validatorFalse.validate(invalidFormat));
    }
}
