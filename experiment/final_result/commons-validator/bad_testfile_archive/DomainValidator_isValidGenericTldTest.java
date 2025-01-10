
package org.apache.commons.validator.routines;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DomainValidator_isValidGenericTldTest {

    private DomainValidator validator;

    @BeforeEach
    public void setUp() {
        validator = DomainValidator.getInstance();
    }

    @Test
    public void testIsValidGenericTld() {
        // Valid generic TLDs
        assertTrue(validator.isValidGenericTld("com"));
        assertTrue(validator.isValidGenericTld("org"));
        assertTrue(validator.isValidGenericTld("net"));

        // Invalid generic TLDs
        assertFalse(validator.isValidGenericTld("invalid"));
        assertFalse(validator.isValidGenericTld(""));
        assertFalse(validator.isValidGenericTld(null));

        // Case insensitivity
        assertTrue(validator.isValidGenericTld("CoM"));
        assertTrue(validator.isValidGenericTld("OrG"));
        assertTrue(validator.isValidGenericTld("NeT"));

        // Leading dot should be removed
        assertTrue(validator.isValidGenericTld(".com"));
        assertTrue(validator.isValidGenericTld(".org"));
        assertTrue(validator.isValidGenericTld(".net"));

        // Unicode to ASCII conversion
        assertTrue(validator.isValidGenericTld("xn--mgbaam7a8h")); // همراه
        assertFalse(validator.isValidGenericTld("همراه")); // Non-ASCII characters should not be valid
    }
}
