
package org.apache.commons.validator.routines;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DomainValidator_isValidLocalTldTest {

    private DomainValidator validator;

    @BeforeEach
    public void setUp() {
        validator = DomainValidator.getInstance(true);
    }

    @Test
    public void testIsValidLocalTld_ValidLocalTld() {
        assertTrue(validator.isValidLocalTld("localhost"));
    }

    @Test
    public void testIsValidLocalTld_InvalidLocalTld() {
        assertFalse(validator.isValidLocalTld("invalidlocal"));
    }

    @Test
    public void testIsValidLocalTld_ValidLocalTldWithPlus() {
        assertTrue(validator.isValidLocalTld("localdomain"));
    }

    @Test
    public void testIsValidLocalTld_InvalidLocalTldWithMinus() {
        assertFalse(validator.isValidLocalTld("invalidlocal"));
    }
}
