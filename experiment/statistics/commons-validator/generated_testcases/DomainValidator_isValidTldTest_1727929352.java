
package org.apache.commons.validator.routines;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DomainValidator_isValidTldTest {

    private DomainValidator validator;

    @BeforeEach
    public void setUp() {
        validator = DomainValidator.getInstance();
    }

    @Test
    public void testIsValidTld() {
        // Given valid TLDs
        String validCountryCodeTld = "uk";
        String validGenericTld = "com";
        String validInfrastructureTld = "arpa";
        String validLocalTld = "local";

        // When validating TLDs
        boolean isValidCountryCodeTld = validator.isValidTld(validCountryCodeTld);
        boolean isValidGenericTld = validator.isValidTld(validGenericTld);
        boolean isValidInfrastructureTld = validator.isValidTld(validInfrastructureTld);
        boolean isValidLocalTld = validator.isValidTld(validLocalTld);

        // Then all valid TLDs should be valid
        assertTrue(isValidCountryCodeTld, "Valid country code TLD should be valid");
        assertTrue(isValidGenericTld, "Valid generic TLD should be valid");
        assertTrue(isValidInfrastructureTld, "Valid infrastructure TLD should be valid");
        assertTrue(isValidLocalTld, "Valid local TLD should be valid");
    }

    @Test
    public void testIsValidTldWithInvalidTld() {
        // Given invalid TLDs
        String invalidTld = "invalid";

        // When validating TLDs
        boolean isValidTld = validator.isValidTld(invalidTld);

        // Then invalid TLD should not be valid
        assertFalse(isValidTld, "Invalid TLD should not be valid");
    }

    @Test
    public void testIsValidTldWithNull() {
        // Given null TLD
        String nullTld = null;

        // When validating TLDs
        boolean isValidTld = validator.isValidTld(nullTld);

        // Then null TLD should not be valid
        assertFalse(isValidTld, "Null TLD should not be valid");
    }

    @Test
    public void testIsValidTldWithEmptyString() {
        // Given empty TLD
        String emptyTld = "";

        // When validating TLDs
        boolean isValidTld = validator.isValidTld(emptyTld);

        // Then empty TLD should not be valid
        assertFalse(isValidTld, "Empty TLD should not be valid");
    }

    @Test
    public void testIsValidTldWithLeadingDot() {
        // Given TLDs with leading dots
        String validCountryCodeTld = ".uk";
        String validGenericTld = ".com";
        String validInfrastructureTld = ".arpa";
        String validLocalTld = ".local";

        // When validating TLDs
        boolean isValidCountryCodeTld = validator.isValidTld(validCountryCodeTld);
        boolean isValidGenericTld = validator.isValidTld(validGenericTld);
        boolean isValidInfrastructureTld = validator.isValidTld(validInfrastructureTld);
        boolean isValidLocalTld = validator.isValidTld(validLocalTld);

        // Then TLDs with leading dots should be valid
        assertTrue(isValidCountryCodeTld, "Country code TLD with leading dot should be valid");
        assertTrue(isValidGenericTld, "Generic TLD with leading dot should be valid");
        assertTrue(isValidInfrastructureTld, "Infrastructure TLD with leading dot should be valid");
        assertTrue(isValidLocalTld, "Local TLD with leading dot should be valid");
    }

    @Test
    public void testIsValidTldWithUnicode() {
        // Given TLDs with Unicode characters
        String validCountryCodeTld = "الاردن"; // Jordan in Arabic
        String validGenericTld = "公司"; // Company in Chinese

        // When validating TLDs
        boolean isValidCountryCodeTld = validator.isValidTld(validCountryCodeTld);
        boolean isValidGenericTld = validator.isValidTld(validGenericTld);

        // Then TLDs with Unicode characters should be valid
        assertTrue(isValidCountryCodeTld, "Country code TLD with Unicode characters should be valid");
        assertTrue(isValidGenericTld, "Generic TLD with Unicode characters should be valid");
    }
}
