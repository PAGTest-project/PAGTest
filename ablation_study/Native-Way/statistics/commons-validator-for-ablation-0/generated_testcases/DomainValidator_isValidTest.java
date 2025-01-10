
package org.apache.commons.validator.routines;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DomainValidator_isValidTest {

    private DomainValidator validator;

    @BeforeEach
    public void setUp() {
        validator = DomainValidator.getInstance(true); // Allow local domains
    }

    @Test
    public void testIsValidWithNullDomain() {
        assertFalse(validator.isValid(null), "Null domain should not be valid");
    }

    @Test
    public void testIsValidWithEmptyDomain() {
        assertFalse(validator.isValid(""), "Empty domain should not be valid");
    }

    @Test
    public void testIsValidWithValidDomain() {
        assertTrue(validator.isValid("example.com"), "example.com should be valid");
    }

    @Test
    public void testIsValidWithInvalidDomain() {
        assertFalse(validator.isValid("invalid..domain"), "Invalid domain should not be valid");
    }

    @Test
    public void testIsValidWithValidUnicodeDomain() {
        assertTrue(validator.isValid("xn--fsq.xn--zckzah"), "Unicode domain should be valid");
    }

    @Test
    public void testIsValidWithInvalidUnicodeDomain() {
        assertFalse(validator.isValid("xn--fsq..xn--zckzah"), "Invalid Unicode domain should not be valid");
    }

    @Test
    public void testIsValidWithValidCountryCodeTLD() {
        assertTrue(validator.isValid("example.co.uk"), "example.co.uk should be valid");
    }

    @Test
    public void testIsValidWithInvalidCountryCodeTLD() {
        assertFalse(validator.isValid("example.invalidcc"), "Invalid country code TLD should not be valid");
    }

    @Test
    public void testIsValidWithValidGenericTLD() {
        assertTrue(validator.isValid("example.info"), "example.info should be valid");
    }

    @Test
    public void testIsValidWithInvalidGenericTLD() {
        assertFalse(validator.isValid("example.invalidgtld"), "Invalid generic TLD should not be valid");
    }

    @Test
    public void testIsValidWithValidLocalTLD() {
        assertTrue(validator.isValid("example.local"), "example.local should be valid");
    }

    @Test
    public void testIsValidWithInvalidLocalTLD() {
        assertFalse(validator.isValid("example.invalidlocal"), "Invalid local TLD should not be valid");
    }

    @Test
    public void testIsValidWithValidInfrastructureTLD() {
        assertTrue(validator.isValid("example.arpa"), "example.arpa should be valid");
    }

    @Test
    public void testIsValidWithInvalidInfrastructureTLD() {
        assertFalse(validator.isValid("example.invalidinfra"), "Invalid infrastructure TLD should not be valid");
    }

    @Test
    public void testIsValidWithDomainExceedingMaxLength() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 254; i++) {
            sb.append('a');
        }
        assertFalse(validator.isValid(sb.toString()), "Domain exceeding max length should not be valid");
    }

    @Test
    public void testIsValidWithDomainAtMaxLength() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 253; i++) {
            sb.append('a');
        }
        assertTrue(validator.isValid(sb.toString() + ".com"), "Domain at max length should be valid");
    }
}
