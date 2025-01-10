
package org.apache.commons.validator.routines;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DomainValidator_isValidTldTest {

    private DomainValidator validator;

    @BeforeEach
    public void setUp() {
        validator = DomainValidator.getInstance(true);
    }

    @Test
    public void testIsValidTld_LocalTld_Valid() {
        assertTrue(validator.isValidTld("localhost"));
    }

    @Test
    public void testIsValidTld_LocalTld_Invalid() {
        assertFalse(validator.isValidTld("invalidlocal"));
    }

    @Test
    public void testIsValidTld_GenericTld_Valid() {
        assertTrue(validator.isValidTld("com"));
    }

    @Test
    public void testIsValidTld_GenericTld_Invalid() {
        assertFalse(validator.isValidTld("invalidgeneric"));
    }

    @Test
    public void testIsValidTld_CountryCodeTld_Valid() {
        assertTrue(validator.isValidTld("us"));
    }

    @Test
    public void testIsValidTld_CountryCodeTld_Invalid() {
        assertFalse(validator.isValidTld("invalidcountry"));
    }

    @Test
    public void testIsValidTld_InfrastructureTld_Valid() {
        assertTrue(validator.isValidTld("arpa"));
    }

    @Test
    public void testIsValidTld_InfrastructureTld_Invalid() {
        assertFalse(validator.isValidTld("invalidinfrastructure"));
    }
}
