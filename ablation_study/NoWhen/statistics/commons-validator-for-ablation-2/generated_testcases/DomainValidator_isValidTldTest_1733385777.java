
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
    public void testIsValidTld_ValidLocalTld() {
        assertTrue(validator.isValidTld("localdomain"));
        assertTrue(validator.isValidTld("localhost"));
    }

    @Test
    public void testIsValidTld_InvalidLocalTld() {
        assertFalse(validator.isValidTld("invalidlocal"));
    }

    @Test
    public void testIsValidTld_ValidInfrastructureTld() {
        assertTrue(validator.isValidTld("arpa"));
    }

    @Test
    public void testIsValidTld_InvalidInfrastructureTld() {
        assertFalse(validator.isValidTld("invalidinfra"));
    }

    @Test
    public void testIsValidTld_ValidGenericTld() {
        assertTrue(validator.isValidTld("com"));
        assertTrue(validator.isValidTld("org"));
    }

    @Test
    public void testIsValidTld_InvalidGenericTld() {
        assertFalse(validator.isValidTld("invalidgeneric"));
    }

    @Test
    public void testIsValidTld_ValidCountryCodeTld() {
        assertTrue(validator.isValidTld("us"));
        assertTrue(validator.isValidTld("uk"));
    }

    @Test
    public void testIsValidTld_InvalidCountryCodeTld() {
        assertFalse(validator.isValidTld("invalidcc"));
    }

    @Test
    public void testIsValidTld_MixedCase() {
        assertTrue(validator.isValidTld("CoM"));
        assertTrue(validator.isValidTld("OrG"));
    }

    @Test
    public void testIsValidTld_EmptyString() {
        assertFalse(validator.isValidTld(""));
    }

    @Test
    public void testIsValidTld_NullString() {
        assertFalse(validator.isValidTld(null));
    }
}
