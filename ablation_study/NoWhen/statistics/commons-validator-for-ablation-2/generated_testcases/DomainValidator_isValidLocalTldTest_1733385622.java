
package org.apache.commons.validator.routines;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DomainValidator_isValidLocalTldTest {

    private DomainValidator domainValidator;

    @BeforeEach
    public void setUp() {
        domainValidator = DomainValidator.getInstance(true);
    }

    @Test
    public void testIsValidLocalTldValid() {
        assertTrue(domainValidator.isValidLocalTld("localhost"));
        assertTrue(domainValidator.isValidLocalTld("localdomain"));
    }

    @Test
    public void testIsValidLocalTldInvalid() {
        assertFalse(domainValidator.isValidLocalTld("invalid"));
        assertFalse(domainValidator.isValidLocalTld("example.com"));
    }

    @Test
    public void testIsValidLocalTldWithLeadingDot() {
        assertTrue(domainValidator.isValidLocalTld(".localhost"));
        assertTrue(domainValidator.isValidLocalTld(".localdomain"));
    }

    @Test
    public void testIsValidLocalTldWithUnicode() {
        assertTrue(domainValidator.isValidLocalTld("local\uFF61host"));
        assertTrue(domainValidator.isValidLocalTld("local\uFF0Edomain"));
    }

    @Test
    public void testIsValidLocalTldWithMixedCase() {
        assertTrue(domainValidator.isValidLocalTld("LocalHost"));
        assertTrue(domainValidator.isValidLocalTld("LocalDomain"));
    }

    @Test
    public void testIsValidLocalTldWithInvalidUnicode() {
        assertFalse(domainValidator.isValidLocalTld("local\uFF62host"));
        assertFalse(domainValidator.isValidLocalTld("local\uFF0Fdomain"));
    }
}
