
package org.apache.commons.validator.routines;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DomainValidator_isValidTest {
    private DomainValidator validator;

    @BeforeEach
    public void setUp() {
        validator = DomainValidator.getInstance();
    }

    @Test
    public void testIsValidWithValidDomains() {
        assertTrue(validator.isValid("example.com"));
        assertTrue(validator.isValid("test.co.uk"));
        assertTrue(validator.isValid("sub.domain.example.com"));
    }

    @Test
    public void testIsValidWithInvalidDomains() {
        assertFalse(validator.isValid("invalid-domain"));
        assertFalse(validator.isValid("example..com"));
        assertFalse(validator.isValid("test.invalidtld"));
    }

    @Test
    public void testIsValidWithNullAndEmpty() {
        assertFalse(validator.isValid(null));
        assertFalse(validator.isValid(""));
    }

    @Test
    public void testIsValidWithLocalDomains() {
        assertFalse(validator.isValid("localhost")); // Default instance does not allow local domains
        DomainValidator localValidator = DomainValidator.getInstance(true);
        assertTrue(localValidator.isValid("localhost")); // Instance allowing local domains
    }

    @Test
    public void testIsValidWithUnicodeToASCIIConversion() {
        assertTrue(validator.isValid("президент.рф")); // Cyrillic domain
        assertTrue(validator.isValid("例子.中国")); // Chinese domain
    }

    @Test
    public void testIsValidWithLeadingDot() {
        assertFalse(validator.isValid(".example.com"));
        assertFalse(validator.isValid(".test.co.uk"));
    }

    @Test
    public void testIsValidWithMaxLength() {
        StringBuilder longDomain = new StringBuilder("a");
        for (int i = 0; i < 252; i++) { // 253 characters total
            longDomain.append("a");
        }
        longDomain.append(".com");
        assertTrue(validator.isValid(longDomain.toString()));

        longDomain.append("a"); // 254 characters total
        assertFalse(validator.isValid(longDomain.toString()));
    }

    @Test
    public void testIsValidWithInvalidTLDs() {
        assertFalse(validator.isValid("example.invalidtld"));
        assertFalse(validator.isValid("test.nonexistenttld"));
    }

    @Test
    public void testIsValidWithValidTLDs() {
        assertTrue(validator.isValid("example.com"));
        assertTrue(validator.isValid("test.co.uk"));
        assertTrue(validator.isValid("sub.domain.example.net"));
    }

    @Test
    public void testIsValidWithMixedCase() {
        assertTrue(validator.isValid("Example.Com"));
        assertTrue(validator.isValid("Test.Co.Uk"));
    }

    @Test
    public void testIsValidWithUnderscore() {
        assertFalse(validator.isValid("example_domain.com"));
        assertFalse(validator.isValid("test.co_uk"));
    }

    @Test
    public void testIsValidWithHyphen() {
        assertTrue(validator.isValid("example-domain.com"));
        assertTrue(validator.isValid("test-domain.co.uk"));
    }

    @Test
    public void testIsValidWithNumbers() {
        assertTrue(validator.isValid("123.com"));
        assertTrue(validator.isValid("test123.co.uk"));
    }

    @Test
    public void testIsValidWithSpecialCharacters() {
        assertFalse(validator.isValid("example!domain.com"));
        assertFalse(validator.isValid("test@domain.co.uk"));
    }
}
