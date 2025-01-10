
package org.apache.commons.validator.routines;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class DomainValidator_isValidTest {

    private final DomainValidator validator = DomainValidator.getInstance(false);

    @Test
    public void testIsValid_NullDomain() {
        assertFalse(validator.isValid(null));
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
    public void testIsValid_ValidTLD() {
        assertTrue(validator.isValid("example.com"));
    }

    @Test
    public void testIsValid_InvalidTLD() {
        assertFalse(validator.isValid("example.invalidtld"));
    }

    @Test
    public void testIsValid_LocalDomainAllowed() {
        DomainValidator localValidator = DomainValidator.getInstance(true);
        assertTrue(localValidator.isValid("localhost"));
    }

    @Test
    public void testIsValid_LocalDomainNotAllowed() {
        assertFalse(validator.isValid("localhost"));
    }
}
