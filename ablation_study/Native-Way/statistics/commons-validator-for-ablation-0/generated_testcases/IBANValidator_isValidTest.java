
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
    public void testIsValidWithInvalidIBAN() {
        assertFalse(validator.isValid("GB82WEST12345698765433"));
    }

    @Test
    public void testIsValidWithNullCode() {
        assertFalse(validator.isValid(null));
    }

    @Test
    public void testIsValidWithShortCode() {
        assertFalse(validator.isValid("GB1"));
    }

    @Test
    public void testIsValidWithInvalidCountryCode() {
        assertFalse(validator.isValid("XX82WEST12345698765432"));
    }

    @Test
    public void testIsValidWithInvalidLength() {
        assertFalse(validator.isValid("GB82WEST123456987654321"));
    }

    @Test
    public void testIsValidWithInvalidFormat() {
        assertFalse(validator.isValid("GB82WEST1234569876543A"));
    }

    @Test
    public void testIsValidWithValidIBANForOtherCountryCode() {
        assertTrue(validator.isValid("AX2112345600000785"));
    }

    @Test
    public void testIsValidWithInvalidIBANForOtherCountryCode() {
        assertFalse(validator.isValid("AX2112345600000786"));
    }
}
