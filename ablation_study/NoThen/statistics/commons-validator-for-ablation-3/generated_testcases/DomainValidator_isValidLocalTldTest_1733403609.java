
package org.apache.commons.validator.routines;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DomainValidator_isValidLocalTldTest {

    private DomainValidator domainValidator;

    @BeforeEach
    public void setUp() {
        domainValidator = DomainValidator.getInstance(true);
    }

    @Test
    public void testIsValidLocalTld_ValidLocalTld() {
        assertTrue(domainValidator.isValidLocalTld("localdomain"));
        assertTrue(domainValidator.isValidLocalTld("localhost"));
    }

    @Test
    public void testIsValidLocalTld_InvalidLocalTld() {
        assertFalse(domainValidator.isValidLocalTld("invalidlocaldomain"));
        assertFalse(domainValidator.isValidLocalTld("invalidlocalhost"));
    }

    @Test
    public void testIsValidLocalTld_MixedCase() {
        assertTrue(domainValidator.isValidLocalTld("LocalDomain"));
        assertTrue(domainValidator.isValidLocalTld("LocalHost"));
    }

    @Test
    public void testIsValidLocalTld_LeadingDot() {
        assertTrue(domainValidator.isValidLocalTld(".localdomain"));
        assertTrue(domainValidator.isValidLocalTld(".localhost"));
    }

    @Test
    public void testIsValidLocalTld_UnicodeToASCIIConversion() {
        assertTrue(domainValidator.isValidLocalTld("localdomain\uFF0E"));
        assertTrue(domainValidator.isValidLocalTld("localhost\uFF0E"));
    }

    @Test
    public void testIsValidLocalTld_EmptyString() {
        assertFalse(domainValidator.isValidLocalTld(""));
    }

    @Test
    public void testIsValidLocalTld_NullString() {
        assertFalse(domainValidator.isValidLocalTld(null));
    }
}
