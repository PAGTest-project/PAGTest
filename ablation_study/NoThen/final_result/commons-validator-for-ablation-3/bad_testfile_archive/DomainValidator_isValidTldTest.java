
package org.apache.commons.validator.routines;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DomainValidator_isValidTldTest {

    private DomainValidator validator;

    @BeforeEach
    public void setUp() {
        validator = DomainValidator.getInstance(true);
    }

    @Test
    public void testIsValidTld_ValidLocalTld() {
        assertTrue(validator.isValidTld("localdomain"));
        assertTrue(validator.isValidTld("localhost"));
    }

    @Test
    public void testIsValidTld_ValidInfrastructureTld() {
        assertTrue(validator.isValidTld("arpa"));
    }

    @Test
    public void testIsValidTld_ValidGenericTld() {
        assertTrue(validator.isValidTld("com"));
        assertTrue(validator.isValidTld("org"));
    }

    @Test
    public void testIsValidTld_ValidCountryCodeTld() {
        assertTrue(validator.isValidTld("uk"));
        assertTrue(validator.isValidTld("us"));
    }

    @Test
    public void testIsValidTld_InvalidTld() {
        assertFalse(validator.isValidTld("invalid"));
        assertFalse(validator.isValidTld(""));
        assertFalse(validator.isValidTld(null));
    }

    @Test
    public void testIsValidTld_CaseInsensitive() {
        assertTrue(validator.isValidTld("COM"));
        assertTrue(validator.isValidTld("BiZ"));
    }

    @Test
    public void testIsValidTld_UnicodeToASCIIConversion() {
        assertTrue(validator.isValidTld("xn--4gbrim")); // موقع
        assertTrue(validator.isValidTld("xn--fiqs8s")); // 中国
    }

    @Test
    public void testIsValidTld_ChompLeadingDot() {
        assertTrue(validator.isValidTld(".com"));
        assertTrue(validator.isValidTld(".org"));
    }
}
