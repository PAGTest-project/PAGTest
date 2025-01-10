
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
        // Test valid local TLDs
        assertTrue(validator.isValidLocalTld("localdomain"));
        assertTrue(validator.isValidLocalTld("localhost"));

        // Test invalid local TLDs
        assertFalse(validator.isValidLocalTld("invalidlocal"));
        assertFalse(validator.isValidLocalTld(""));
        assertFalse(validator.isValidLocalTld(null));
    }

    @Test
    public void testIsValidLocalTldWithLeadingDot() {
        // Test valid local TLDs with leading dot
        assertTrue(validator.isValidLocalTld(".localdomain"));
        assertTrue(validator.isValidLocalTld(".localhost"));

        // Test invalid local TLDs with leading dot
        assertFalse(validator.isValidLocalTld(".invalidlocal"));
    }

    @Test
    public void testIsValidLocalTldWithUnicode() {
        // Test valid local TLDs with Unicode characters
        assertTrue(validator.isValidLocalTld("localdomain"));
        assertTrue(validator.isValidLocalTld("localhost"));

        // Test invalid local TLDs with Unicode characters
        assertFalse(validator.isValidLocalTld("invalidlocal"));
    }

    @Test
    public void testIsValidLocalTldWithMixedCase() {
        // Test valid local TLDs with mixed case
        assertTrue(validator.isValidLocalTld("LocalDomain"));
        assertTrue(validator.isValidLocalTld("LocalHost"));

        // Test invalid local TLDs with mixed case
        assertFalse(validator.isValidLocalTld("InvalidLocal"));
    }
}
