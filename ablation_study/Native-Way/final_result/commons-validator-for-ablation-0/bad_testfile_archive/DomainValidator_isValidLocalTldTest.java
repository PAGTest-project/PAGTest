
package org.apache.commons.validator.routines;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DomainValidator_isValidLocalTldTest {

    private DomainValidator validator;

    @BeforeEach
    public void setUp() {
        validator = DomainValidator.getInstance();
    }

    @Test
    public void testIsValidLocalTld() {
        // Valid local TLDs
        assertTrue(validator.isValidLocalTld("localdomain"), "localdomain should validate as local TLD");
        assertTrue(validator.isValidLocalTld("localhost"), "localhost should validate as local TLD");

        // Invalid local TLDs
        assertFalse(validator.isValidLocalTld("invalidlocal"), "invalidlocal should not validate as local TLD");
        assertFalse(validator.isValidLocalTld(""), "empty string should not validate as local TLD");
        assertFalse(validator.isValidLocalTld(null), "null should not validate as local TLD");
    }

    @Test
    public void testIsValidLocalTldWithLeadingDot() {
        // Valid local TLDs with leading dot
        assertTrue(validator.isValidLocalTld(".localdomain"), ".localdomain should validate as local TLD");
        assertTrue(validator.isValidLocalTld(".localhost"), ".localhost should validate as local TLD");

        // Invalid local TLDs with leading dot
        assertFalse(validator.isValidLocalTld(".invalidlocal"), ".invalidlocal should not validate as local TLD");
    }

    @Test
    public void testIsValidLocalTldWithMixedCase() {
        // Valid local TLDs with mixed case
        assertTrue(validator.isValidLocalTld("LocalDomain"), "LocalDomain should validate as local TLD");
        assertTrue(validator.isValidLocalTld("LocalHost"), "LocalHost should validate as local TLD");

        // Invalid local TLDs with mixed case
        assertFalse(validator.isValidLocalTld("InvalidLocal"), "InvalidLocal should not validate as local TLD");
    }

    @Test
    public void testIsValidLocalTldWithUnicode() {
        // Valid local TLDs with Unicode characters
        assertTrue(validator.isValidLocalTld("localdomain"), "localdomain should validate as local TLD");
        assertTrue(validator.isValidLocalTld("localhost"), "localhost should validate as local TLD");

        // Invalid local TLDs with Unicode characters
        assertFalse(validator.isValidLocalTld("invalidlocal"), "invalidlocal should not validate as local TLD");
    }
}
