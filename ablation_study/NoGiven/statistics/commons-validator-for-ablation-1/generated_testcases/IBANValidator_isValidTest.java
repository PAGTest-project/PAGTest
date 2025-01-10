
package org.apache.commons.validator.routines;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class IBANValidator_isValidTest {

    private static IBANValidator validator;

    @BeforeAll
    public static void setUp() {
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
    public void testIsValidWithEmptyCode() {
        assertFalse(validator.isValid(""));
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

    @Test
    public void testIsValidWithValidIBANForOtherCountryCode() {
        assertTrue(validator.isValid("DE89370400440532013000"));
    }

    @Test
    public void testIsValidWithValidIBANForMultipleCountryCodes() {
        assertTrue(validator.isValid("FR1420041010050500013M02606"));
    }

    @Test
    public void testIsValidWithValidIBANForShortestLength() {
        assertTrue(validator.isValid("NO9386011117947"));
    }

    @Test
    public void testIsValidWithValidIBANForLongestLength() {
        assertTrue(validator.isValid("MU17BOMM0101101030300200000MUR"));
    }
}
