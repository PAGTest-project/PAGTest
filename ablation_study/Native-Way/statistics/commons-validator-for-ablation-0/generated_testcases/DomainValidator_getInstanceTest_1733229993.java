
package org.apache.commons.validator.routines;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DomainValidator_getInstanceTest {
    private DomainValidator validator;

    @BeforeEach
    public void setUp() {
        validator = DomainValidator.getInstance(false);
    }

    @Test
    public void testGetInstanceWithLocalAllowed() {
        DomainValidator localValidator = DomainValidator.getInstance(true);
        assertNotNull(localValidator);
        assertTrue(localValidator.isAllowLocal());
    }

    @Test
    public void testGetInstanceWithLocalNotAllowed() {
        DomainValidator nonLocalValidator = DomainValidator.getInstance(false);
        assertNotNull(nonLocalValidator);
        assertFalse(nonLocalValidator.isAllowLocal());
    }

    @Test
    public void testGetInstanceWithLocalAllowedAndCustomTLDs() {
        List<DomainValidator.Item> items = new ArrayList<>();
        items.add(new DomainValidator.Item(DomainValidator.ArrayType.GENERIC_PLUS, "local", "pvt"));
        DomainValidator customValidator = DomainValidator.getInstance(true, items);
        assertNotNull(customValidator);
        assertTrue(customValidator.isAllowLocal());
        assertTrue(customValidator.isValidGenericTld("local"));
        assertTrue(customValidator.isValid("abc.local"));
        assertTrue(customValidator.isValidGenericTld("pvt"));
        assertTrue(customValidator.isValid("abc.pvt"));
    }

    @Test
    public void testGetInstanceWithLocalNotAllowedAndCustomTLDs() {
        List<DomainValidator.Item> items = new ArrayList<>();
        items.add(new DomainValidator.Item(DomainValidator.ArrayType.GENERIC_PLUS, "local", "pvt"));
        DomainValidator customValidator = DomainValidator.getInstance(false, items);
        assertNotNull(customValidator);
        assertFalse(customValidator.isAllowLocal());
        assertTrue(customValidator.isValidGenericTld("local"));
        assertTrue(customValidator.isValid("abc.local"));
        assertTrue(customValidator.isValidGenericTld("pvt"));
        assertTrue(customValidator.isValid("abc.pvt"));
    }

    @Test
    public void testGetTLDEntries() {
        String[] genericTLDs = DomainValidator.getTLDEntries(DomainValidator.ArrayType.GENERIC_RO);
        assertNotNull(genericTLDs);
        assertTrue(genericTLDs.length > 0);

        String[] countryCodeTLDs = DomainValidator.getTLDEntries(DomainValidator.ArrayType.COUNTRY_CODE_RO);
        assertNotNull(countryCodeTLDs);
        assertTrue(countryCodeTLDs.length > 0);
    }

    @Test
    public void testUnicodeToASCII() {
        String asciiDomain = DomainValidator.unicodeToASCII("президент.рф");
        assertEquals("xn--d1abbgf6aiiy.xn--p1ai", asciiDomain);
    }

    @Test
    public void testIsValidDomainSyntax() {
        assertTrue(validator.isValidDomainSyntax("example.com"));
        assertFalse(validator.isValidDomainSyntax("example..com"));
    }

    @Test
    public void testIsValid() {
        assertTrue(validator.isValid("example.com"));
        assertFalse(validator.isValid("example..com"));
    }
}
