
package org.apache.commons.validator.routines;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DomainValidator_isValidInfrastructureTldTest {

    private DomainValidator validator;

    @BeforeEach
    public void setUp() {
        validator = DomainValidator.getInstance();
    }

    @Test
    public void testIsValidInfrastructureTld() {
        // Valid infrastructure TLDs
        assertTrue(validator.isValidInfrastructureTld("arpa"), "arpa should validate as iTLD");
        assertTrue(validator.isValidInfrastructureTld("ARPA"), "ARPA should validate as iTLD");

        // Invalid infrastructure TLDs
        assertFalse(validator.isValidInfrastructureTld("com"), "com shouldn't validate as iTLD");
        assertFalse(validator.isValidInfrastructureTld(""), "empty string shouldn't validate as iTLD");
        assertFalse(validator.isValidInfrastructureTld(null), "null shouldn't validate as iTLD");
    }

    @Test
    public void testChompLeadingDot() {
        assertEquals("example.com", validator.chompLeadingDot(".example.com"), "Leading dot should be removed");
        assertEquals("example.com", validator.chompLeadingDot("example.com"), "String without leading dot should remain unchanged");
        assertEquals("", validator.chompLeadingDot("."), "Single dot should be removed");
        assertEquals("", validator.chompLeadingDot(""), "Empty string should remain unchanged");
    }

    @Test
    public void testUnicodeToASCII() {
        assertEquals("xn--e1aybc", validator.unicodeToASCII("пример"), "Unicode string should be converted to ASCII Punycode");
        assertEquals("example.com", validator.unicodeToASCII("example.com"), "ASCII string should remain unchanged");
        assertEquals("", validator.unicodeToASCII(""), "Empty string should remain unchanged");
    }

    @Test
    public void testArrayContains() {
        String[] array = {"arpa", "com", "net"};
        assertTrue(validator.arrayContains(array, "arpa"), "arpa should be found in the array");
        assertFalse(validator.arrayContains(array, "org"), "org should not be found in the array");
    }
}
