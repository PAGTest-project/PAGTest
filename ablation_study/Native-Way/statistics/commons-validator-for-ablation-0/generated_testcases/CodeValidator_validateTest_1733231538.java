
package org.apache.commons.validator.routines;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.apache.commons.validator.routines.checkdigit.CheckDigit;
import org.apache.commons.validator.routines.checkdigit.EAN13CheckDigit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CodeValidator_validateTest {

    private CodeValidator codeValidator;
    private CheckDigit checkDigit;

    @BeforeEach
    public void setUp() {
        checkDigit = EAN13CheckDigit.EAN13_CHECK_DIGIT;
        codeValidator = new CodeValidator("^[0-9]*$", 13, 13, checkDigit);
    }

    @Test
    public void testValidateNullInput() {
        assertNull(codeValidator.validate(null), "Null input should return null");
    }

    @Test
    public void testValidateEmptyInput() {
        assertNull(codeValidator.validate(""), "Empty input should return null");
    }

    @Test
    public void testValidateInvalidRegex() {
        codeValidator = new CodeValidator("^[A-Z]*$", 13, 13, checkDigit);
        assertNull(codeValidator.validate("1234567890123"), "Input does not match regex should return null");
    }

    @Test
    public void testValidateInvalidLength() {
        assertNull(codeValidator.validate("123456789012"), "Input length less than minLength should return null");
        assertNull(codeValidator.validate("12345678901234"), "Input length greater than maxLength should return null");
    }

    @Test
    public void testValidateInvalidCheckDigit() {
        assertNull(codeValidator.validate("1234567890128"), "Invalid check digit should return null");
    }

    @Test
    public void testValidateValidInput() {
        assertEquals("1234567890128", codeValidator.validate("1234567890128"), "Valid input should return the input");
    }
}
