
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
    public void testValidDomains() {
        assertTrue(validator.isValid("apache.org"), "apache.org should validate");
        assertTrue(validator.isValid("www.google.com"), "www.google.com should validate");
        assertTrue(validator.isValid("test-domain.com"), "test-domain.com should validate");
        assertTrue(validator.isValid("test---domain.com"), "test---domain.com should validate");
        assertTrue(validator.isValid("test-d-o-m-ain.com"), "test-d-o-m-ain.com should validate");
        assertTrue(validator.isValid("as.uk"), "two-letter domain label should validate");
        assertTrue(validator.isValid("ApAchE.Org"), "case-insensitive ApAchE.Org should validate");
        assertTrue(validator.isValid("z.com"), "single-character domain label should validate");
        assertTrue(validator.isValid("i.have.an-example.domain.name"), "i.have.an-example.domain.name should validate");
    }

    @Test
    public void testInvalidDomains() {
        assertFalse(validator.isValid("invalid..domain.com"), "invalid..domain.com should not validate");
        assertFalse(validator.isValid("invalid-.domain.com"), "invalid-.domain.com should not validate");
        assertFalse(validator.isValid("-invalid.domain.com"), "-invalid.domain.com should not validate");
        assertFalse(validator.isValid("invalid.domain-.com"), "invalid.domain-.com should not validate");
        assertFalse(validator.isValid("invalid.domain.com-"), "invalid.domain.com- should not validate");
        assertFalse(validator.isValid("invalid.domain.com."), "invalid.domain.com. should not validate");
        assertFalse(validator.isValid("invalid.domain.com.."), "invalid.domain.com.. should not validate");
    }

    @Test
    public void testLocalDomains() {
        DomainValidator localValidator = DomainValidator.getInstance(true);
        assertTrue(localValidator.isValid("localhost"), "localhost should validate when local is allowed");
        assertTrue(localValidator.isValid("localdomain"), "localdomain should validate when local is allowed");
        assertFalse(validator.isValid("localhost"), "localhost should not validate when local is not allowed");
        assertFalse(validator.isValid("localdomain"), "localdomain should not validate when local is not allowed");
    }

    @Test
    public void testTLDOverride() {
        DomainValidator.updateTLDOverride(DomainValidator.ArrayType.GENERIC_PLUS, "example");
        DomainValidator customValidator = DomainValidator.getInstance(false);
        assertTrue(customValidator.isValid("test.example"), "test.example should validate after TLD override");
    }
}
