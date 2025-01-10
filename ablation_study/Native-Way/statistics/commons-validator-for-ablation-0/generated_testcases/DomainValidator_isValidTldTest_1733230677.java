
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
    public void testIsValidTldValidCountryCode() {
        assertTrue(validator.isValidTld("us"));
    }

    @Test
    public void testIsValidTldValidGeneric() {
        assertTrue(validator.isValidTld("com"));
    }

    @Test
    public void testIsValidTldValidInfrastructure() {
        assertTrue(validator.isValidTld("arpa"));
    }

    @Test
    public void testIsValidTldValidLocal() {
        assertTrue(validator.isValidTld("localhost"));
    }

    @Test
    public void testIsValidTldInvalid() {
        assertFalse(validator.isValidTld("invalid"));
    }

    @Test
    public void testIsValidTldNull() {
        assertFalse(validator.isValidTld(null));
    }

    @Test
    public void testIsValidTldEmpty() {
        assertFalse(validator.isValidTld(""));
    }

    @Test
    public void testIsValidTldLeadingDot() {
        assertTrue(validator.isValidTld(".com"));
    }

    @Test
    public void testIsValidTldUnicodeToASCII() {
        assertTrue(validator.isValidTld("президент.рф"));
    }

    @Test
    public void testIsValidTldMaxLength() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 253; i++) {
            sb.append('a');
        }
        assertTrue(validator.isValidTld(sb.toString()));
    }

    @Test
    public void testIsValidTldExceedMaxLength() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 254; i++) {
            sb.append('a');
        }
        assertFalse(validator.isValidTld(sb.toString()));
    }
}
