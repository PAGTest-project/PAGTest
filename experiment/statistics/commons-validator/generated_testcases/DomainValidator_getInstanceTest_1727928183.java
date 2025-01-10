
package org.apache.commons.validator.routines;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DomainValidator_getInstanceTest {

    private DomainValidator validator;

    @BeforeEach
    public void setUp() {
        validator = DomainValidator.getInstance();
    }

    @Test
    public void testGetInstance() {
        DomainValidator instance = DomainValidator.getInstance();
        assertNotNull(instance);
        assertTrue(instance instanceof DomainValidator);
    }

    @Test
    public void testIsValid() {
        assertTrue(validator.isValid("example.com"));
        assertFalse(validator.isValid("invalid-domain"));
    }

    @Test
    public void testIsValidTld() {
        assertTrue(validator.isValidTld("com"));
        assertFalse(validator.isValidTld("invalid"));
    }

    @Test
    public void testUnicodeToASCII() {
        String unicodeDomain = "пример.испытание";
        String asciiDomain = DomainValidator.unicodeToASCII(unicodeDomain);
        assertEquals("xn--e1afmkfd.xn--80akhbyknj4f", asciiDomain);
    }

    @Test
    public void testUpdateTLDOverride() {
        DomainValidator.updateTLDOverride(DomainValidator.ArrayType.GENERIC_PLUS, "test");
        DomainValidator validator = DomainValidator.getInstance();
        assertTrue(validator.isValidGenericTld("test"));
    }

    @Test
    public void testGetTLDEntries() {
        String[] tldEntries = DomainValidator.getTLDEntries(DomainValidator.ArrayType.GENERIC_RO);
        assertNotNull(tldEntries);
        assertTrue(tldEntries.length > 0);
    }
}
