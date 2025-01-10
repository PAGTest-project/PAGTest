
package org.apache.commons.validator.routines;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class IBANValidator_isValidTest {

    private static final IBANValidator VALIDATOR = new IBANValidator();

    @BeforeEach
    public void setUp() {
        // Any setup code if needed
    }

    @Test
    public void testIsValid_ValidIBAN() {
        assertTrue(VALIDATOR.isValid("GB82WEST12345698765432"));
    }

    @Test
    public void testIsValid_InvalidLength() {
        assertFalse(VALIDATOR.isValid("GB82WEST1234569876543"));
    }

    @Test
    public void testIsValid_InvalidFormat() {
        assertFalse(VALIDATOR.isValid("GB82WEST1234569876543A"));
    }

    @Test
    public void testIsValid_NullCode() {
        assertFalse(VALIDATOR.isValid(null));
    }

    @Test
    public void testIsValid_EmptyCode() {
        assertFalse(VALIDATOR.isValid(""));
    }

    @Test
    public void testIsValid_InvalidCountryCode() {
        assertFalse(VALIDATOR.isValid("XX82WEST12345698765432"));
    }

    @Test
    public void testIsValid_LowercaseCountryCode() {
        assertFalse(VALIDATOR.isValid("gb82WEST12345698765432"));
    }

    @Test
    public void testIsValid_ValidIBANWithSpaces() {
        assertTrue(VALIDATOR.isValid("GB82 WEST 1234 5698 7654 32"));
    }

    @Test
    public void testIsValid_ValidIBANWithMixedCase() {
        assertTrue(VALIDATOR.isValid("Gb82WeSt12345698765432"));
    }

    @Test
    public void testIsValid_ValidIBANWithSpecialCharacters() {
        assertFalse(VALIDATOR.isValid("GB82WEST12345698765432!"));
    }

    @Test
    public void testIsValid_ValidIBANWithLeadingZeros() {
        assertTrue(VALIDATOR.isValid("GB00WEST12345698765432"));
    }

    @Test
    public void testIsValid_ValidIBANWithTrailingZeros() {
        assertTrue(VALIDATOR.isValid("GB82WEST1234569876543200"));
    }

    @Test
    public void testIsValid_ValidIBANWithAllZeros() {
        assertFalse(VALIDATOR.isValid("GB00000000000000000000"));
    }

    @Test
    public void testIsValid_ValidIBANWithInvalidCheckDigit() {
        assertFalse(VALIDATOR.isValid("GB82WEST12345698765433"));
    }

    @Test
    public void testIsValid_ValidIBANWithValidCheckDigit() {
        assertTrue(VALIDATOR.isValid("GB82WEST12345698765432"));
    }

    @Test
    public void testIsValid_ValidIBANWithInvalidCountryCode() {
        assertFalse(VALIDATOR.isValid("ZZ82WEST12345698765432"));
    }

    @Test
    public void testIsValid_ValidIBANWithInvalidLengthForCountry() {
        assertFalse(VALIDATOR.isValid("GB82WEST123456987654321"));
    }

    @Test
    public void testIsValid_ValidIBANWithValidLengthForCountry() {
        assertTrue(VALIDATOR.isValid("GB82WEST12345698765432"));
    }

    @Test
    public void testIsValid_ValidIBANWithInvalidRegex() {
        assertFalse(VALIDATOR.isValid("GB82WEST1234569876543A"));
    }

    @Test
    public void testIsValid_ValidIBANWithValidRegex() {
        assertTrue(VALIDATOR.isValid("GB82WEST12345698765432"));
    }

    @Test
    public void testIsValid_ValidIBANWithInvalidCheckDigitCalculation() {
        assertFalse(VALIDATOR.isValid("GB82WEST12345698765433"));
    }

    @Test
    public void testIsValid_ValidIBANWithValidCheckDigitCalculation() {
        assertTrue(VALIDATOR.isValid("GB82WEST12345698765432"));
    }
}
