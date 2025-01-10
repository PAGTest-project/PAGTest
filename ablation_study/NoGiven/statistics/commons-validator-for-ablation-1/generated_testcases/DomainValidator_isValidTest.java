
package org.apache.commons.validator.routines;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.net.IDN;

public class DomainValidator_isValidTest {

    private DomainValidator validator;

    @BeforeEach
    public void setUp() {
        validator = DomainValidator.getInstance(false);
    }

    @Test
    public void testIsValid_NullDomain() {
        assertFalse(validator.isValid(null));
    }

    @Test
    public void testIsValid_EmptyDomain() {
        assertFalse(validator.isValid(""));
    }

    @Test
    public void testIsValid_ValidDomain() {
        assertTrue(validator.isValid("example.com"));
    }

    @Test
    public void testIsValid_InvalidDomainLength() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 254; i++) {
            sb.append("a");
        }
        assertFalse(validator.isValid(sb.toString() + ".com"));
    }

    @Test
    public void testIsValid_InvalidTLD() {
        assertFalse(validator.isValid("example.invalidtld"));
    }

    @Test
    public void testIsValid_ValidLocalDomain() {
        DomainValidator localValidator = DomainValidator.getInstance(true);
        assertTrue(localValidator.isValid("localhost"));
    }

    @Test
    public void testIsValid_InvalidLocalDomain() {
        assertFalse(validator.isValid("localhost"));
    }

    @Test
    public void testIsValid_ValidUnicodeDomain() {
        assertTrue(validator.isValid(IDN.toASCII("例え.テスト")));
    }

    @Test
    public void testIsValid_InvalidUnicodeDomain() {
        assertFalse(validator.isValid(IDN.toASCII("例え.テストinvalid")));
    }
}
