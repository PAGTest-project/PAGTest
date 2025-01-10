
package org.apache.commons.validator.routines;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RegexValidator_validateTest {

    private RegexValidator validator;

    @BeforeEach
    public void setUp() {
        validator = new RegexValidator(new String[] { "(\\d{3})", "([A-Z]{3})" });
    }

    @Test
    public void testValidateWithNullValue() {
        assertNull(validator.validate(null));
    }

    @Test
    public void testValidateWithMatchingSingleGroup() {
        assertEquals("123", validator.validate("123"));
    }

    @Test
    public void testValidateWithMatchingMultipleGroups() {
        assertEquals("ABC", validator.validate("ABC"));
    }

    @Test
    public void testValidateWithNonMatchingValue() {
        assertNull(validator.validate("abc"));
    }

    @Test
    public void testValidateWithPartialMatchingValue() {
        assertNull(validator.validate("123abc"));
    }
}
