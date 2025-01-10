
package org.apache.commons.validator.routines;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DomainValidator_isValidCountryCodeTldTest {

    private DomainValidator validator;

    @BeforeEach
    public void setUp() {
        validator = DomainValidator.getInstance(true); // Allow local domains for testing purposes
    }

    @Test
    public void testIsValidCountryCodeTld_ValidCountryCodeTld() {
        assertTrue(validator.isValidCountryCodeTld("uk"));
    }

    @Test
    public void testIsValidCountryCodeTld_InvalidCountryCodeTld() {
        assertFalse(validator.isValidCountryCodeTld("invalid"));
    }

    @Test
    public void testIsValidCountryCodeTld_ValidCountryCodeTldWithLeadingDot() {
        assertTrue(validator.isValidCountryCodeTld(".uk"));
    }

    @Test
    public void testIsValidCountryCodeTld_InvalidCountryCodeTldWithLeadingDot() {
        assertFalse(validator.isValidCountryCodeTld(".invalid"));
    }

    @Test
    public void testIsValidCountryCodeTld_ValidCountryCodeTldWithUnicode() {
        assertTrue(validator.isValidCountryCodeTld("xn--mgbaam7a8h")); // UAE in Arabic script
    }

    @Test
    public void testIsValidCountryCodeTld_InvalidCountryCodeTldWithUnicode() {
        assertFalse(validator.isValidCountryCodeTld("xn--invalid"));
    }

    @Test
    public void testIsValidCountryCodeTld_ValidCountryCodeTldWithPlusOverride() {
        DomainValidator.updateTLDOverride(DomainValidator.ArrayType.COUNTRY_CODE_PLUS, "testcc");
        assertTrue(validator.isValidCountryCodeTld("testcc"));
    }

    @Test
    public void testIsValidCountryCodeTld_InvalidCountryCodeTldWithMinusOverride() {
        DomainValidator.updateTLDOverride(DomainValidator.ArrayType.COUNTRY_CODE_MINUS, "uk");
        assertFalse(validator.isValidCountryCodeTld("uk"));
    }

    @Test
    public void testIsValidCountryCodeTld_EmptyString() {
        assertFalse(validator.isValidCountryCodeTld(""));
    }

    @Test
    public void testIsValidCountryCodeTld_NullInput() {
        assertFalse(validator.isValidCountryCodeTld(null));
    }
}
