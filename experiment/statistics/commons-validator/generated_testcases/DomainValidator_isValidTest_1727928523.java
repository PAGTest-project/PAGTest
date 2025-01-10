
package org.apache.commons.validator.routines;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DomainValidator_isValidTest {

    private DomainValidator domainValidator;

    @BeforeEach
    public void setUp() {
        domainValidator = DomainValidator.getInstance(true);
    }

    @Test
    public void testIsValid_NullDomain() {
        assertFalse(domainValidator.isValid(null));
    }

    @Test
    public void testIsValid_EmptyDomain() {
        assertFalse(domainValidator.isValid(""));
    }

    @Test
    public void testIsValid_ValidDomain() {
        assertTrue(domainValidator.isValid("example.com"));
    }

    @Test
    public void testIsValid_InvalidDomain() {
        assertFalse(domainValidator.isValid("example..com"));
    }

    @Test
    public void testIsValid_ValidUnicodeDomain() {
        assertTrue(domainValidator.isValid("例え.テスト"));
    }

    @Test
    public void testIsValid_InvalidUnicodeDomain() {
        assertFalse(domainValidator.isValid("例え..テスト"));
    }

    @Test
    public void testIsValid_ValidLocalDomain() {
        assertTrue(domainValidator.isValid("localhost"));
    }

    @Test
    public void testIsValid_InvalidLocalDomain() {
        assertFalse(domainValidator.isValid("local..host"));
    }

    @Test
    public void testIsValid_ValidTLD() {
        assertTrue(domainValidator.isValid("example.xn--fiqs8s")); // .中国
    }

    @Test
    public void testIsValid_InvalidTLD() {
        assertFalse(domainValidator.isValid("example.invalidtld"));
    }

    @Test
    public void testIsValid_ValidCountryCodeTLD() {
        assertTrue(domainValidator.isValid("example.us"));
    }

    @Test
    public void testIsValid_InvalidCountryCodeTLD() {
        assertFalse(domainValidator.isValid("example.zz"));
    }

    @Test
    public void testIsValid_ValidGenericTLD() {
        assertTrue(domainValidator.isValid("example.com"));
    }

    @Test
    public void testIsValid_InvalidGenericTLD() {
        assertFalse(domainValidator.isValid("example.invalid"));
    }

    @Test
    public void testIsValid_ValidInfrastructureTLD() {
        assertTrue(domainValidator.isValid("example.arpa"));
    }

    @Test
    public void testIsValid_InvalidInfrastructureTLD() {
        assertFalse(domainValidator.isValid("example.invalidarpa"));
    }

    @Test
    public void testIsValid_ValidLocalTLD() {
        assertTrue(domainValidator.isValid("example.local"));
    }

    @Test
    public void testIsValid_InvalidLocalTLD() {
        assertFalse(domainValidator.isValid("example.invalidlocal"));
    }

    @Test
    public void testIsValid_DomainLengthExceeded() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 254; i++) {
            sb.append('a');
        }
        assertFalse(domainValidator.isValid(sb.toString()));
    }

    @Test
    public void testIsValid_DomainLengthWithinLimit() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 253; i++) {
            sb.append('a');
        }
        assertTrue(domainValidator.isValid(sb.toString()));
    }
}
