
package org.apache.commons.validator.routines;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RegexValidator_validateTest {

    private RegexValidator validator;

    @BeforeEach
    public void setUp() {
        validator = new RegexValidator("^([A-Z]{2})-(\\d{2})-(\\d{4})$");
    }

    @Test
    public void testValidateNullInput() {
        assertNull(validator.validate(null), "Null input should return null");
    }

    @Test
    public void testValidateSingleGroupMatch() {
        assertEquals("AB1234", validator.validate("AB-12-3456"), "Single group match should return the group");
    }

    @Test
    public void testValidateMultipleGroupMatch() {
        assertEquals("AB123456", validator.validate("AB-12-3456"), "Multiple group match should return concatenated groups");
    }

    @Test
    public void testValidateNoMatch() {
        assertNull(validator.validate("AB123456"), "No match should return null");
    }
}
