
package org.apache.commons.validator.routines;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IBANValidator_isValidTest {

    private IBANValidator validator;

    @BeforeEach
    public void setUp() {
        validator = new IBANValidator();
    }

    @Test
    public void testIsValidWithValidIBAN() {
        assertTrue(validator.isValid("GB82WEST12345698765432"));
    }

    @Test
    public void testIsValidWithInvalidLength() {
        assertFalse(validator.isValid("GB82WEST1234569876543"));
    }

    @Test
    public void testIsValidWithInvalidFormat() {
        assertFalse(validator.isValid("GB82WEST1234569876543A"));
    }

    @Test
    public void testIsValidWithNullCode() {
        assertFalse(validator.isValid(null));
    }

    @Test
    public void testIsValidWithUnknownCountryCode() {
        assertFalse(validator.isValid("ZZ82WEST12345698765432"));
    }

    @Test
    public void testIsValidWithLowercaseCountryCode() {
        assertFalse(validator.isValid("gb82WEST12345698765432"));
    }

    @Test
    public void testIsValidWithInvalidCheckDigit() {
        assertFalse(validator.isValid("GB82WEST12345698765433"));
    }
}
