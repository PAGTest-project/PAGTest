
package org.apache.commons.validator.routines;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.apache.commons.validator.routines.checkdigit.CheckDigit;
import org.apache.commons.validator.routines.checkdigit.EAN13CheckDigit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CodeValidator_validateTest {

    private CodeValidator validator;

    @BeforeEach
    public void setUp() {
        validator = new CodeValidator("\\d{13}", 13, 13, EAN13CheckDigit.EAN13_CHECK_DIGIT);
    }

    @Test
    public void testValidateNullInput() {
        assertNull(validator.validate(null), "Null input should return null");
    }

    @Test
    public void testValidateEmptyInput() {
        assertNull(validator.validate(""), "Empty input should return null");
    }

    @Test
    public void testValidateInvalidRegex() {
        assertNull(validator.validate("1234567890123"), "Input not matching regex should return null");
    }

    @Test
    public void testValidateInvalidLength() {
        assertNull(validator.validate("97819301109912"), "Input with invalid length should return null");
    }

    @Test
    public void testValidateInvalidCheckDigit() {
        assertNull(validator.validate("9781930110992"), "Input with invalid check digit should return null");
    }

    @Test
    public void testValidateValidInput() {
        assertEquals("9781930110991", validator.validate("9781930110991"), "Valid input should return the input");
    }

    @Test
    public void testIsValid() {
        assertTrue(validator.isValid("9781930110991"), "Valid input should return true");
        assertFalse(validator.isValid("9781930110992"), "Invalid input should return false");
    }
}
